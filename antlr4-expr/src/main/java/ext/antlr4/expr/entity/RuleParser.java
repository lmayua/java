// Generated from Rule.g4 by ANTLR 4.7

package ext.antlr4.expr.entity;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RuleParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, EQ=3, NEQ=4, GT=5, LT=6, GTEQ=7, LTEQ=8, PLUS=9, MINUS=10, 
		MULT=11, DIV=12, MOD=13, POW=14, NOT=15, DLLR=16, SCOL=17, ASSIGN=18, 
		OPAR=19, CPAR=20, OBRACE=21, CBRACE=22, OMBRAC=23, CMBRAC=24, TRUE=25, 
		FALSE=26, IF=27, ELSEIF=28, ELSE=29, IN=30, NOTIN=31, COMMA=32, LIKE=33, 
		DICT=34, ID=35, INT=36, FLOAT=37, STRING=38, COMMENT=39, SPACE=40, OTHER=41;
	public static final int
		RULE_parse = 0, RULE_expr = 1, RULE_atom = 2;
	public static final String[] ruleNames = {
		"parse", "expr", "atom"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'=='", "'!='", "'>'", "'<'", "'>='", "'<='", "'+'", 
		"'-'", "'*'", "'/'", "'%'", "'^'", "'!'", "'$'", "';'", "'='", "'('", 
		"')'", "'{'", "'}'", "'['", "']'", "'true'", "'false'", "'if'", "'else if'", 
		"'else'", "'in'", "'not in'", "','", "'like'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OR", "AND", "EQ", "NEQ", "GT", "LT", "GTEQ", "LTEQ", "PLUS", "MINUS", 
		"MULT", "DIV", "MOD", "POW", "NOT", "DLLR", "SCOL", "ASSIGN", "OPAR", 
		"CPAR", "OBRACE", "CBRACE", "OMBRAC", "CMBRAC", "TRUE", "FALSE", "IF", 
		"ELSEIF", "ELSE", "IN", "NOTIN", "COMMA", "LIKE", "DICT", "ID", "INT", 
		"FLOAT", "STRING", "COMMENT", "SPACE", "OTHER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Rule.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RuleParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(6);
				expr(0);
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << NOT) | (1L << DLLR) | (1L << OPAR) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << DICT) | (1L << ID) | (1L << INT) | (1L << FLOAT) | (1L << STRING))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPAR() { return getToken(RuleParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(RuleParser.CPAR, 0); }
		public TerminalNode IN() { return getToken(RuleParser.IN, 0); }
		public TerminalNode NOTIN() { return getToken(RuleParser.NOTIN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(RuleParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RuleParser.COMMA, i);
		}
		public InExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterInExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitInExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitInExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomExprContext extends ExprContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(RuleParser.OR, 0); }
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AdditiveExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(RuleParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(RuleParser.MINUS, 0); }
		public AdditiveExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitAdditiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelationalExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LTEQ() { return getToken(RuleParser.LTEQ, 0); }
		public TerminalNode GTEQ() { return getToken(RuleParser.GTEQ, 0); }
		public TerminalNode LT() { return getToken(RuleParser.LT, 0); }
		public TerminalNode GT() { return getToken(RuleParser.GT, 0); }
		public RelationalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitRelationalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MutilMsgExprContext extends ExprContext {
		public TerminalNode DLLR() { return getToken(RuleParser.DLLR, 0); }
		public TerminalNode OMBRAC() { return getToken(RuleParser.OMBRAC, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CMBRAC() { return getToken(RuleParser.CMBRAC, 0); }
		public MutilMsgExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterMutilMsgExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitMutilMsgExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitMutilMsgExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExprContext extends ExprContext {
		public TerminalNode NOT() { return getToken(RuleParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryMinusExprContext extends ExprContext {
		public TerminalNode MINUS() { return getToken(RuleParser.MINUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryMinusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterUnaryMinusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitUnaryMinusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitUnaryMinusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplicationExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(RuleParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(RuleParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(RuleParser.MOD, 0); }
		public MultiplicationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterMultiplicationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitMultiplicationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitMultiplicationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LikeExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LIKE() { return getToken(RuleParser.LIKE, 0); }
		public List<TerminalNode> MOD() { return getTokens(RuleParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(RuleParser.MOD, i);
		}
		public LikeExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterLikeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitLikeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitLikeExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfExprContext extends ExprContext {
		public TerminalNode IF() { return getToken(RuleParser.IF, 0); }
		public List<TerminalNode> OPAR() { return getTokens(RuleParser.OPAR); }
		public TerminalNode OPAR(int i) {
			return getToken(RuleParser.OPAR, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CPAR() { return getTokens(RuleParser.CPAR); }
		public TerminalNode CPAR(int i) {
			return getToken(RuleParser.CPAR, i);
		}
		public List<TerminalNode> OBRACE() { return getTokens(RuleParser.OBRACE); }
		public TerminalNode OBRACE(int i) {
			return getToken(RuleParser.OBRACE, i);
		}
		public List<TerminalNode> CBRACE() { return getTokens(RuleParser.CBRACE); }
		public TerminalNode CBRACE(int i) {
			return getToken(RuleParser.CBRACE, i);
		}
		public List<TerminalNode> ELSEIF() { return getTokens(RuleParser.ELSEIF); }
		public TerminalNode ELSEIF(int i) {
			return getToken(RuleParser.ELSEIF, i);
		}
		public TerminalNode ELSE() { return getToken(RuleParser.ELSE, 0); }
		public IfExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterIfExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitIfExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitIfExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ChildExprContext extends ExprContext {
		public TerminalNode OPAR() { return getToken(RuleParser.OPAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CPAR() { return getToken(RuleParser.CPAR, 0); }
		public ChildExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterChildExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitChildExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitChildExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PowExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode POW() { return getToken(RuleParser.POW, 0); }
		public PowExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterPowExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitPowExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitPowExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualityExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ() { return getToken(RuleParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(RuleParser.NEQ, 0); }
		public EqualityExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(RuleParser.AND, 0); }
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPAR:
				{
				_localctx = new ChildExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(12);
				match(OPAR);
				setState(13);
				expr(0);
				setState(14);
				match(CPAR);
				}
				break;
			case DLLR:
				{
				_localctx = new MutilMsgExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(16);
				match(DLLR);
				setState(17);
				match(OMBRAC);
				setState(18);
				expr(0);
				setState(19);
				match(CMBRAC);
				}
				break;
			case IF:
				{
				_localctx = new IfExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(21);
				match(IF);
				setState(22);
				match(OPAR);
				setState(23);
				expr(0);
				setState(24);
				match(CPAR);
				setState(25);
				match(OBRACE);
				setState(26);
				expr(0);
				setState(27);
				match(CBRACE);
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(28);
						match(ELSEIF);
						setState(29);
						match(OPAR);
						setState(30);
						expr(0);
						setState(31);
						match(CPAR);
						setState(32);
						match(OBRACE);
						setState(33);
						expr(0);
						setState(34);
						match(CBRACE);
						}
						} 
					}
					setState(40);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(46);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(41);
					match(ELSE);
					setState(42);
					match(OBRACE);
					setState(43);
					expr(0);
					setState(44);
					match(CBRACE);
					}
					break;
				}
				}
				break;
			case TRUE:
			case FALSE:
			case DICT:
			case ID:
			case INT:
			case FLOAT:
			case STRING:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(48);
				atom();
				}
				break;
			case MINUS:
				{
				_localctx = new UnaryMinusExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(49);
				match(MINUS);
				setState(50);
				expr(8);
				}
				break;
			case NOT:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(51);
				match(NOT);
				setState(52);
				expr(7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(103);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new PowExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(55);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(56);
						match(POW);
						setState(57);
						expr(9);
						}
						break;
					case 2:
						{
						_localctx = new MultiplicationExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(58);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(59);
						((MultiplicationExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((MultiplicationExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(60);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new AdditiveExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(61);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(62);
						((AdditiveExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AdditiveExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(63);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new RelationalExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(64);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(65);
						((RelationalExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GTEQ) | (1L << LTEQ))) != 0)) ) {
							((RelationalExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(66);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new EqualityExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(67);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(68);
						((EqualityExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((EqualityExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(69);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(70);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(71);
						match(AND);
						setState(72);
						expr(3);
						}
						break;
					case 7:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(73);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(74);
						match(OR);
						setState(75);
						expr(2);
						}
						break;
					case 8:
						{
						_localctx = new InExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(76);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(77);
						((InExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==IN || _la==NOTIN) ) {
							((InExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(78);
						match(OPAR);
						setState(84);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
						while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1+1 ) {
								{
								{
								setState(79);
								expr(0);
								setState(80);
								match(COMMA);
								}
								} 
							}
							setState(86);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
						}
						setState(87);
						expr(0);
						setState(88);
						match(CPAR);
						}
						break;
					case 9:
						{
						_localctx = new LikeExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(90);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(91);
						match(LIKE);
						setState(101);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
						case 1:
							{
							setState(92);
							match(MOD);
							setState(93);
							expr(0);
							setState(94);
							match(MOD);
							}
							break;
						case 2:
							{
							setState(96);
							match(MOD);
							setState(97);
							expr(0);
							}
							break;
						case 3:
							{
							setState(98);
							expr(0);
							setState(99);
							match(MOD);
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(107);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BooleanAtomContext extends AtomContext {
		public TerminalNode TRUE() { return getToken(RuleParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(RuleParser.FALSE, 0); }
		public BooleanAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterBooleanAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitBooleanAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitBooleanAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdAtomContext extends AtomContext {
		public TerminalNode ID() { return getToken(RuleParser.ID, 0); }
		public IdAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterIdAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitIdAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitIdAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DictAtomContext extends AtomContext {
		public TerminalNode DICT() { return getToken(RuleParser.DICT, 0); }
		public DictAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterDictAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitDictAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitDictAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatAtomContext extends AtomContext {
		public TerminalNode FLOAT() { return getToken(RuleParser.FLOAT, 0); }
		public FloatAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterFloatAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitFloatAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitFloatAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntAtomContext extends AtomContext {
		public TerminalNode INT() { return getToken(RuleParser.INT, 0); }
		public IntAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterIntAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitIntAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitIntAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringAtomContext extends AtomContext {
		public TerminalNode STRING() { return getToken(RuleParser.STRING, 0); }
		public StringAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).enterStringAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RuleListener ) ((RuleListener)listener).exitStringAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RuleVisitor ) return ((RuleVisitor<? extends T>)visitor).visitStringAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atom);
		int _la;
		try {
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DICT:
				_localctx = new DictAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				match(DICT);
				}
				break;
			case INT:
				_localctx = new IntAtomContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(INT);
				}
				break;
			case FLOAT:
				_localctx = new FloatAtomContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				match(FLOAT);
				}
				break;
			case TRUE:
			case FALSE:
				_localctx = new BooleanAtomContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case ID:
				_localctx = new IdAtomContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(112);
				match(ID);
				}
				break;
			case STRING:
				_localctx = new StringAtomContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(113);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		case 6:
			return precpred(_ctx, 1);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+w\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\3\2\6\2\n\n\2\r\2\16\2\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\'"+
		"\n\3\f\3\16\3*\13\3\3\3\3\3\3\3\3\3\3\3\5\3\61\n\3\3\3\3\3\3\3\3\3\3\3"+
		"\5\38\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3U\n\3\f\3\16\3X\13"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3h\n\3\7"+
		"\3j\n\3\f\3\16\3m\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4u\n\4\3\4\3V\3\4\5\2"+
		"\4\6\2\b\3\2\r\17\3\2\13\f\3\2\7\n\3\2\5\6\3\2 !\3\2\33\34\2\u008c\2\t"+
		"\3\2\2\2\4\67\3\2\2\2\6t\3\2\2\2\b\n\5\4\3\2\t\b\3\2\2\2\n\13\3\2\2\2"+
		"\13\t\3\2\2\2\13\f\3\2\2\2\f\3\3\2\2\2\r\16\b\3\1\2\16\17\7\25\2\2\17"+
		"\20\5\4\3\2\20\21\7\26\2\2\218\3\2\2\2\22\23\7\22\2\2\23\24\7\31\2\2\24"+
		"\25\5\4\3\2\25\26\7\32\2\2\268\3\2\2\2\27\30\7\35\2\2\30\31\7\25\2\2\31"+
		"\32\5\4\3\2\32\33\7\26\2\2\33\34\7\27\2\2\34\35\5\4\3\2\35(\7\30\2\2\36"+
		"\37\7\36\2\2\37 \7\25\2\2 !\5\4\3\2!\"\7\26\2\2\"#\7\27\2\2#$\5\4\3\2"+
		"$%\7\30\2\2%\'\3\2\2\2&\36\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)\60"+
		"\3\2\2\2*(\3\2\2\2+,\7\37\2\2,-\7\27\2\2-.\5\4\3\2./\7\30\2\2/\61\3\2"+
		"\2\2\60+\3\2\2\2\60\61\3\2\2\2\618\3\2\2\2\628\5\6\4\2\63\64\7\f\2\2\64"+
		"8\5\4\3\n\65\66\7\21\2\2\668\5\4\3\t\67\r\3\2\2\2\67\22\3\2\2\2\67\27"+
		"\3\2\2\2\67\62\3\2\2\2\67\63\3\2\2\2\67\65\3\2\2\28k\3\2\2\29:\f\13\2"+
		"\2:;\7\20\2\2;j\5\4\3\13<=\f\b\2\2=>\t\2\2\2>j\5\4\3\t?@\f\7\2\2@A\t\3"+
		"\2\2Aj\5\4\3\bBC\f\6\2\2CD\t\4\2\2Dj\5\4\3\7EF\f\5\2\2FG\t\5\2\2Gj\5\4"+
		"\3\6HI\f\4\2\2IJ\7\4\2\2Jj\5\4\3\5KL\f\3\2\2LM\7\3\2\2Mj\5\4\3\4NO\f\16"+
		"\2\2OP\t\6\2\2PV\7\25\2\2QR\5\4\3\2RS\7\"\2\2SU\3\2\2\2TQ\3\2\2\2UX\3"+
		"\2\2\2VW\3\2\2\2VT\3\2\2\2WY\3\2\2\2XV\3\2\2\2YZ\5\4\3\2Z[\7\26\2\2[j"+
		"\3\2\2\2\\]\f\r\2\2]g\7#\2\2^_\7\17\2\2_`\5\4\3\2`a\7\17\2\2ah\3\2\2\2"+
		"bc\7\17\2\2ch\5\4\3\2de\5\4\3\2ef\7\17\2\2fh\3\2\2\2g^\3\2\2\2gb\3\2\2"+
		"\2gd\3\2\2\2hj\3\2\2\2i9\3\2\2\2i<\3\2\2\2i?\3\2\2\2iB\3\2\2\2iE\3\2\2"+
		"\2iH\3\2\2\2iK\3\2\2\2iN\3\2\2\2i\\\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2"+
		"\2\2l\5\3\2\2\2mk\3\2\2\2nu\7$\2\2ou\7&\2\2pu\7\'\2\2qu\t\7\2\2ru\7%\2"+
		"\2su\7(\2\2tn\3\2\2\2to\3\2\2\2tp\3\2\2\2tq\3\2\2\2tr\3\2\2\2ts\3\2\2"+
		"\2u\7\3\2\2\2\13\13(\60\67Vgikt";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}