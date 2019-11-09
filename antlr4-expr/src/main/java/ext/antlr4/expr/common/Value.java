package ext.antlr4.expr.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Value {
    private Object val;
    private Class<?> type;
    
    public Value(Object val) {
        super();
        if (null != val){
            this.val = val;
            this.type = val.getClass();
        } else {
            this.val = new Null();
            this.type = Null.class;
        }
    }
    
    public Object getVal() {
        return val;
    }
    public void setVal(Object val) {
        this.val = val;
    }
    public Class<?> getType() {
        return type;
    }
    public void setType(Class<?> type) {
        this.type = type;
    }
    
    
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
