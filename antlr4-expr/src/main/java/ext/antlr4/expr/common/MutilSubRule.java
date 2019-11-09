package ext.antlr4.expr.common;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ext.antlr4.expr.ExprEngine;

public class MutilSubRule {

    private String subId;
    private String subContent;

    public MutilSubRule(String subId, String subContent) {
        super();
        this.subId = subId;
        this.subContent = subContent;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public Object calculate(List<Var> vars) {
        return ExprEngine.getInstance().eval(this.subContent, vars);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
