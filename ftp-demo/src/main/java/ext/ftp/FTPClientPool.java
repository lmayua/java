package ext.ftp;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * FTP客户端池
 */
public class FTPClientPool {

    /**
     * LOG
     */
    private static final Log LOG = LogFactory.getLog(FTPClientPool.class);

    /**
     * FTPClient对象池
     */
    private GenericObjectPool<FTPClient> pool;

    /**
     * FTPClient配置信息类
     */
    protected FTPClientConfiguration ftpConf;

    /**
     * <default constructor>
     */
    public FTPClientPool() {
        this.pool = new GenericObjectPool<FTPClient>(new FTPClientPoolFactory(),
                new GenericObjectPoolConfig<FTPClient>());
    }

    /**
     * 从FTP客户端池中获取一个FTPClient
     *
     * @param ftpClientConf FTPClient配置信息
     * @return
     * @throws Exception
     */
    public FTPClient borrowFtpClient(FTPClientConfiguration ftpClientConf) throws Exception {
        this.ftpConf = ftpClientConf;
        return pool.borrowObject();
    }

    /**
     * 归还FTP客户端
     * 
     * @param ftpClient FTP客户端
     */
    public void returnFtpClient(FTPClient ftpClient) {
        pool.returnObject(ftpClient);
    }

    /**
     * FTP客户端池工厂
     */
    class FTPClientPoolFactory extends BasePooledObjectFactory<FTPClient> {

        @Override
        public FTPClient create() throws Exception {
            FTPClient ftpClient = new FTPClient();
            ftpClient.setControlEncoding("utf-8");
            try {
                ftpClient.connect(ftpConf.getHostName(), ftpConf.getPort()); // 连接ftp服务器
                ftpClient.login(ftpConf.getUserName(), ftpConf.getPassword()); // 登录ftp服务器
                int replyCode = ftpClient.getReplyCode(); // 是否成功登录服务器

                if (!FTPReply.isPositiveCompletion(replyCode)) {
                    LOG.info(MessageFormat.format("FTP服务器登录失败, 地址->{0}:{1}", ftpConf.getHostName(), ftpConf.getPort()));
                    return null;
                }

                LOG.info(MessageFormat.format("FTP服务器登录成功, 地址->{0}:{1}", ftpConf.getHostName(), ftpConf.getPort()));

                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                //设置Linux环境:如果ftp服务器部署在linux系统中，此处注释应该打开，若为Windows服务器则不需要
                /*FTPClientConfig conf = new FTPClientConfig("UNIX");
                ftpClient.configure(conf);*/

                ftpClient.enterLocalPassiveMode(); // 防止假卡死
                ftpClient.setRemoteVerificationEnabled(false); // 是否远程校验连接的主机和控制的主机是否相同
                ftpClient.setConnectTimeout(10 * 1000); // 登录十秒超时
                ftpClient.setDataTimeout(1 * 60 * 1000); // 获取数据超时 一分钟
                ftpClient.setReceiveBufferSize(1024 * 1024);
                ftpClient.setBufferSize(1024 * 1024);
            } catch (Exception e) {
                LOG.error("获取FTP客户端失败, 异常->", e);
                return null;
            }

            return ftpClient;
        }

        @Override
        public PooledObject<FTPClient> wrap(FTPClient ftpClient) {
            return new DefaultPooledObject<FTPClient>(ftpClient);
        }

        @Override
        public void destroyObject(PooledObject<FTPClient> p) throws Exception {
            FTPClient ftpClient = p.getObject();
            ftpClient.logout();
            ftpClient.disconnect();
            super.destroyObject(p);
        }

        @Override
        public boolean validateObject(PooledObject<FTPClient> p) {

            FTPClient ftpClient = p.getObject();
            boolean isConnect = false;
            try {
                isConnect = ftpClient.sendNoOp();
            } catch (FTPConnectionClosedException e) {
                LOG.error("校验FTP连接发生FTPConnectionClosedException", e);
            } catch (IOException e) {
                LOG.error("校验FTP连接发生IOException", e);
            }
            return isConnect;
        }

        @Override
        public void activateObject(PooledObject<FTPClient> p) throws Exception {
        }

        @Override
        public void passivateObject(PooledObject<FTPClient> p) throws Exception {
        }
    }

    /**
     * FTP客户端池常量
     */
    interface FTPClientPoolConstants {

        /**
         * 默认池中最大数量
         */
        public static final int DEFAULT_MAX_TOTAL = 10;

        /**
         * 默认最大等待毫秒数
         */
        public static final long DEFAULT_MAX_WAIT_MILLIS = 20000L;

        /**
         * 默认最大空闲数
         */
        public static final int DEFAULT_MAX_IDLE = 10;

        /**
         * 默认最小空闲数
         */
        public static final int DEFAULT_MIN_IDLE = 2;

    }

    /*private static void testShow(FTPClient ftpClient, String path) {
        try {
            ftpClient.changeWorkingDirectory(path);
            for (String fileName : ftpClient.listNames()) {
                System.out.println(fileName);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    
        FTPClientPool pool = new FTPClientPool();
        FTPClientConfiguration ftpConf1 = new FTPClientConfiguration();
        ftpConf1.setHostName("10.27.97.65");
        ftpConf1.setPort(21);
        ftpConf1.setUserName("ftp_erp_w");
        ftpConf1.setPassword("ftp_erp_w");
        ftpConf1.setControlEncoding("utf-8");
    
        FTPClientConfiguration ftpConf2 = new FTPClientConfiguration();
        ftpConf2.setHostName("ossftpsit.cnsuning.com");
        ftpConf2.setPort(21);
        ftpConf2.setUserName("saposs/r3/saposs");
        ftpConf2.setPassword("Sn@12345");
        ftpConf2.setControlEncoding("utf-8");
    
        FTPClient ftpClient1 = null;
        FTPClient ftpClient2 = null;
        try {
            ftpClient1 = pool.borrowFtpClient(ftpConf1);
            ftpClient2 = pool.borrowFtpClient(ftpConf2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        System.out.println("\nclient1 --->");
        testShow(ftpClient1, "/test");
        System.out.println("\nclient2 --->");
        testShow(ftpClient2, "20181115");
        System.out.println("\nclient1 --->");
        testShow(ftpClient1, "/test");
    
        pool.returnFtpClient(ftpClient1);
        System.out.println("\nreturn client1 --->");
        testShow(ftpClient1, "/poispider");
    }*/
}
