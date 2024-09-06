// Base trait for all AST nodes
sealed trait ASTNode

// Base trait for all condition-related nodes
sealed trait ConditionNode extends ASTNode

// Logical operators should extend ConditionNode to be used in conditions
sealed trait LogicalOperatorNode extends ConditionNode
case class AndNode(left: ConditionNode, right: ConditionNode) extends LogicalOperatorNode
case class OrNode(left: ConditionNode, right: ConditionNode) extends LogicalOperatorNode
case class NotNode(expr: ConditionNode) extends LogicalOperatorNode

// Comparison operators extend ConditionNode
sealed trait WhereNode extends ConditionNode
case class EqualsNode(left: ASTNode, right: ASTNode) extends WhereNode
case class GreaterThanNode(left: ASTNode, right: ASTNode) extends WhereNode
case class LessThanNode(left: ASTNode, right: ASTNode) extends WhereNode
case class GreaterThanOrEqualsNode(left: ASTNode, right: ASTNode) extends WhereNode
case class LessThanOrEqualsNode(left: ASTNode, right: ASTNode) extends WhereNode
case class NotEqualsNode(left: ASTNode, right: ASTNode) extends WhereNode

// Node for SELECT statement
case class SelectNode(columns: List[ColumnNode], from: TableNode, joins: List[JoinNode], where: Option[WhereNode]) extends ASTNode

// Node for a single column
case class ColumnNode(name: String) extends ASTNode

// Node for a table
case class TableNode(name: String) extends ASTNode

// Node for a JOIN clause
case class JoinNode(joinType: String, table: TableNode, condition: ConditionNode) extends ASTNode

// Node for WHERE conditions
//case class WhereNode(condition: ConditionNode) extends ASTNode

// Node for a literal value (e.g., a string or number)
case class ValueNode(value: String) extends ASTNode

// Node for a subquery
case class SubqueryNode(query: SelectNode) extends ASTNode
