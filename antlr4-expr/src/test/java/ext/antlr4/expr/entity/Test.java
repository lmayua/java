package ext.antlr4.expr.entity;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.FelContext;

public class Test {

    @org.junit.Test
    public void test(){
        String expr = "a+20+b";
        
        FelContext ctx = FelEngine.instance.getContext();
        ctx.set("a", 10);
        ctx.set("b", 30);
        
        Expression expressoin = FelEngine.instance.compile(expr, ctx);
        
        System.out.println(expressoin.eval(ctx));
        
    }
    
    @org.junit.Test
    public void test2(){
        System.out.println(FelEngine.instance.eval("I==I||I=='F'"));
    }
}
