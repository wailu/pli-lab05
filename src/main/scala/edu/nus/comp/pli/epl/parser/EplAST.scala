package edu.nus.comp.pli.epl.parser

package object EplAST {

  sealed class Operator(val opString: String)

  sealed class UnaryOperator(op: String) extends Operator(op)

  case object Not extends UnaryOperator("!")

  case object Minus extends UnaryOperator("-")

  sealed class BinaryOperator(op: String) extends Operator(op)

  case object And extends BinaryOperator("&")

  case object Or extends BinaryOperator("|")

  case object Mul extends BinaryOperator("*")

  case object Div extends BinaryOperator("/")

  case object Pow extends BinaryOperator("^")

  case object Add extends BinaryOperator("+")

  case object Sub extends BinaryOperator("-")

  case object Lt extends BinaryOperator("<")

  case object Lte extends BinaryOperator("<=")

  case object Gt extends BinaryOperator(">")

  case object Gte extends BinaryOperator(">=")

  case object Eq extends BinaryOperator("==")

  case object NEq extends BinaryOperator("!=")

  val binaryOperators = List(Mul, Div, Pow, Add, Sub, Lt, Lte, Gt, Gte, Eq, NEq)
  val unaryOperators = List(Not, Minus)

  sealed trait Expression

  case class IfElse (cond: Expression, ifb: Expression, elseb: Expression) extends Expression // IF ELSE STATEMENT

  case class Bin(op: BinaryOperator, lhs: Expression, rhs: Expression) extends Expression // Binary operator

  case class Ury(op: UnaryOperator, operand: Expression) extends Expression // Unary operator

  case class Num(value: Int) extends Expression

  case class Bool(value: Boolean) extends Expression
}
