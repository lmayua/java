package ext.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class Test {
    public static void main(String[] args) throws IOException {
        FTPClientPool pool = new FTPClientPool();

        FTPClientConfiguration conf = new FTPClientConfiguration();
        conf.setHostName("");
        conf.setPort(21);
        conf.setUserName("");
        conf.setPassword("");

        FTPClient ftpClient = null;
        try {
            ftpClient = pool.borrowFtpClient(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ftpClient.connect(host, port);
        //ftpClient.login(username, password);
        //ftpClient.changeToParentDirectory();
        //ftpClient.changeWorkingDirectory(pathname);
        //boolean isCompletePending = ftpClient.completePendingCommand(); // 阻塞状态，直到等待的任务完成，一般处理多个文件防止传输未结束关闭连接
        //ftpClient.cwd(directory); //等于changeWorkingDirectory
        //ftpClient.deleteFile(pathname);
        //ftpClient.logout();
        //ftpClient.disconnect();
        //ftpClient.isAvailable();
        //ftpClient.isConnected();
        //ftpClient.list();
        //ftpClient.list(pathname);
        //ftpClient.listDirectories();
        //ftpClient.listDirectories(parent);
        //ftpClient.listFiles();
        //ftpClient.listFiles(pathname);
        //ftpClient.listFiles(pathname, filter);
        //ftpClient.makeDirectory(pathname);
        //ftpClient.mkd(pathname);
        //ftpClient.retrieveFileStream(remote);
    }
}
