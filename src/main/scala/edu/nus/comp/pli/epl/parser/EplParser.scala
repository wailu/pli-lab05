package edu.nus.comp.pli.epl.parser

import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}
import EplAST._
import ExpressionParserParser.{ExprContext => ASTNode}
import org.antlr.v4.runtime.tree.TerminalNode

object EplParser {

  def parse(input: String): Expression = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new ExpressionParserLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new ExpressionParserParser(tokens)
    treeWalk(parser.expr)
  }

  private implicit class ASTNodePimp(val node: ASTNode) extends AnyVal {
    def left: ASTNode = node.getChild(0).asInstanceOf[ASTNode]

    def right: ASTNode = node.getChild(2).asInstanceOf[ASTNode]

    def operand: ASTNode = node.getChild(1).asInstanceOf[ASTNode]

    def condition: ASTNode = node.getChild(1).asInstanceOf[ASTNode]

    def ifbranch: ASTNode = node.getChild(3).asInstanceOf[ASTNode]
    def elsebranch: ASTNode = node.getChild(5).asInstanceOf[ASTNode]
  }

  private def treeWalk(context: ASTNode): Expression = {
    import NodeType._
    val textMatch = context.getChild(if (context.getChildCount == 3) 1 else 0).getText
    (classify(context), textMatch) match {
      case (IntConst, text) => Num(text.toInt)
      case (BoolConst, text) => Bool(text.toBoolean)
      case (ParenExpr, _) => treeWalk(context.operand)
      case (UnaryExpr, Minus.opString) => Ury(Minus, treeWalk(context.operand))
      case (UnaryExpr, Not.opString) => Ury(Not, treeWalk(context.operand))
      case (BinaryExpr, Add.opString) => Bin(Add, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Sub.opString) => Bin(Sub, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Mul.opString) => Bin(Mul, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Div.opString) => Bin(Div, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Pow.opString) => Bin(Pow, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Lt.opString) => Bin(Lt, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Lte.opString) => Bin(Lte, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Gt.opString) => Bin(Gt, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Gte.opString) => Bin(Gte, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Eq.opString) => Bin(Eq, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, NEq.opString) => Bin(NEq, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, And.opString) => Bin(And, treeWalk(context.left), treeWalk(context.right))
      case (BinaryExpr, Or.opString) => Bin(Or, treeWalk(context.left), treeWalk(context.right))
      case (IfElseExpr, _) => IfElse(treeWalk(context.condition),treeWalk(context.ifbranch),treeWalk(context.elsebranch) )
    }
  }

  object NodeType extends Enumeration {
    type NodeType = Value
    val BoolConst, IntConst, UnaryExpr, BinaryExpr, ParenExpr, IfElseExpr = Value

    def classify(node: ASTNode): NodeType =
      (Option(node.INT), Option(node.BOOL), node.getChildCount, node.getChild(0).isInstanceOf[TerminalNode]) match {
        case (_, None, 1, _) => IntConst
        case (None, _, 1, _) => BoolConst
        case (None, None, 2, _) => UnaryExpr
        case (None, None, 3, false) => BinaryExpr
        case (None, None, 3, true) => ParenExpr
        case (None, None, 6, true) => IfElseExpr
      }
  }

}
