package ext.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

/**
 * FTP服务类
 */
public class FTPService {

    /**
     * LOG
     */
    private static final Log LOG = LogFactory.getLog(FTPService.class);

    private FTPClient ftpClient;
    
    private FTPClientPool pool;
    
    private FTPClientConfiguration ftpConf;

    /**
     * <default constructor>
     * @param pool
     * @param conf
     */
    public FTPService(FTPClientPool pool, FTPClientConfiguration conf){
        this.pool = pool;
        this.ftpConf = conf;
        try {
            this.ftpClient = this.pool.borrowFtpClient(ftpConf);
        } catch (Exception e) {
            LOG.error("创建FTPClient失败，错误信息->\n", e);
        }
    }
    
    public String[] getFileNames(String path, String prefix, String tail) {
        List<String> fileNameList = new ArrayList<>();
        try {
            ftpClient.changeWorkingDirectory(path);
            String[] nameArr = ftpClient.listNames();
            if (null != nameArr) {
                for (String fileName : ftpClient.listNames()) {
                    if (fileName.startsWith(prefix) && fileName.endsWith(tail)) {
                        fileNameList.add(fileName);
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(MessageFormat.format("获取{0}目录下,文件{1}*{2}错误，错误信息->\n", path, prefix, tail), e);
        }
        String[] fileNameArr = new String[fileNameList.size()];
        return fileNameList.toArray(fileNameArr);
    }
    /**
     * 判断文件是否存在
     *
     * @param path
     * @param prefix
     * @param tail
     * @return
     */
    public boolean isExistFile(String path, final String prefix, final String tail){
        FTPFile[] files = null;
        try {
            files = ftpClient.listFiles(path, new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile file) {
                    return null != file && file.getName().startsWith(prefix) && file.getName().endsWith(tail);
                }
            });
        } catch (IOException e) {
            LOG.error(MessageFormat.format("判断{0}目录下文件{1}*{2}是否存在发生异常，错误信息->\n", path, prefix, tail), e);
        }
        return null != files && files.length > 0;
    }
    
    /**
     * 判断是否存在指定文件
     *
     * @param path 路径
     * @param fileName 文件名
     * @return
     */
    public boolean isExistFile(String path, final String fileName){
        FTPFile[] files = null;
        try {
            files = ftpClient.listFiles(path, new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile file) {
                    return null != file && file.getName().equals(fileName);
                }
            });
        } catch (IOException e) {
            LOG.error(MessageFormat.format("判断{0}目录下文件{1}是否存在发生异常，错误信息->\n", path, fileName), e);
        }
        return null != files && files.length > 0;
    }
    
    /**
     * 获取读数据流
     * retrieveFileStream方法获取FTP流，结束后必须调用completePendingCommand等待Server端返回226完成码，否则后续该ftpClient调用会出现问题
     */
    public BufferedReader getFileReader(String path, String fileName) {
        BufferedReader br = null;
        try {
            ftpClient.changeWorkingDirectory(path);
            br = new BufferedReader(new InputStreamReader(ftpClient.retrieveFileStream(fileName)));
        } catch (IOException e) {
            LOG.error(MessageFormat.format("获取FTP文件流发生IOException，文件：{0}/{1}", path, fileName), e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            LOG.error(MessageFormat.format("获取FTP文件流发生未知异常，文件：{0}/{1}", path, fileName), e);
            throw e;
        }
        return br;
    }

    /**
     * 等待任务传输结束
     * 
     * completePendingCommand()方法等待FTP Server返回226(Transfer complete)，FTP Server只有在接受到InputStream执行close方法时才会返回226
     * 所以流程是ftpClient.retrieveFileStream结束关闭流-> FTP Server 发送226返回吗 -> FTPClient的 completePendingCommand方法等待226返回码
     */
    public boolean completePendingCommand(){
        boolean isCompleted = false;
        try {
            isCompleted = ftpClient.completePendingCommand();
        } catch (IOException e) {
            LOG.error("'completePendingCommand' with IOException", e);
        }
        return isCompleted;
    }
    
    /**
     * 登出FTP
     */
    public void closeConn() {
        this.pool.returnFtpClient(ftpClient, ftpConf);
    }
}
