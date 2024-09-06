class QueryOptimizer {
  // Optimize a SELECT statement AST
  def optimize(query: SelectNode): SelectNode = {
    // Push down WHERE conditions into JOINs
    val pushedDownJoins = query.joins.map { join =>
      pushDownConditions(join, query.from, query.where)
    }

    // Return an optimized SELECT node
    SelectNode(query.columns, query.from, pushedDownJoins, query.where)
  }

  // Push down conditions into a JOIN if applicable
  def pushDownConditions(join: JoinNode,from:TableNode, where: Option[ConditionNode]): JoinNode = {
    where match {
      case Some(condition) =>
        if (referencestables(condition, List(from.name,join.table.name))) {
            printf("references true")
            AndNode(join.condition, condition) match {
            case andCondition: ConditionNode =>{
              printf("ici *")
              join.copy(condition = andCondition) // Ensure AndNode is a valid ConditionNode
              }
          }
        } else {
          join
        }
      case None => join
    }
  }

  // Check if a condition references a specific tables
  def referencestables(node: ASTNode, tables: List[String]): Boolean = {
    println(tables)
    node match {
      case ColumnNode(column) => {
        printf(s"$column $tables ")
      tables.exists(table => column.startsWith(s"$table."))}
      case AndNode(left, right) => referencestables(left, tables) || referencestables(right, tables)
      case OrNode(left, right) => referencestables(left, tables) || referencestables(right, tables)
      case NotNode(expr) => referencestables(expr, tables)
      case EqualsNode(left, right) => referencestables(left, tables) || referencestables(right, tables)
      case GreaterThanNode(left, right) => referencestables(left, tables) || referencestables(right, tables)
      case LessThanNode(left, right) => referencestables(left, tables) || referencestables(right, tables)
      case GreaterThanOrEqualsNode(left, right) => referencestables(left, tables) || referencestables(right, tables)
      case LessThanOrEqualsNode(left, right) => referencestables(left, tables) || referencestables(right, tables)
      case NotEqualsNode(left, right) => referencestables(left, tables) || referencestables(right, tables)
      case SubqueryNode(query) => referencestablesInSubquery(query, tables)
      case _ => false
    }
  }

  // Check if a subquery condition references a specific tables
  private def referencestablesInSubquery(query: SelectNode, tables: List[String]): Boolean = {
    query.where match {
      case Some(condition) => referencestables(condition, tables)
      case None => false
    }
  }
}
