package ext.antlr4.expr;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import ext.antlr4.expr.common.SimpleRule;
import ext.antlr4.expr.common.Var;
import ext.antlr4.expr.exception.ExprEvalException;

public class ExprCacheEngine {

    /**
     * 规则引擎实例
     */
    private static ExprCacheEngine instance = null;

    /**
     * 规则ID-表达式对象映射
     */
    private static final ConcurrentHashMap<String, SimpleRule> EXP_CACHE = new ConcurrentHashMap<>();

    /**
     * <default constructor>
     */
    private ExprCacheEngine() {
        super();
    }

    /**
     * 获取实例
     */
    public static ExprCacheEngine getInstance() {
        if (null == instance) {
            instance = new ExprCacheEngine();
        }

        return instance;
    }

    /**
     * 根据规则ID计算结果
     *
     * @param ruleId 规则ID
     * @param paramsMap 参数集合
     * @return Object 计算结果
     */
    public Object calculate(String ruleId, List<Var> varList) {

        if (EXP_CACHE.contains(ruleId)) {
            SimpleRule rule = EXP_CACHE.get(ruleId);
            return ExprEngine.getInstance().eval(rule.getExprContent(), varList);
        } else {
            throw new ExprEvalException("not exist rule id -> " + ruleId);
        }
    }

    /**
     * 刷新规则缓存
     *
     * @param ruleList
     * @return boolean 规则是否都成功刷入
     */
    public boolean refreshRulesCache(List<SimpleRule> ruleList) {

        if (null == ruleList || ruleList.isEmpty()) {
            return false;
        }

        boolean isSuccess = true;
        for (int i = 0; i < ruleList.size(); i++) {
            SimpleRule rule = ruleList.get(i);
            isSuccess = isSuccess && refreshSingleRule(rule);
        }

        return isSuccess;
    }

    /**
     * 添加单个规则入缓存
     *
     * @param rule 简单规则对象
     */
    public boolean refreshSingleRule(SimpleRule rule) {

        if (null == rule) {
            return false;
        }

        String ruleId = rule.getRuleId();
        String version = rule.getVersion();
        boolean isused = rule.isIsused();

        if (EXP_CACHE.containsKey(ruleId)) {
            SimpleRule simpleRule = EXP_CACHE.get(ruleId);
            String cacheRuleVersion = simpleRule.getVersion();

            if (!isused) {// 是否需要移除
                EXP_CACHE.remove(ruleId);
            } else if (!StringUtils.isEmpty(cacheRuleVersion) && !cacheRuleVersion.equals(version)) { // 是否需要更新
                pushCache(ruleId, rule.getExprContent(), version);
            }
        } else if (isused) {
            pushCache(ruleId, rule.getExprContent(), version);
        }
        return true;
    }

    /**
     * 添加规则入缓存
     *
     * @param ruleId
     * @param exprContent
     * @param version
     */
    private void pushCache(String ruleId, String exprContent, String version) {
        // check expression content
        ExprEngine.getInstance().checkExpr(exprContent);

        SimpleRule rule = new SimpleRule();
        rule.setRuleId(ruleId);
        rule.setVersion(version);
        rule.setExprContent(exprContent);
        rule.setIsused(true);

        EXP_CACHE.put(ruleId, rule);
    }
}
