// Generated from Rule.g4 by ANTLR 4.7

package ext.antlr4.expr.entity;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RuleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RuleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RuleParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(RuleParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInExpr(RuleParser.InExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExpr(RuleParser.AtomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(RuleParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(RuleParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(RuleParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mutilMsgExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMutilMsgExpr(RuleParser.MutilMsgExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(RuleParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryMinusExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinusExpr(RuleParser.UnaryMinusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpr(RuleParser.MultiplicationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code likeExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikeExpr(RuleParser.LikeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr(RuleParser.IfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code childExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChildExpr(RuleParser.ChildExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code powExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowExpr(RuleParser.PowExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(RuleParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link RuleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(RuleParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dictAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictAtom(RuleParser.DictAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntAtom(RuleParser.IntAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatAtom(RuleParser.FloatAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanAtom(RuleParser.BooleanAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdAtom(RuleParser.IdAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAtom}
	 * labeled alternative in {@link RuleParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAtom(RuleParser.StringAtomContext ctx);
}