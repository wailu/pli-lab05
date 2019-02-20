package edu.nus.comp.pli.epl.evm

import edu.nus.comp.pli.epl.bigstepsemantics.EplBigStepSemantics.{Result, ResultBool, ResultInt}
import edu.nus.comp.pli.epl.evm.EvmInstruction._

case class EvmState(program: Seq[Instruction], opdStack: List[Result]) {
  def isDone: Boolean = program.headOption == Option(DONE)

  def result: Result = {
    assert(isDone)
    opdStack.head
  }

  def transition: EvmState = (program, opdStack) match {
    case (PLUS :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultInt(n1 + n2) :: restOpds)

    case (MINUS :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultInt(n1 - n2) :: restOpds)

    case (TIMES :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultInt(n1 * n2) :: restOpds)

    /*add your code for div and pow*/
    //case (DIV :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>

    //case (POW :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>

    case (AND :: restProgram, ResultBool(b2) :: ResultBool(b1) :: restOpds) =>
      EvmState(restProgram, ResultBool(b1 && b2) :: restOpds)

    case (OR :: restProgram, ResultBool(b2) :: ResultBool(b1) :: restOpds) =>
      EvmState(restProgram, ResultBool(b1 || b2) :: restOpds)

    case (LT :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultBool(n1 < n2) :: restOpds)

    case (LE :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultBool(n1 <= n2) :: restOpds)

    case (GT :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultBool(n1 > n2) :: restOpds)

    case (GE :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultBool(n1 >= n2) :: restOpds)

    /*add your code for EQ*/
    case (EQ :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultBool(n1 == n2) :: restOpds)

    case (NEQ :: restProgram, ResultInt(n2) :: ResultInt(n1) :: restOpds) =>
      EvmState(restProgram, ResultBool(n1 != n2) :: restOpds)

    case (NOT :: restProgram, ResultBool(b) :: restOpds) =>
      EvmState(restProgram, ResultBool(!b) :: restOpds)

    case (NEG :: restProgram, ResultInt(n) :: restOpds) =>
      EvmState(restProgram, ResultInt(- n) :: restOpds)

    case (LDCI(n) :: restProgram, opds) =>
      EvmState(restProgram, ResultInt(n) :: opds)

    /*add your code for bool*/
    case (LDCB(b) :: restProgram, opds) =>
      EvmState(restProgram, ResultBool(b) :: opds)

    /*add your code for if else statement
    * hint:
    * 1) be careful with the order of
    * 2) the type of if-branch and else-branch should be the same
    * */
    //case (IFELSE ::restProgram,  (...) ::(...) :: (...) ::restOpds) =>


  }

  def execute: Result =
    Iterator.iterate(this)(_.transition).dropWhile(! _.isDone).map(_.result).next
}