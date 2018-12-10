package ext.ftp;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;

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
    //private GenericObjectPool<FTPClient> pool;
    
    /**
     * 
     */
    private ConcurrentHashMap<FTPClientConfiguration, GenericObjectPool<FTPClient>> poolMap;

    /**
     * FTPClient配置信息类
     */
    //protected FTPClientConfiguration ftpConf;
    
    /**
     * 单例对象
     */
    private static FTPClientPool instance;

    /**
     * <default constructor>
     */
    private FTPClientPool() {
        //this.pool = new GenericObjectPool<FTPClient>(new FTPClientPoolFactory(), new GenericObjectPoolConfig<FTPClient>());
        this.poolMap = new ConcurrentHashMap<>();
    }
    
    /**
     * 获取FTP客户端池
     *
     * @return FTPClientPool FTP客户端池对象
     */
    public static FTPClientPool getInstance(){
        if (null == instance){
            instance = new FTPClientPool();
        }
        return instance;
    }

    /**
     * 从FTP客户端池中获取一个FTPClient
     *
     * @param ftpClientConf FTPClient配置信息
     * @return
     * @throws Exception
     */
    public synchronized FTPClient borrowFtpClient(FTPClientConfiguration ftpClientConf) throws Exception {
        GenericObjectPool<FTPClient> pool = null;
        if (poolMap.containsKey(ftpClientConf)){
            pool = poolMap.get(ftpClientConf);
        } else {
            pool = new GenericObjectPool<>(new FTPClientPoolFactory(ftpClientConf), new GenericObjectPoolConfig<FTPClient>());
            poolMap.put(ftpClientConf, pool);
        }
        
        return borrowFtpClient(pool);
    }
    
    /**
     * 获取可用FTP连接
     *
     * @return
     * @throws Exception
     */
    private FTPClient borrowFtpClient(GenericObjectPool<FTPClient> pool) throws Exception {
        FTPClient ftpClient = pool.borrowObject();
        LOG.info(MessageFormat.format("连接池有{0}活动连接，有{1}空闲连接，有{2}借出连接，有{3}归还连接，FTP客户端内存地址:{4}", pool.getNumActive(),
                pool.getNumIdle(), pool.getBorrowedCount(), pool.getReturnedCount(), ftpClient.toString()));
        // 校验联通性，如果连接不上销毁对象重新创建
        if (!checkConnect(ftpClient)) {
            pool.invalidateObject(ftpClient);
            ftpClient = this.borrowFtpClient(pool);
        }

        return ftpClient;
    }

    /**
     * 校验FTP是否联通
     *
     * @param ftpClient FTP客户端
     * @return
     */
    private boolean checkConnect(FTPClient ftpClient) {
        boolean isConnect = false;
        try {
            isConnect = ftpClient.sendNoOp();
            LOG.info("判断是否连接："+ isConnect);
        } catch (FTPConnectionClosedException e) {
            LOG.error("校验FTP连接发生FTPConnectionClosedException", e);
        } catch (IOException e) {
            LOG.error("校验FTP连接发生IOException", e);
        }
        return isConnect;
    }

    /**
     * 归还FTP客户端
     * 
     * @param ftpClient FTP客户端
     */
    public void returnFtpClient(FTPClient ftpClient, FTPClientConfiguration ftpConf) {
        if (poolMap.containsKey(ftpConf)){
            GenericObjectPool<FTPClient> pool = poolMap.get(ftpConf);
            pool.returnObject(ftpClient);
        }
    }

    /**
     * FTP客户端池工厂
     */
    class FTPClientPoolFactory extends BasePooledObjectFactory<FTPClient> {
        FTPClientConfiguration ftpConf;
        
        public FTPClientPoolFactory(FTPClientConfiguration ftpConf) {
            this.ftpConf = ftpConf;
        }
        
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
                ftpClient.setRemoteVerificationEnabled(false);
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
            LOG.info("开始销毁对象");
            FTPClient ftpClient = p.getObject();
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (Exception e) {
                LOG.error("销毁对象失败，异常\n", e);
            }
            super.destroyObject(p);
        }

        @Override
        public boolean validateObject(PooledObject<FTPClient> p) {
            LOG.info("校验对象开始");
            FTPClient ftpClient = p.getObject();
            boolean isConnect = false;
            try {
                isConnect = ftpClient.sendNoOp();
                LOG.info("判断是否连接："+ isConnect);
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
}
