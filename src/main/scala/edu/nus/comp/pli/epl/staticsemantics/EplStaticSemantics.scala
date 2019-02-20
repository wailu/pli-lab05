package edu.nus.comp.pli.epl.staticsemantics

import edu.nus.comp.pli.epl.parser.EplAST._

/**
  * @author rvoicu
  */
object EplStaticSemantics {
  sealed trait ResultType
  case object ResultTypeInt extends ResultType
  case object ResultTypeBool extends ResultType
  case object ResultTypeError extends ResultType
}
