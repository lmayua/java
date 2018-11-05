package ext.bigdata.es.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin;

import ext.bigdata.es.IElasticSearchService;

public abstract class AbstractEsService implements IElasticSearchService {

    /**
     * 查询默认超时时间(ms)
     */
    protected static final long QUERY_DEFAULT_TIMEOUT = 5000L;
    
    /**
     * 删除默认超时时间(ms)
     */
    protected static final long CRT_DEL_DEFAULT_TIMEOUT = 5000L;
    
    /**
     * Client
     */
    protected TransportClient client;

    /**
     * <default constructor>
     * @param ip IP地址
     * @param port 端口
     * @throws UnknownHostException
     */
    public AbstractEsService(String ip, int port) throws UnknownHostException {
        // TODO check

        if (null == client) {

            /**
               cluster.name => ElasticSearch cluster name
               client.transport.sniff => auto adding cluster IPs to client
             */
            // TODO read from properties
            Settings settings = Settings.settingsBuilder().put("cluster.name", "RKB")
                    .put("client.transport.sniff", true).build();
            
            client = TransportClient.builder()
                                        .settings(settings)
                                        // 2.x版本之后不支持删除type，只能通过添加plugin方式删除，5.x版本新增删除type API
                                        // 2.x集群添加插件后需要重启集群
                                        .addPlugin(DeleteByQueryPlugin.class)
                                        .build()
                                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByAddress(getByteIp(ip)), port));
        }
    }

    private byte[] getByteIp(String ip) throws UnknownHostException {
        return InetAddress.getByName(ip).getAddress();
    }
}
