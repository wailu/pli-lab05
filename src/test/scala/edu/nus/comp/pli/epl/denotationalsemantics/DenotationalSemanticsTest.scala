package edu.nus.comp.pli.epl.denotationalsemantics

import edu.nus.comp.pli.epl.bigstepsemantics.EplBigStepSemantics.ResultBool
import edu.nus.comp.pli.epl.parser.EplParser.parse
import edu.nus.comp.pli.epl.denotationalsemantics.EplDenotationalSemantics.compile
import edu.nus.comp.pli.epl.evm.EvmInstruction._
import edu.nus.comp.pli.epl.evm.EvmState
import org.scalatest.FlatSpec

class DenotationalSemanticsTest extends FlatSpec {
  "1) An expression" should "compile correctly" in {
    assert(compile(parse("123")) == List(LDCI(123), DONE))
  }
  "2) An expression" should "compile correctly" in {
    assert(compile(parse("true")) == List(LDCB(true), DONE))
  }
  "3) An expression" should "compile correctly" in {
    assert(compile(parse("false")) == List(LDCB(false), DONE))
  }
  "4) An expression" should "compile correctly" in {
    assert(compile(parse("1+2")) == List(LDCI(1), LDCI(2), PLUS, DONE))
  }
  "5) An expression" should "compile correctly" in {
    assert(compile(parse("1-2")) == List(LDCI(1), LDCI(2), MINUS, DONE))
  }
  "6) An expression" should "compile correctly" in {
    assert(compile(parse("2-1")) == List(LDCI(2), LDCI(1), MINUS, DONE))
  }
  "7) An expression" should "compile correctly" in {
    assert(compile(parse("1+2*3-4 <= 5")) == List(LDCI(1), LDCI(2), LDCI(3), TIMES, PLUS, LDCI(4), MINUS, LDCI(5), LE, DONE))
  }
  "8) An expression" should "compile correctly" in {
    assert(compile(parse("(1+2)*(4-3)")) == List(LDCI(1), LDCI(2), PLUS, LDCI(4), LDCI(3), MINUS, TIMES, DONE))
  }
  "9) An expression" should "compile correctly" in {
    assert(compile(parse("1 <= 2 & 4 == 5")) == List(LDCI(1), LDCI(2), LE, LDCI(4), LDCI(5), EQ, AND, DONE))
  }
  "10) An expression" should "compile correctly" in {
    assert(compile(parse("2^3")) == List(LDCI(2), LDCI(3), POW, DONE))
  }
  "11) An expression" should "compile correctly" in {
    assert(compile(parse("false == (!true) & false | (2+3 ==4)")) == List(LDCB(false), LDCB(true), NOT, EQ, LDCB(false), AND, LDCI(2), LDCI(3), PLUS, LDCI(4), EQ, OR, DONE))
  }
  "12) An expression" should "compile correctly" in {
    assert(compile(parse("false == 10 > 6")) == List(LDCB(false), LDCI(10), LDCI(6), GT, EQ, DONE))
  }
  "13) An expression" should "compile correctly" in {
    assert(compile(parse("false == !true & false")) == List(LDCB(false), LDCB(true), NOT, EQ, LDCB(false), AND, DONE))
  }
  "14) An expression" should "compile correctly" in {
    assert(compile(parse("10 == 3 + 7")) == List(LDCI(10), LDCI(3), LDCI(7), PLUS, EQ, DONE))
  }
  "15) An expression" should "compile correctly" in {
    assert(compile(parse("1+2*3+4")) == List(LDCI(1), LDCI(2), LDCI(3), TIMES, PLUS, LDCI(4), PLUS, DONE))
  }
  "16) An expression" should "compile correctly" in {
    assert(compile(parse("(1+2)*3")) == List(LDCI(1), LDCI(2), PLUS, LDCI(3), TIMES, DONE))
  }
  "17) An expression" should "compile correctly" in {
    assert(compile(parse("(1+2)*(4-3)")) == List(LDCI(1), LDCI(2), PLUS, LDCI(4), LDCI(3), MINUS, TIMES, DONE))
  }
  "18) An expression" should "compile correctly" in {
    assert(compile(parse("---5")) == List(LDCI(5), NEG, NEG, NEG, DONE))
  }
  "19) An expression" should "compile correctly" in {
    assert(compile(parse("--5")) == List(LDCI(5), NEG, NEG, DONE))
  }
  "20) An expression" should "compile correctly" in {
    assert(compile(parse("false == (!true) | false & (2+3 ==4)")) == List(LDCB(false), LDCB(true), NOT, EQ, LDCB(false), LDCI(2), LDCI(3), PLUS, LDCI(4), EQ, AND, OR, DONE))
  }
  "21) An expression" should "compile correctly" in {
    assert(compile(parse("if true then 2 else 3")) == List(LDCB(true), LDCI(2), LDCI(3), IFELSE, DONE))
  }
  "22) An expression" should "compile correctly" in {
    assert(compile(parse("if (1+2*3-4 <= 5) then 2^3 else ---5")) == List(LDCI(1), LDCI(2), LDCI(3), TIMES, PLUS, LDCI(4), MINUS, LDCI(5), LE, LDCI(2), LDCI(3), POW, LDCI(5), NEG, NEG, NEG, IFELSE, DONE)
    )
  }
  "23) An expression" should "compile correctly" in {
    assert(compile(parse("(2+3)*4/2-1")) == List(LDCI(2), LDCI(3), PLUS, LDCI(4), TIMES, LDCI(2), DIV, LDCI(1), MINUS, DONE))
  }
  "24) An expression" should "compile correctly" in {
    assert(compile(parse("if (1+2*3-4 <= 5) then true else false")) == List(LDCI(1), LDCI(2), LDCI(3), TIMES, PLUS, LDCI(4), MINUS, LDCI(5), LE, LDCB(true), LDCB(false), IFELSE, DONE))
  }
  "25) An expression" should "compile correctly" in {
    assert(compile(parse("2+3*4 < 1000 / 2")) == List(LDCI(2), LDCI(3), LDCI(4), TIMES, PLUS, LDCI(1000), LDCI(2), DIV, LT, DONE))
  }
}