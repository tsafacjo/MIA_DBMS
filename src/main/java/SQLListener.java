// Generated from SQL.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLParser}.
 */
public interface SQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQLParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(SQLParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(SQLParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(SQLParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(SQLParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#subquery}.
	 * @param ctx the parse tree
	 */
	void enterSubquery(SQLParser.SubqueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#subquery}.
	 * @param ctx the parse tree
	 */
	void exitSubquery(SQLParser.SubqueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnList}.
	 * @param ctx the parse tree
	 */
	void enterColumnList(SQLParser.ColumnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnList}.
	 * @param ctx the parse tree
	 */
	void exitColumnList(SQLParser.ColumnListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnItem}.
	 * @param ctx the parse tree
	 */
	void enterColumnItem(SQLParser.ColumnItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnItem}.
	 * @param ctx the parse tree
	 */
	void exitColumnItem(SQLParser.ColumnItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSource(SQLParser.TableSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSource(SQLParser.TableSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void enterJoinClause(SQLParser.JoinClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void exitJoinClause(SQLParser.JoinClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#joinType}.
	 * @param ctx the parse tree
	 */
	void enterJoinType(SQLParser.JoinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#joinType}.
	 * @param ctx the parse tree
	 */
	void exitJoinType(SQLParser.JoinTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(SQLParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(SQLParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(SQLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(SQLParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(SQLParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(SQLParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SQLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SQLParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(SQLParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(SQLParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(SQLParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(SQLParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(SQLParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(SQLParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdentifier(SQLParser.QualifiedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdentifier(SQLParser.QualifiedIdentifierContext ctx);
}