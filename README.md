This code supports the Programming Language Implementation course
It contains a bunch of APIs for computing various semantics described in the course.
Examples for using these APIs are in the corresponding test packages.

In this tutorial, we will be practicing 
-- the Denotational Semantics (to generate the virtual machine code). 
-- execute the virtual machine code.
To finish this tutorial, you need to:

1) compiler all the code and run all the test cases.
Upon cloning the project, simply do
```
sbt test
```
Alternatively, you may import the code into IntelliJ as an Scala `sbt`. Make sure that you have the Scala and Antlr plugins loaded before doing that. Then you can just run the tests (methods in classes whose names have the suffix 'Test') individually.
Each test is an example of how the language APIs can be used.

2) fill up all the pattern matching blanks in 2 files:
 -- /src/main/scala/edu/nus/comp/pli/epl/denotationalsemantics/EplDenotationalSemantics.scala
 -- src/main/scala/edu/nus/comp/pli/epl/evm/EvmExec.scala
 To ease your work, all the blanks are marked with comment "//add your code"
 
3) after programming, all the test cases are expected to be passed. 

(Note: the AST of this expression language is defined in 
/src/main/antlr4/epl/ExpressionParser.g4)

---------------------------------------------------------------- * 
Origin repository: https://github.com/razvanvoicu/pli
Revised by:        Yahui Song  (e0210374@u.nus.edu)  
Date:              16/2/2019                            
Purpose:           Tutorial for CS4215 (2018/2019 sem2) 
---------------------------------------------------------------- * 
Submission: please submit a compressed folder which is renamed with: "Lab05_<ID>_<Name>"
eg. Lab05_A12345678_Yahui.zip

Deadline: 1/03/2019 23:59pm