package ext.bigdata.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;

public interface IElasticSearchService {
    
    /**
     * 创建Document
     *
     * @param index 索引
     * @param type 类型
     * @param id ID
     * @return
     */
    public boolean createDocument(String index, String type, String id, String sourceJson);
    
    /**
     * 创建Index
     *
     * @param index 索引
     * @param settingAndMappingJson settings和mappings配置JSON
     * @return
     */
    public boolean createIndex(String index, String settingAndMappingJson);
    
    /**
     * 通过条件查询
     *
     * @param index 索引
     * @param type 类型
     * @param qb 查询条件
     * @param fromSize  起始位置
     * @param querySize 查询的数据量
     * @return
     */
    public SearchResponse query(String index, String type, QueryBuilder qb, int fromSize, int querySize);
    
    /**
     * 删除Document
     *
     * @param index 索引
     * @param type 类型
     * @param id ID
     * @return
     */
    public boolean deleteDocument(String index, String type, String id);
    
    /**
     * 删除一个或者多个Index
     *
     * @param indices 一个或者多个Index
     * @return
     */
    public boolean deleteIndex(String ...indices);
    
    /**
     * 删除指定Index的多个Type的数据
     *
     * @param index 索引
     * @param sourceJson 删除条件，e.g:全部删除-->"{\"query\": {\"match_all\": {}}}"
     * @param types 一个或者多个类型
     * @return
     */
    public boolean deleteType(String index, String sourceJson, String ...types);
}
