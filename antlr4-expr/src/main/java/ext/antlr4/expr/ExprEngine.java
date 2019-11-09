package ext.antlr4.expr;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import ext.antlr4.expr.common.MutilRule;
import ext.antlr4.expr.common.MutilSubRule;
import ext.antlr4.expr.common.Var;
import ext.antlr4.expr.entity.RuleParser.MutilMsgExprContext;
import ext.antlr4.expr.optimizer.RuleEvalVisitor;
import ext.antlr4.expr.parser.ExprParser;


public class ExprEngine {

    private static ExprEngine instance = null;

    private ExprParser parser = new ExprParser();

    private ExprEngine() {
        super();
    }

    public static ExprEngine getInstance() {
        if (null == instance) {
            instance = new ExprEngine();
        }

        return instance;
    }

    public Object eval(String content) {
        ParseTree tree = parser.parse(content);
        RuleEvalVisitor visitor = new RuleEvalVisitor();
        return visitor.visit(tree);
    }

    public Object eval(String content, List<Var> vars) {
        long start = System.currentTimeMillis();
        ParseTree tree = parser.parse(content);
        System.out.println("parse cost: " + (System.currentTimeMillis() - start));
        RuleEvalVisitor visitor = new RuleEvalVisitor();
        return visitor.visit(tree, vars);
    }

    public MutilRule parseMutilMsg(String content) {
        
        long start = System.currentTimeMillis();
        ParseTree tree = parser.parse(content);
        System.out.println("parse cost: " + (System.currentTimeMillis() - start));
        return convertMutilRule(tree);
    }
    
    private MutilRule convertMutilRule(ParseTree tree) {
        List<MutilSubRule> subRules = new ArrayList<>();
        convertMutilVars(subRules, tree);
        
        long start = System.currentTimeMillis();
        String content = tree.getText().replace("$[", "").replace("]", "");
        for (int i = 0;i < subRules.size();i++){
            MutilSubRule subRule = subRules.get(i);
            content = content.replace(subRule.getSubContent(), subRule.getSubId());
        }
        System.out.println("tree -> subRules cost: " + (System.currentTimeMillis() - start));
        
        MutilRule mRule = new MutilRule();
        mRule.setContent(content);
        mRule.setSubRules(subRules);
        return mRule;
    }
    
    private void convertMutilVars(List<MutilSubRule> subRules, ParseTree tree) {
        
        for (int i = 0;i < tree.getChildCount();i++){
            ParseTree childTree = tree.getChild(i);
            if (childTree instanceof MutilMsgExprContext) {
                String subId = convertMutilVarKey(subRules.size() + 1);
                String subContent = ((MutilMsgExprContext) childTree).getText().replace("$[", "").replace("]", "");
                
                subRules.add(new MutilSubRule(subId, subContent));
                continue;
            }
            
            if (childTree.getChildCount() > 0){
                convertMutilVars(subRules, childTree);
            }
        }
    }
    
    private String convertMutilVarKey(int index){
        return "_r" + new DecimalFormat("000").format(index) + "_";
    }
    
    public void checkExpr(String content) {
        parser.checkExpr(content);
    }
}
