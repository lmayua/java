package ext.bigdata.es.service;

import java.net.UnknownHostException;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;

import com.alibaba.fastjson.JSONObject;

public class ElasticSearchServiceImpl extends AbstractEsService {

    public ElasticSearchServiceImpl(String ip, int port) throws UnknownHostException {
        super(ip, port);
    }

    @Override
    public boolean createDocument(String index, String type, String id, String sourceJson) {
        IndexResponse ir = client.prepareIndex(index, type, id).setSource(sourceJson)
                .get(TimeValue.timeValueMillis(QUERY_DEFAULT_TIMEOUT));
        return ir.getShardInfo().getSuccessful() > 0;
    }

    @Override
    public boolean createIndex(String index, String settingAndMappingJson) {
        JSONObject settingJsonObj = JSONObject.parseObject(settingAndMappingJson).getJSONObject("settings");
        JSONObject mappingJsonObj = JSONObject.parseObject(settingAndMappingJson).getJSONObject("mappings");

        String settingJson = settingJsonObj.toJSONString();
        String type = mappingJsonObj.keySet().iterator().next();
        String mappingJson = mappingJsonObj.getJSONObject(type).toJSONString();

        //@formatter:off
        CreateIndexResponse cir = client.admin().indices()
                                        .prepareCreate(index)
                                        .setSettings(settingJson)
                                        .addMapping(type, mappingJson)
                                        .get(TimeValue.timeValueMillis(CRT_DEL_DEFAULT_TIMEOUT))
                                        ;
        return cir.isAcknowledged();
        //@formatter:on
    }

    @Override
    public SearchResponse query(String index, String type, QueryBuilder qb, int fromSize, int querySize) {
        //@formatter:off
        return client.prepareSearch(index)
                        .setQuery(qb)
                        .setFrom(fromSize)
                        .setSize(querySize)
                        //.addAggregation(AggregationBuilders.)
                        //.addSort(SortBuilders.)
                        .setSearchType(SearchType.QUERY_THEN_FETCH)
                        .get(TimeValue.timeValueMillis(QUERY_DEFAULT_TIMEOUT))
                        ;
       //@formatter:on
    }

    @Override
    public boolean deleteDocument(String index, String type, String id) {
        DeleteResponse dr = client.prepareDelete(index, type, id)
                .get(TimeValue.timeValueMillis(CRT_DEL_DEFAULT_TIMEOUT));
        return dr.getShardInfo().getSuccessful() > 0;
    }

    @Override
    public boolean deleteIndex(String... indices) {
        boolean isSuccess = true;

        for (String index : indices) {
            DeleteIndexResponse dir = client.admin().indices().prepareDelete(index).get(TimeValue.timeValueMillis(CRT_DEL_DEFAULT_TIMEOUT));
            if (!dir.isAcknowledged()) {
                isSuccess = false;
                // TODO LOG
            }
        }
        return isSuccess;
    }
    
    @Override
    public boolean deleteType(String index, String sourceJson, String ...types) {
        DeleteByQueryResponse dbqr = new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
                                         .setIndices(index)
                                         .setTypes(types)
                                         .setSource(sourceJson)
                                         .get();
        System.out.println(dbqr);
        return true;
    }
}
