package ext.antlr4.expr.parser;

import java.text.MessageFormat;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ext.antlr4.expr.entity.RuleErrorListener;
import ext.antlr4.expr.entity.RuleLexer;
import ext.antlr4.expr.entity.RuleParser;
import ext.antlr4.expr.entity.RuleParser.ParseContext;
import ext.antlr4.expr.exception.ExprParseException;

public class ExprParser {

    public ParseTree parse(String content) {
        RuleParser parser = getParser(content);
        ParseTree tree = parser.parse();
        System.out.println(tree.toStringTree(parser));
        return tree;
    }

    private RuleParser getParser(String content) {
        CharStream input = CharStreams.fromString(content);
        RuleLexer lexer = new RuleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new RuleParser(tokens);
    }

    public void callback(String errorMsg) {
        throw new ExprParseException(errorMsg);
    }

    public void checkExpr(String content) {
        RuleParser parser = getParser(content);
        parser.addErrorListener(new RuleErrorListener(this));

        ParseTree tree = parser.parse();
        System.out.println(tree.toStringTree(parser));

        if (!(tree instanceof ParseContext)) {
            throw new ExprParseException(MessageFormat.format("'parseTree' is empty, expression->{0}", content));
        }
    }
}
