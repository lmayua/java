package ext.bigdata.es;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder.Operator;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;

import com.google.common.base.Strings;

/**
 * QueryBuilder生成类<br/>
 * <br/>
 * 1.boolQuery <br/>
 * 2.termQuery <br/>
 * 3.matchQuery <br/>
 * 4.multiMatchQuery <br/>
 * 5.matchPhraseQuery <br/>
 * 6.fuzzyQuery <br/>
 * 7.wildcardQuery <br/>
 * 8.regexpQuery <br/>
 * 9.queryStringQuery <br/>
 * 10.simpleQueryStringQuery <br/>
 * 11.functionScoreQuery <br/>
 */
public class QueryBuilderCreator {
    //@formatter:off
    
    /**
     * 多条件查询
     * 
     * filter,must(AND),mustNot(NOT),should(OR)
     */
    public static BoolQueryBuilder getBoolQueryBuilder(){
        return QueryBuilders.boolQuery()
                                .adjustPureNegative(true) // 默认true，只包含否定"must not"子句，使用@{MatchAllDocsQuery}增强查询
                                .disableCoord(false)// 默认false
                                //.filter(qb)  // 过滤条件
                                //.must(qb) // 等于条件
                                //.mustNot(qb) // 不等于条件
                                //.should(qb) // 或者条件
                                ;
    }
    
    /**
     * 精确搜索
     * 
     * 如果字段设置了analyzed，查询可能会受到解析器影响而搜索不出结果
     * 所以字段不需要分词，设置not_analyzed
     * (不支持analyzer)
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return QueryBuilder
     */
    public static QueryBuilder getTermQuery(String fieldName, Object fieldValue) {
        return QueryBuilders.termQuery(fieldName, fieldValue);
    }

    /**
     * 多字段匹配查询
     * GET /${index}/${type}?query=${text}
     * 
     * @param text 匹配文本
     * @param analyzer 解析器
     * @param fieldNames 一个或者多个字段名
     * @return
     */
    public static QueryBuilder getMutilMatchQuery(String text, String analyzer, String... fieldNames) {
        MultiMatchQueryBuilder mmQb = QueryBuilders.multiMatchQuery(text, fieldNames);
        if (!Strings.isNullOrEmpty(analyzer)) {
            mmQb = mmQb.analyzer(analyzer);
        }
        return mmQb;
    }

    /**
     * 单字段匹配查询
     *
     * @param text 匹配文本
     * @param fieldName 字段名
     * @param analyzer 解析器
     * @return
     */
    public static QueryBuilder getMatchQuery(String text, String fieldName, String analyzer) {
        MatchQueryBuilder mQb = QueryBuilders.matchQuery(fieldName, text)
                                                //.fuzziness(Fuzziness.AUTO)  // 设置纠正拼写错误的容忍范围，0,1,2,auto
                                                ;
        if (!Strings.isNullOrEmpty(analyzer)) {
            mQb = mQb.analyzer(analyzer);
        }
        return mQb;
    }
    
    /**
     * 短语匹配查询
     * 传入的文本不会被分词器分词，以一个独立的短语查询
     * 
     * 例如:有两个document，一个有值"love me, love my dog"，另一个值"the dog love me"
     * 使用"dog love me"查询，matchQuery会查询两个document，而matchPhraseQuery只会查出第二个document
     * 因为matchPhraseQuery会把查询的值作为一个独立的短语，第一个document不存在该短语
     *
     * @param fieldName 字段名
     * @param text 短语
     * @param analyzer 分析器
     * @return
     */
    public static QueryBuilder getMatchParseQuery(String fieldName, String text, String analyzer){
        MatchQueryBuilder mQb = QueryBuilders.matchPhraseQuery(fieldName, text)
                                                 //.cutoffFrequency(0.01f)    // 设置高频与低频分界值
                                                 //.fuzziness(Fuzziness.TWO)  // 设置纠正拼写错误的容忍范围，0,1,2,auto
                                                 ;
        
        if (!Strings.isNullOrEmpty(analyzer)) {
            mQb = mQb.analyzer(analyzer);
        }
        
        return mQb;
            
    }
    
    /**
     * 模糊查询
     *
     * @param fieldName 字段名
     * @param fieldValue 字段值
     * @return 
     */
    public static QueryBuilder getFuzzyQuery(String fieldName, String fieldValue) {
        return QueryBuilders.fuzzyQuery(fieldName, fieldValue)
                                //.fuzziness(Fuzziness.AUTO)                 // 设置纠正拼写错误的容忍范围，0,1,2,auto
                                ;
    }
    
