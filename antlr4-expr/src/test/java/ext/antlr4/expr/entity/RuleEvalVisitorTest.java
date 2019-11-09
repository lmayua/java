package ext.antlr4.expr.entity;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import ext.antlr4.expr.ExprEngine;
import ext.antlr4.expr.common.Var;
import ext.antlr4.expr.optimizer.RuleEvalVisitor;
import ext.antlr4.expr.parser.ExprParser;

public class RuleEvalVisitorTest {

    private ExprParser parser = new ExprParser();
    
    @Test
    public void test() {
        String expression = "if(true){100}else{200}";
        CharStream input = CharStreams.fromString(expression);

        RuleLexer lexer = new RuleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RuleParser parser = new RuleParser(tokens);
        
        ParseTree tree = parser.parse();
        System.out.println(tree.toStringTree(parser));

        RuleEvalVisitor visitor = new RuleEvalVisitor();
        List<Var> vars = new ArrayList<>();
        vars.add(new Var("_1000.1001_", 100));
        vars.add(new Var("_1000.1002_", 200));
        
        System.out.println(visitor.visit(tree, vars));
        //System.out.println(visitor.visit(tree));
    }

    @Test
    public void visitIfExprTest() {
        String expression1 = "if (100>20){100} else {200}";
        ParseTree tree1 = parser.parse(expression1);
        
        String expression2 = "if (100<20){100} else if (100>20){200} else {300}";
        ParseTree tree2 = parser.parse(expression2);
        
        RuleEvalVisitor visitor = new RuleEvalVisitor();
        //System.out.println(visitor.visit(tree1));
        System.out.println(visitor.visit(tree2));
    }

    @Test
    public void visitMultiplicationExprTest(){
        ParseTree tree1 = parser.parse("100/3");
        ParseTree tree2 = parser.parse("100/2");
        
        RuleEvalVisitor visitor = new RuleEvalVisitor();
        System.out.println(visitor.visit(tree1));
        System.out.println(visitor.visit(tree2));
    }
    
    @Test
    public void visitInExprTest(){
        ParseTree tree = parser.parse("a in (e,f,g,^,1,&)");
        RuleEvalVisitor visitor = new RuleEvalVisitor();
        System.out.println(visitor.visit(tree));
    }
    
    @Test
    public void visitorTestForPerformance(){
        String content = "if (_1000.1001_>20){100} else if (_1000.1002_>20){200} else {300}";
        //String content = "_1000.1001_>_1000.1002_";
        
        List<Var> vars = new ArrayList<>();
        vars.add(new Var("_1000.1001_", "10"));
        vars.add(new Var("_1000.1002_", "30"));
        long startTime = System.currentTimeMillis();
        System.out.println(ExprEngine.getInstance().eval(content, vars));
        System.out.println("cost -> " + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        System.out.println(ExprEngine.getInstance().eval(content, vars));
        System.out.println("cost -> " + (System.currentTimeMillis() - startTime));
    }
}
