package ext.antlr4.expr.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SimpleRule {

    private String ruleId; // 规则ID
    
    private String version; // 版本
    
    private String exprContent;// 表达式文本

    private boolean isused; // 是否在使用

    /* get & set method begin */
    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getExprContent() {
        return exprContent;
    }

    public void setExprContent(String exprContent) {
        this.exprContent = exprContent;
    }

    public boolean isIsused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }
    /* get & set method end */
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
