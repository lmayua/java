package ext.antlr4.expr.optimizer;

import ext.antlr4.expr.entity.RuleBaseVisitor;
import ext.antlr4.expr.entity.RuleParser.MutilMsgExprContext;

public class RuleEvalForMutilVistor extends RuleBaseVisitor<Object> {

    
    @Override
    public Object visitParse(ext.antlr4.expr.entity.RuleParser.ParseContext ctx) {
        
        
        return null;   
    }
    
    @Override
    public Object visitMutilMsgExpr(MutilMsgExprContext ctx) {
        
        return ctx.getText();
    }
}
