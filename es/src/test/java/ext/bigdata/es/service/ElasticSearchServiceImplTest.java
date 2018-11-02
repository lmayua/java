package ext.bigdata.es.service;

import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Before;
import org.junit.Test;

import ext.bigdata.es.IElasticSearchService;
import ext.bigdata.es.QueryBuilderCreator;

public class ElasticSearchServiceImplTest {
    
    IElasticSearchService ess;
            
    @Before
    public void init(){
        try {
            ess = new ElasticSearchServiceImpl("10.27.114.186", 9300);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void createIndexTest(){
        String settingAndMappingJson = "{'settings':{'index.number_of_shards':2,'index.number_of_replicas':0,'index.max_result_window':'50000','refresh_interval':'10s'},'mappings':{'typedemo':{'_all':{'enabled':false},'dynamic':'strict','properties':{'key1':{'type':'string','index':'not_analyzed'},'key2':{'type':'string','index':'analyzed'}}}}}";
        ess.createIndex("temp", settingAndMappingJson);
    }
    
    @Test
    public void queryTest(){
        String index = "indexdemo";
        String type = "typedemo";
        int fromSize = 0;
        int querySize = 10;
        QueryBuilder qb = QueryBuilderCreator.getBoolQueryBuilder()
                                                .must(QueryBuilderCreator.getMatchParseQuery("key2", "give a hand", null))
                                                .mustNot(QueryBuilderCreator.getQueryStringQuery("four", null))
                                                ;
        SearchResponse sr = ess.query(index, type, qb, fromSize, querySize);
        System.out.println(sr);
    }
    
    
}
