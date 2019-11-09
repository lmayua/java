package ext.antlr4.expr.optimizer;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import ext.antlr4.expr.common.NumberUtil;
import ext.antlr4.expr.common.Var;
import ext.antlr4.expr.entity.RuleBaseVisitor;
import ext.antlr4.expr.entity.RuleParser;
import ext.antlr4.expr.entity.RuleParser.AdditiveExprContext;
import ext.antlr4.expr.entity.RuleParser.AndExprContext;
import ext.antlr4.expr.entity.RuleParser.AtomExprContext;
import ext.antlr4.expr.entity.RuleParser.BooleanAtomContext;
import ext.antlr4.expr.entity.RuleParser.ChildExprContext;
import ext.antlr4.expr.entity.RuleParser.DictAtomContext;
import ext.antlr4.expr.entity.RuleParser.EqualityExprContext;
import ext.antlr4.expr.entity.RuleParser.ExprContext;
import ext.antlr4.expr.entity.RuleParser.FloatAtomContext;
import ext.antlr4.expr.entity.RuleParser.IdAtomContext;
import ext.antlr4.expr.entity.RuleParser.IfExprContext;
import ext.antlr4.expr.entity.RuleParser.InExprContext;
import ext.antlr4.expr.entity.RuleParser.IntAtomContext;
import ext.antlr4.expr.entity.RuleParser.LikeExprContext;
import ext.antlr4.expr.entity.RuleParser.MultiplicationExprContext;
import ext.antlr4.expr.entity.RuleParser.MutilMsgExprContext;
import ext.antlr4.expr.entity.RuleParser.OrExprContext;
import ext.antlr4.expr.entity.RuleParser.ParseContext;
import ext.antlr4.expr.entity.RuleParser.RelationalExprContext;
import ext.antlr4.expr.entity.RuleParser.StringAtomContext;
import ext.antlr4.expr.exception.ExprCompileException;
import ext.antlr4.expr.exception.ExprEvalException;

public class RuleEvalVisitor extends RuleBaseVisitor<Object> {

    private Map<String, Var> varMap = new HashMap<>();
    
    public Object visit(ParseTree tree, List<Var> varList) {
        
        if (null != varList && !varList.isEmpty()){
            for (int i = 0; i < varList.size(); i++) {
                Var var = varList.get(i);
                varMap.put(addSign(var.getName()), var);
            }
        }
        
        return super.visit(tree);
    }
    
    private String addSign(String key){
        return "_" + key + "_";
    }
    
    @Override
    public Object visitParse(ParseContext ctx) {
        return super.visitParse(ctx);
    }

    @Override
    public Object visitChildExpr(ChildExprContext ctx) {
        return this.visit(ctx.expr());
    }
    
    @Override
    public Object visitMutilMsgExpr(MutilMsgExprContext ctx) {
        throw new ExprEvalException("can't eval multilate message expression");
    }
    
    @Override
    public Object visitIfExpr(IfExprContext ctx) {

        List<ParseTree> trees = ctx.children;

        for (int i = 0; i < trees.size();) {
            ParseTree tree = trees.get(i);

            if (tree instanceof TerminalNodeImpl) {
                int type = ((TerminalNodeImpl) tree).getSymbol().getType();
                if (RuleParser.IF == type) {
                    ParseTree conTree = trees.get(i += 2);
                    Object conVal = this.visit(conTree);

                    if (!(conVal instanceof Boolean)) {
                        throw new ExprEvalException("'ifExpr' condiction isn't 'Boolean' type");
                    }

                    if ((Boolean) conVal) {
                        return this.visit(trees.get(i += 3));
                    } else {
                        i += 5;
                    }
                } else if (RuleParser.ELSEIF == type) {
                    Object conVal = this.visit(trees.get(i += 2));

                    if (!(conVal instanceof Boolean)) {
                        throw new ExprEvalException("'ifExpr' condiction isn't 'Boolean' type");
                    }

                    if ((Boolean) conVal) {
                        return this.visit(trees.get(i += 3));
                    } else {
                        i += 5;
                    }
                } else if (RuleParser.ELSE == type) {
                    return this.visit(trees.get(i += 2));
                }
            }
        }

        return super.visitIfExpr(ctx);
    }
    
