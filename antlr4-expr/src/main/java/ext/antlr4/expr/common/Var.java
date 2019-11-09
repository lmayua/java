package ext.antlr4.expr.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Var {

    public static final Class<?> STRING = String.class; 
    public static final Class<?> NUMBER = Number.class;
    public static final Class<?> BOOLEAN = Boolean.class;
    
    private String name;
    private Object value;
    private Class<?> type;

    /**
     * <default constructor>
     * @param name
     * @param value
     * @param type
     */
    public Var(String name, Object value, Class<?> type) {
        super();
        this.name = name;
        this.value = value;
        this.type = type;
    }
    
    /**
     * <default constructor>
     * @param name
     * @param value
     */
    public Var(String name, Object value) {
        this(name, value, STRING);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class<?> getType() {
        return null == type ? getTypeByValue() : type;
    }

    public Class<?> getTypeByValue() {
        return null != value ? value.getClass() : Null.class;
    }
    
    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
