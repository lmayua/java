package ext.ftp;

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