    @Override
    public Object visitInExpr(InExprContext ctx) {
        
        int opType = ctx.op.getType();
        
        List<ExprContext> exprs = ctx.expr();
        String left = String.valueOf(this.visit(exprs.get(0)));
        boolean result = false;
        for (int i = 1;i < exprs.size();i++){
            if (left.equals(String.valueOf(this.visit(exprs.get(i))))){
                result = true;
            }
        }
        
        return (opType == RuleParser.IN) ? result : !result;
    }
    
    @Override
    public Object visitLikeExpr(LikeExprContext ctx) {
        
        Object left = this.visit(ctx.expr(0));
        Object right = this.visit(ctx.expr(1));
        
        // contain
        if (ctx.MOD().size() == 2){
            return String.valueOf(left).contains(String.valueOf(right));
        }
        
        List<ParseTree> children = ctx.children;

        if ("%".equals(children.get(2).getText())){
            return String.valueOf(left).endsWith(String.valueOf(right));
        } else {
            return String.valueOf(left).startsWith(String.valueOf(right));
        }
    }
    
    @Override
    public Object visitMultiplicationExpr(MultiplicationExprContext ctx) {

        Object left = this.visit(ctx.expr(0));
        Object right = this.visit(ctx.expr(1));

        // TODO move to NumberUtil
        if (left instanceof String){
            left = NumberUtil.parseNumber((String)left);
        } 
        
        if (right instanceof String){
            right = NumberUtil.parseNumber((String)right);
        }
        
        if (!(left instanceof Number) || !(right instanceof Number)){
            throw new ExprEvalException("'multiplicationExpr' with 'non-number' type");
        }
        
        Double result;
        switch (ctx.op.getType()) {
            case RuleParser.MULT: // *
                result = toDouble((Number)left) * toDouble((Number)right);
                break;
            case RuleParser.DIV: // /
                result = toDouble((Number)left) / toDouble((Number)right);
                break;
            case RuleParser.MOD: // %
                result = toDouble((Number)left) % toDouble((Number)right);
                break;
            default:
                throw new ExprEvalException("no such element");
        }
        
        return NumberUtil.parseNumber(result);
    }

    @Override
    public Object visitAdditiveExpr(AdditiveExprContext ctx) {
        System.out.println("visit additive");
        Object left = this.visit(ctx.expr(0));
        Object right = this.visit(ctx.expr(1));

        // TODO move to NumberUtil
        if (left instanceof String){
            left = NumberUtil.parseNumber((String)left);
        } 
        
        if (right instanceof String){
            right = NumberUtil.parseNumber((String)right);
        }
        
        Object val;
        if (left instanceof Number && right instanceof Number) {
            val = evalAdditive(toDouble((Number) left), toDouble((Number) right), ctx);
        } else {
            throw new ExprCompileException(
                    MessageFormat.format("additive expression exists non-number type, expr -> {0}", ctx.getText()));
        }

        return val;
    }

    private double toDouble(Number number) {
        // Float直接转Double存在精度问题，需要parseDouble方法转换
        if (number instanceof Float) {
            return Double.parseDouble(number.toString());
        }

        return number.doubleValue();
    }

    private Object evalAdditive(double left, double right, AdditiveExprContext ctx) {
        double result;
        switch (ctx.op.getType()) {
            case RuleParser.PLUS:
                result = left + right;
                break;
            case RuleParser.MINUS:
                result = left - right;
                break;
            default:
                result = 0.0;
                break;
        }

        return NumberUtil.parseNumber(result);
    }

    @Override
    public Object visitRelationalExpr(RelationalExprContext ctx) {

        Object left = this.visit(ctx.expr(0));
        Object right = this.visit(ctx.expr(1));

        // TODO move to NumberUtil
        if (left instanceof String){
            left = NumberUtil.parseNumber((String)left);
        } 
        
        if (right instanceof String){
            right = NumberUtil.parseNumber((String)right);
        }
        
        if (!(left instanceof Number && right instanceof Number)) {
            throw new ExprEvalException("'relationExpr' exists non-number type");
        }

        // TODO 不支持科学计数法Float
        switch (ctx.op.getType()) {
            case RuleParser.LT: // <
                return toDouble((Number) left) < toDouble((Number) right);
            case RuleParser.LTEQ: // <=
                return toDouble((Number) left) <= toDouble((Number) right);
            case RuleParser.GT: // >
                return toDouble((Number) left) > toDouble((Number) right);
            case RuleParser.GTEQ: // >=
                return toDouble((Number) left) >= toDouble((Number) right);
            default:
                throw new ExprEvalException("'relationExpr' with error type");
        }
    }

