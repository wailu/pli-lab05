package edu.nus.comp.pli.epl.bigstepsemantics

import edu.nus.comp.pli.epl.parser.EplAST.{Minus, _}

object EplBigStepSemantics {

  sealed trait Result
  case class ResultInt(value: Int) extends Result
  case class ResultBool(value: Boolean) extends Result
  case object EvaluationError extends Result

}
