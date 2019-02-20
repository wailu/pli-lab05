grammar ExpressionParser;
expr:   ('-'|'!') expr
    |   expr ('*'|'/'| '^') expr
    |   expr ('+'|'-') expr
    |   expr ('<'|'<='|'>'|'>=') expr
    |   expr ('=='|'!=') expr
    |   expr '&' expr
    |   expr '|' expr
    |   INT
    |   BOOL
    |   '(' expr ')'
    |   'if' expr 'then' expr 'else' expr
    ;
WH      : [ \t]+ -> skip;
INT     : [0-9]+ ;
BOOL    : 'true'|'false' ;