    @Override
    public Object visitEqualityExpr(EqualityExprContext ctx) {
        Object left = this.visit(ctx.expr(0));
        Object right = this.visit(ctx.expr(1));
        // Boolean String Number(some)->1.float 支持科学计数法(10e1234||10E1234||1.23123)

        int opType = ctx.op.getType();
        
        if (RuleParser.EQ == opType){
            return compare(left, right);
        } else if (RuleParser.NEQ == opType){
            return !compare(left, right);
        } else {
            throw new ExprEvalException("'equalityExpr' no such element");
        }
        
    }
    
    private boolean compare(Object left, Object right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.getClass().equals(right.getClass())) {
            return left.equals(right);
        } else if (left instanceof String || right instanceof String) {
            return left.toString().equals(right.toString());
        }

       /* if (left instanceof BigDecimal || right instanceof BigDecimal) {// BigDecimal ?
            
            return NumberUtil.toBigDecimal(left).compareTo(NumberUtil.toBigDecimal(right)) == 0;
        } else if (NumberUtil.isFloatingPointNumber(left) || NumberUtil.isFloatingPointNumber(right)) { // Float支持科学计数法
            
            return NumberUtil.toDouble(left) == NumberUtil.toDouble(right);
        } else if (left instanceof Number || right instanceof Number || left instanceof Character
                || right instanceof Character) {
            
            return NumberUtil.toLong(left) == NumberUtil.toLong(right);
        } else if (left instanceof Boolean || right instanceof Boolean) {
            
            return NumberUtil.toBoolean(left) == NumberUtil.toBoolean(right);
        } else if (left instanceof String || right instanceof String) {
            
            return left.toString().equals(right.toString());
        }*/
        return left.equals(right);
    }

    @Override
    public Object visitAndExpr(AndExprContext ctx) {
        Object left = this.visit(ctx.expr(0));
        Object right = this.visit(ctx.expr(1));
        
        // TODO
        if (left instanceof Boolean && right instanceof Boolean){
            return (Boolean)left && (Boolean)right;
        } else if (left instanceof String || right instanceof String) {
            return Boolean.valueOf(String.valueOf(left)) && Boolean.valueOf(String.valueOf(right));
        } else {
            throw new ExprEvalException("expression['&&'] exists non-boolean type");
        }
    }
    
    @Override
    public Object visitOrExpr(OrExprContext ctx) {
        Object left = this.visit(ctx.expr(0));
        Object right = this.visit(ctx.expr(1));
        
        if (left instanceof Boolean && right instanceof Boolean){
            return (Boolean)left || (Boolean)right;
        } else {
            throw new ExprEvalException("expression['||'] exists non-boolean type");
        }
    }
    
    @Override
    public Object visitAtomExpr(AtomExprContext ctx) {
        Object value = this.visitChildren(ctx);
        return value;
    }

    /* Atomic -> dictionary */
    @Override
    public Object visitDictAtom(DictAtomContext ctx) {
        Var var = varMap.get(ctx.getText());
        
        if (null == var){
            throw new ExprEvalException("'dict' exits none value");
        }
        
        return var.getValue();
    }

    /* Atomic -> number(INT|FLOAT)*/
    @Override
    public Object visitIntAtom(IntAtomContext ctx) {
        String text = ctx.getText();
        if (text.length() > 9 && Long.parseLong(text) > Integer.MAX_VALUE){
            return Long.parseLong(text);
        } else {
            return Integer.parseInt(text);
        }
    }

    @Override
    public Object visitFloatAtom(FloatAtomContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Object visitBooleanAtom(BooleanAtomContext ctx) {
        return Boolean.parseBoolean(ctx.getText());
    }

    /* Atomic -> ID */
    @Override
    public Object visitIdAtom(IdAtomContext ctx) {
        Var var = varMap.get(ctx.getText());
        return null != var ? var.getValue() : ctx.getText();
    }

    @Override
    public Object visitStringAtom(StringAtomContext ctx) {
        return ctx.getText().replace("'", "").replace("\"", "");
    }
}
