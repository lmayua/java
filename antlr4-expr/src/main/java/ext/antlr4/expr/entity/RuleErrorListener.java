package ext.antlr4.expr.entity;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import ext.antlr4.expr.parser.ExprParser;

public class RuleErrorListener extends BaseErrorListener {

    private ExprParser parser;
    
    public RuleErrorListener(ExprParser parser) {
        this.parser = parser;
    }
    
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {
        /*
            // 词法解析
            Lexer lexer = (Lexer)recognizer;
            String text = lexer._input.getText(Interval.of(lexer._tokenStartCharIndex, lexer._input.index()));
            String errorText = lexer.getErrorDisplay(text);
            System.err.println(MessageFormat.format("expression -> {0}, error text -> {1}", text, errorText));
        */
        
        List<String> stackList = ((Parser)recognizer).getRuleInvocationStack();
        Collections.reverse(stackList);
        String errorMsg = MessageFormat.format("line {0}:{1} {2}", line, charPositionInLine, msg);
        System.err.println(errorMsg);
        parser.callback(errorMsg);
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    }
    
}
