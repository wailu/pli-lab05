package edu.nus.comp.pli.epl.denotationalsemantics

import edu.nus.comp.pli.epl.parser.EplAST._
import edu.nus.comp.pli.epl.evm.EvmInstruction._

object EplDenotationalSemantics {
  private def compileHelper(expr: Expression): Seq[Instruction] = expr match {
    case Bin(Add, left, right) => compileHelper(left) ++ compileHelper(right) :+ PLUS
    /*add your code for sub,mul,div and pow*/
    case Bin(Sub, left, right) =>
      compileHelper(left) ++ compileHelper(right) :+ MINUS
    case Bin(Mul, left, right) =>
      compileHelper(left) ++ compileHelper(right) :+ TIMES
    case Bin(Div, left, right) =>
      compileHelper(left) ++ compileHelper(right) :+ DIV
    case Bin(Pow, left, right) =>
      compileHelper(left) ++ compileHelper(right) :+ POW
    case Bin(And, left, right) =>  compileHelper(left) ++ compileHelper(right) :+ AND
    /*add your code for or*/
    //case Bin(Or, left, right) =>
    case Bin(Lt, left, right) =>  compileHelper(left) ++ compileHelper(right) :+ LT
    /*add your code for lte*/
    //case Bin(Lte, left, right) =>
    case Bin(Gt, left, right) =>  compileHelper(left) ++ compileHelper(right) :+ GT
    /*add your code for gte*/
    //case Bin(Gte, left, right) =>
    case Bin(Eq, left, right) =>  compileHelper(left) ++ compileHelper(right) :+ EQ
    /*add your code for NEq*/
    //case Bin(NEq, left, right) =>
    case Ury(Not, inner) => compileHelper(inner) :+ NOT
    /*add your code for neg*/
    //case Ury(Minus, inner) => compileHelper(inner) :+ NEG
    case Num(value) => Seq(LDCI(value))
    /*add your code for bool value*/
    //case Bool(value) => Seq(LDCB(value))

    /*add your code for if else statement*/
    //case IfElse (cond, ifb, elseb) => compileHelper(cond) ++ compileHelper(ifb) ++ compileHelper(elseb) :+ IFELSE
  }


  def compile(expr: Expression) = compileHelper(expr) :+ DONE
}