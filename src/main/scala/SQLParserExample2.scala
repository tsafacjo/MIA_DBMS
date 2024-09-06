import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._

// Assuming that SQLLexer, SQLParser, and the related AST classes (SelectNode, JoinNode, etc.) are defined appropriately
object QueryOptimizerExample {

  // Method to parse SQL and return a ParseTree
  def generateParse(sql: String): SQLParser = {
    // Create a CharStream that reads from the SQL string
    val input: CharStream = CharStreams.fromString(sql)

    // Create a lexer that feeds off the input CharStream
    val lexer = new SQLLexer(input)

    // Create a token stream from the lexer
    val tokens = new CommonTokenStream(lexer)

    // Create a parser that feeds off the token stream
    val parser = new SQLParser(tokens)

    // Begin parsing
    parser
  }

  def main(args: Array[String]): Unit = {
    //val sql = "SELECT name, age FROM users u WHERE u.age > 25"
    
    val sql = "SELECT name, age FROM users INNER JOIN cars ON ( users.id=cars.owner ) WHERE users.age > 25"
   
    //val sql = "SELECT name, age FROM users INNER JOIN cars ON (id=owner) WHERE age > 25"
    val parser =  generateParse(sql)
    
    val tree = parser.sqlStatement()

    println(tree.toStringTree(parser))
    // Convert parse tree to custom AST
    val astBuilder = new ASTBuilder()
    val ast = astBuilder.visit(tree).asInstanceOf[SelectNode]

    // Initialize QueryOptimizer
    val queryOptimizer = new QueryOptimizer()

    // Optimize the AST
    val optimizedQuery = queryOptimizer.optimize(ast)

    // Print the optimized query

    println("optimized :"+optimizedQuery)

  }
}