    /*public QueryBuilder getBoostingQuery(Map<String, Object> positiveMap, Map<String, Object> negativeMap) {
        return QueryBuilders.boostingQuery()
                                .positive(QueryBuilders.termQuery("", ""))
                                .boost(0.1f)
                                .negative(QueryBuilders.termQuery("", ""))
                                .negativeBoost(0.1f)
                                ;
    }*/
    
    /**
     * 通配符搜索
     * 
     * 通配符:
     *       '*':一个或多个字符
     *       '?':一个字符
     *       
     * @param fieldName 字段名
     * @param wildcardText 包含通配符的值
     * @return
     */
    public static QueryBuilder getWildcardQuery(String fieldName, String wildcardText){
        return QueryBuilders.wildcardQuery(fieldName, wildcardText);
    }
    
    /**
     * 正则查询
     *
     * @param fieldName 字段名
     * @param regexp 正则表达式
     * @return
     */
    public static QueryBuilder getRegexpQuery(String fieldName, String regexp){
        return QueryBuilders.regexpQuery(fieldName, regexp)
                                // .flags(RegexpFlag.ALL)     // 默认ALL
                                ;
    }
    
    /**
     * 分词查询
     * 查询字符串支持连接符(OR AND)、通配符(*?+-":等)
     * 
     * 连接符: defaultOperator(Operator.OR|AND)
     *       OR: 默认，可能存在一个或者多个，设置为OR时，空格等价OR
     *       AND: 都包含
     * 
     * 通配符: allowLeadingWildcard(true) 默认true
     *       "+":包含, "-":不包含;
     *          e.g: document1:"love me, love my dog"，document2："the dog love me"
     *               +\"love me\" -the, 表示包含love me不包含the
     *       "*":一个或者多个字符, "?":一个字符
     *       双引号:表示其内的文本作为一个独立的短语，否则使用分词器分词
     *       ":":键值符  e.g: city:南京，表示搜索键为city，值为南京的document 
     * @param queryString 查询文本
     * @param analyzer 分析器
     * @return
     */
    public static QueryBuilder getQueryStringQuery(String queryString, String analyzer){
        QueryStringQueryBuilder qsQb = QueryBuilders.queryStringQuery(queryString)
                                                        .analyzeWildcard(true) // true:支持通配符(*?)和前缀搜索;反之不支持
                                                        .autoGeneratePhraseQueries(false) // true:以空格分割分词(通用性不高);false:以双引号分割分词(\\"\\")
                                                        //.enablePositionIncrements(true) //自查询的结果中显示位置增量;默认true
                                                        .defaultOperator(Operator.OR) // 连接符(OR AND)，默认OR
                                                        .allowLeadingWildcard(true) // 默认true，表示支持通配符
                                                        //.escape(true)
                                                        ;
        if (!Strings.isNullOrEmpty(analyzer)) {
            qsQb = qsQb.analyzer(analyzer);
        }
        return qsQb;
    }
    
    /**
     * 简单分词查询
     * 精度低，只是会查询差不多的，满足其中一个就会查询出出来，可能会查出多个不相关的结果
     *
     * @param queryString 查询文本
     * @param analyzer 分析器
     * @return
     */
    public static QueryBuilder getSimpleQueryStringQuery(String queryString, String analyzer) {
        SimpleQueryStringBuilder sqsQb = QueryBuilders.simpleQueryStringQuery(queryString)
                                                        .analyzeWildcard(true)
                                                        ;
        if (!Strings.isNullOrEmpty(analyzer)) {
            sqsQb = sqsQb.analyzer(analyzer);
        }
        
        return sqsQb;
    }
    
    /**
     * 范围查询
     *
     * @param fieldName 字段名
     * @param from 范围起始值
     * @param to 范围终止值
     * @param format 字段格式化
     * @return
     */
    public static QueryBuilder getRangeQueryBuilder(String fieldName, Object from, Object to, String format){
        RangeQueryBuilder rQb = QueryBuilders.rangeQuery(fieldName)
                                                .format(format)
                                                .from(from)
                                                .to(to)
                                                ;
        
        return rQb;
    }
    
    /**
     * 自定义评分脚本查询
     *
     * @param bQb
     * @param script
     * @return
     */
    public static QueryBuilder getFunctionScoreQueryBuilder(BoolQueryBuilder bQb, String script){
        return QueryBuilders.functionScoreQuery(bQb)
                                .add(ScoreFunctionBuilders.scriptFunction(script));
    }
    
    //@formatter:on
}
