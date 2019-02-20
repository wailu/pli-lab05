package edu.nus.comp.pli.epl.evm

import org.scalatest.FlatSpec

import edu.nus.comp.pli.epl.bigstepsemantics.EplBigStepSemantics._

import edu.nus.comp.pli.epl.parser.EplParser.parse
import edu.nus.comp.pli.epl.denotationalsemantics.EplDenotationalSemantics.compile


class EvmTest extends FlatSpec {

  "1) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("123")), List.empty).execute == ResultInt(123)
    )
  }
  "2) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("true")), List.empty).execute == ResultBool(true)
    )
  }
  "3) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("false")), List.empty).execute == ResultBool(false)
    )
  }
  "4) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("1+2")), List.empty).execute == ResultInt(3)
    )
  }
  "5) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("1-2")), List.empty).execute == ResultInt(-1)
    )
  }
  "6) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("2-1")), List.empty).execute == ResultInt(1)
    )
  }
  "7) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("1+2*3-4 <= 5")), List.empty).execute == ResultBool(true)
    )
  }
  "8) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("(1+2)*(4-3)")), List.empty).execute == ResultInt(3)
    )
  }
  "9) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("1 <= 2 & 4 == 5")), List.empty).execute == ResultBool(false)
    )
  }
  "10) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("2^3")), List.empty).execute == ResultInt(8))
  }
  "11) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("false == (!true) & false | (2+3 ==4)")), List.empty).execute == ResultBool(false))
  }
  "12) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("false == 10 > 6")), List.empty).execute ==ResultBool(false))
  }
  "13) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("false == !true & false")), List.empty).execute ==ResultBool(false))
  }
  "14) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("10 == 3 + 7")), List.empty).execute ==ResultBool(true))
  }
  "15) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("1+2*3+4")), List.empty).execute ==ResultInt(11))
  }

  "16) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("(1+2)*3")), List.empty).execute ==ResultInt(9))
  }
  "17) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("(1+2)*(4-3)")), List.empty).execute ==ResultInt(3))
  }
  "18) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("---5")), List.empty).execute ==ResultInt(-5))
  }
  "19) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("--5")), List.empty).execute ==ResultInt(5))
  }
  "20) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("false == (!true) | false & (2+3 ==4)")), List.empty).execute ==ResultBool(true))
  }
  "21) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("if true then 2 else 3")), List.empty).execute ==ResultInt(2))
  }
  "22) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("if (1+2*3-4 <= 5) then 2^3 else ---5")), List.empty).execute ==ResultInt(8))
  }
  "23) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("(2+3)*4/2-1")), List.empty).execute ==ResultInt(9))
  }
  "24) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("if (1+2*3-4 <= 5) then true else false")), List.empty).execute ==ResultBool(true))
  }
  "25) A simple operation" should "evaluate correctly" in {
    assert(
      EvmState(compile(parse("2+3*4 < 1000 / 2")), List.empty).execute ==ResultBool(true))
  }



}