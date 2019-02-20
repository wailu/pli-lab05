package edu.nus.comp.pli.epl.evm

object EvmInstruction {
  sealed trait Instruction

  case class LDCI(n: Int) extends Instruction
  case class LDCB(b: Boolean) extends Instruction
  case object NEG extends Instruction
  case object PLUS extends Instruction
  case object MINUS extends Instruction
  case object TIMES extends Instruction
  case object DIV extends Instruction
  case object AND extends Instruction
  case object OR extends Instruction
  case object NOT extends Instruction
  case object LT extends Instruction
  case object LE extends Instruction
  case object GT extends Instruction
  case object GE extends Instruction
  case object EQ extends Instruction
  case object NEQ extends Instruction
  case object DONE extends Instruction
  case object POW extends Instruction
  case object IFELSE extends Instruction
}
