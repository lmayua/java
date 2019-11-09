package ext.antlr4.expr.common;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ext.antlr4.expr.ExprEngine;

public class MutilRule {

    private String content;

    private List<MutilSubRule> subRules;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MutilSubRule> getSubRules() {
        return subRules;
    }

    public void setSubRules(List<MutilSubRule> subRules) {
        this.subRules = subRules;
    }

    public Object calculate(List<Var> vars) {
        return ExprEngine.getInstance().eval(this.content, vars);
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
