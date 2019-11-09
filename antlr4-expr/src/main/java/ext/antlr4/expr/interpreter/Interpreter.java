package ext.antlr4.expr.interpreter;

import org.antlr.v4.runtime.ParserRuleContext;

public interface Interpreter<T> {

    /**
     * 解析节点
     *
     * @param ctx
     * @return
     */
    public T interpreter(ParserRuleContext ctx);
}
