package edu.nus.comp.pli.epl.parser

import org.scalatest.FlatSpec
import EplAST._
import EplParser._

class ParserTest extends FlatSpec {

  "1) The parser" should "parse an integer" in {
    assert(
      parse("123") == Num(123)
    )
  }

  "2) The parser" should "parse true" in {
    assert(
      parse("true") == Bool(true)
    )
  }

  "3) The parser" should "parse false" in {
    assert(
      parse("false") == Bool(false)
    )
  }

  "4) The parser" should "parse a simple addition" in {
    assert(
      parse("1 + true ") == Bin(Add, Num(1), Bool(true))
    )
  }


  "5) The parser" should "parse a combination of addition and unary minus" in {
    assert(
      parse("1+-true") == Bin(Add, Num(1), Ury(Minus, Bool(true)))
    )
  }

  "6) The parser" should "parse a combination of binary and unary minus" in {
    assert(
      parse("1--true") == Bin(Sub, Num(1), Ury(Minus, Bool(true)))
    )
  }

  "7) The parser" should "parse operator precedence correctly" in {
    assert(
      parse("1+2*3-4 <= 5") ==
        Bin(
          Lte,
          Bin(
            Sub,
            Bin(
              Add,
              Num(1),
              Bin(
                Mul,
                Num(2),
                Num(3)
              )
            ),
            Num(4)),
          Num(5)
        )
    )
  }

  "8) Brackets" should "change the order of operations" in {
    assert(
      parse("(1+2)*(4-3)") ==
        Bin(
          Mul,
          Bin(
            Add,
            Num(1),
            Num(2)
          ),
          Bin(
            Sub,
            Num(4),
            Num(3)
          )
        )
    )
  }

  "9) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("1 <= 2 & 4 == 5") ==
        Bin(And,Bin(Lte,Num(1),Num(2)),Bin(Eq,Num(4),Num(5)))
    )
  }


  /////////////////////////////////

  "10) the parser" should "parse simply power operator" in {
    assert(
      parse("2^3") ==
        Bin(Pow,Num(2),Num(3))
    )
  }

  "11) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("false == (!true) & false | (2+3 ==4)") ==
        Bin(Or,Bin(And,Bin(Eq,Bool(false),Ury(Not,Bool(true))),Bool(false)),Bin(Eq,Bin(Add,Num(2),Num(3)),Num(4)))
    )
  }

  "12) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("false == 10 > 6") ==
        Bin(Eq,Bool(false),Bin(Gt,Num(10),Num(6)))
    )
  }

  "13) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("false == !true & false") ==
        Bin(And,Bin(Eq,Bool(false),Ury(Not,Bool(true))),Bool(false))
    )
  }

  "14) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("10 == 3 + 7") ==
        Bin(Eq,Num(10),Bin(Add,Num(3),Num(7)))
    )
  }

  "15) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("1+2*3+4") ==
        Bin(Add,Bin(Add,Num(1),Bin(Mul,Num(2),Num(3))),Num(4))
    )
  }

  "16) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("(1+2)*3") ==
        Bin(Mul,Bin(Add,Num(1),Num(2)),Num(3))
    )
  }
  "17) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("(1+2)*(4-3)") ==
        Bin(Mul,Bin(Add,Num(1),Num(2)),Bin(Sub,Num(4),Num(3)))
    )
  }

  "18) the parser" should "parse some Minus operator" in {
    assert(
      parse("---5") ==
        Ury(Minus,Ury(Minus,Ury(Minus,Num(5))))
    )
  }

  "19) the parser" should "parse some Minus operator" in {
    assert(
      parse("--5") ==
        Ury(Minus,Ury(Minus,Num(5)))
    )
  }

  "20) the parser" should "parse combinations of comparisons" in {
    assert(
      parse("false == (!true) | false & (2+3 ==4)") ==
        Bin(Or,Bin(Eq,Bool(false),Ury(Not,Bool(true))),Bin(And,Bool(false),Bin(Eq,Bin(Add,Num(2),Num(3)),Num(4))))
    )
  }

  "21) the parser" should "parse is else statement" in {
    assert(
      parse("if true then 2 else 3") ==
        IfElse(Bool(true),Num(2),Num(3))
    )
  }

  "22) the parser" should "parse is else statement" in {
    assert(
      parse("if (1+2*3-4 <= 5) then 2^3 else ---5") ==
        IfElse(Bin(Lte,Bin(Sub,Bin(Add,Num(1),Bin(Mul,Num(2),Num(3))),Num(4)),Num(5)),Bin(Pow,Num(2),Num(3)),Ury(Minus,Ury(Minus,Ury(Minus,Num(5)))))
    )
  }

  "23) the parser" should "parse is else statement" in {
    assert(
      parse("(2+3)*4/2-1") ==
        Bin(Sub,Bin(Div,Bin(Mul,Bin(Add,Num(2),Num(3)),Num(4)),Num(2)),Num(1))
    )
  }
  "24) the parser" should "parse is else statement" in {
    assert(
      parse("if (1+2*3-4 <= 5) then true else false") ==
        IfElse(Bin(Lte,Bin(Sub,Bin(Add,Num(1),Bin(Mul,Num(2),Num(3))),Num(4)),Num(5)),Bool(true),Bool(false))
    )
  }
  "25) the parser" should "parse is else statement" in {
    assert(
      parse("2^true") ==
        Bin(Pow,Num(2),Bool(true))
    )
  }
}
