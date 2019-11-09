// Generated from Rule.g4 by ANTLR 4.7

package ext.antlr4.expr.entity;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RuleParser}.
 */
public interface RuleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RuleParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(RuleParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(RuleParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInExpr(RuleParser.InExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInExpr(RuleParser.InExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExpr(RuleParser.AtomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExpr(RuleParser.AtomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(RuleParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(RuleParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(RuleParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(RuleParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(RuleParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(RuleParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mutilMsgExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMutilMsgExpr(RuleParser.MutilMsgExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mutilMsgExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMutilMsgExpr(RuleParser.MutilMsgExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(RuleParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(RuleParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinusExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpr(RuleParser.UnaryMinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinusExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpr(RuleParser.UnaryMinusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpr(RuleParser.MultiplicationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpr(RuleParser.MultiplicationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code likeExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLikeExpr(RuleParser.LikeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code likeExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLikeExpr(RuleParser.LikeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIfExpr(RuleParser.IfExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIfExpr(RuleParser.IfExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code childExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterChildExpr(RuleParser.ChildExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code childExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitChildExpr(RuleParser.ChildExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPowExpr(RuleParser.PowExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPowExpr(RuleParser.PowExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(RuleParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(RuleParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(RuleParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(RuleParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dictAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterDictAtom(RuleParser.DictAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dictAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitDictAtom(RuleParser.DictAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterIntAtom(RuleParser.IntAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitIntAtom(RuleParser.IntAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterFloatAtom(RuleParser.FloatAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitFloatAtom(RuleParser.FloatAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterBooleanAtom(RuleParser.BooleanAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitBooleanAtom(RuleParser.BooleanAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterIdAtom(RuleParser.IdAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitIdAtom(RuleParser.IdAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterStringAtom(RuleParser.StringAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitStringAtom(RuleParser.StringAtomContext ctx);
}