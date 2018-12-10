package ext.ftp;

import java.util.Objects;

/**
 * FTP客户端配置信息类
 */
public class FTPClientConfiguration {
    
    /**
     * 主机名/IP
     */
    private String hostName;
    
    /**
     * 端口
     */
    private int port;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 编码格式
     */
    private String controlEncoding;

    /** get & set method begin */
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getControlEncoding() {
        return controlEncoding;
    }

    public void setControlEncoding(String controlEncoding) {
        this.controlEncoding = controlEncoding;
    }
    /** get & set method end */

    @Override
    public int hashCode() {
        return Objects.hashCode(this.hostName) ^ Objects.hashCode(this.port) ^ Objects.hashCode(this.userName)
                ^ Objects.hash(this.password);
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof FTPClientConfiguration) {
            FTPClientConfiguration conf = (FTPClientConfiguration) obj;
            String confHostName = conf.getHostName();
            int confPort = conf.getPort();
            String confUserName = conf.getUserName();
            String confPassword = conf.getPassword();

            return (null != confHostName) && confHostName.equals(this.hostName) && confPort == this.port
                    && null != confUserName && confUserName.equals(this.userName) && null != confPassword
                    && confPassword.equals(this.password);
        }
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FTPClientConfiguration [hostName=");
        builder.append(hostName);
        builder.append(", port=");
        builder.append(port);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", password=");
        builder.append(password);
        builder.append(", controlEncoding=");
        builder.append(controlEncoding);
        builder.append("]");
        return builder.toString();
    }
}