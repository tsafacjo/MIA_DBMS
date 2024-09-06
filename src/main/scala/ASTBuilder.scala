import scala.jdk.CollectionConverters._
import scala.jdk.CollectionConverters._

// Assuming ASTNode and its subclasses are defined
class ASTBuilder extends SQLBaseVisitor[ASTNode] {

  override def visitSqlStatement(ctx: SQLParser.SqlStatementContext): ASTNode = {
    visit(ctx.selectStatement())
  }

  override def visitSelectStatement(ctx: SQLParser.SelectStatementContext): ASTNode = {
    val columns = ctx.columnList().columnItem().asScala.map(visit).collect { case c: ColumnNode => c }.toList
    val from = visit(ctx.tableSource()).asInstanceOf[TableNode]
    val joins = ctx.joinClause().asScala.map(visit).collect { case j: JoinNode => j }.toList
    val where = if (ctx.whereClause() != null) {
      Some(visit(ctx.whereClause()).asInstanceOf[WhereNode])
    } else {
      None
    }

    SelectNode(columns, from, joins, where)
  }

  override def visitColumnItem(ctx: SQLParser.ColumnItemContext): ASTNode = {
    if (ctx.columnName() != null) {
      ColumnNode(ctx.columnName().getText)
    } else {
      visit(ctx.subquery())
    }
  }


  override def visitTableSource(ctx: SQLParser.TableSourceContext): ASTNode = {
    if (ctx.tableName() != null) {
      TableNode(ctx.tableName().getText)
    } else {
      visit(ctx.subquery())
    }
  }

  override def visitWhereClause(ctx: SQLParser.WhereClauseContext): ASTNode = {
    // Assuming a single condition, can be extended for AND/OR
    visit(ctx.condition(0)).asInstanceOf[ConditionNode]
  }


  // Method to map operator string to specific comparison node
  def createComparisonNode(left: ASTNode, operator: String, right: ASTNode): ConditionNode = {
    operator match {
      case "="  => EqualsNode(left, right)
      case ">"  => GreaterThanNode(left, right)
      case "<"  => LessThanNode(left, right)
      case ">=" => GreaterThanOrEqualsNode(left, right)
      case "<=" => LessThanOrEqualsNode(left, right)
      case "!=" => NotEqualsNode(left, right)
      case _    => throw new UnsupportedOperationException(s"Unknown operator: $operator")
    }
}

// Visit method for condition in the SQL visitor
override def visitCondition(ctx: SQLParser.ConditionContext): ASTNode = {
  if (ctx.columnName(0) != null) {
    val left = ColumnNode(ctx.columnName(0).getText)
    val operator = ctx.operator().getText
    val right = if (ctx.value() != null) {
      ValueNode(ctx.value().getText)
    } else if (ctx.columnName().size() > 1) {
      ColumnNode(ctx.columnName(1).getText)
    } else if (ctx.subquery() != null) {
      SubqueryNode(visit(ctx.subquery()).asInstanceOf[SelectNode])
    } else {
      throw new UnsupportedOperationException("Unsupported condition format")
    }
    createComparisonNode(left, operator, right)
  } else {
    throw new UnsupportedOperationException("Column name is null")
  }
}


  override def visitJoinClause(ctx: SQLParser.JoinClauseContext): ASTNode = {
    val joinType = ctx.joinType().getText
    val table = visit(ctx.tableSource()).asInstanceOf[TableNode]
    val condition = visit(ctx.condition()).asInstanceOf[ConditionNode]

    JoinNode(joinType, table, condition)
  }




  override def visitSubquery(ctx: SQLParser.SubqueryContext): ASTNode = {
    SubqueryNode(visit(ctx.selectStatement()).asInstanceOf[SelectNode])
  }


}
