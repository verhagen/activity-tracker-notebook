lexer grammar ActivityLexer;

LBRACKET                    : '[';
RBRACKET                    : ']';

COLON                       : ':';
HYPHEN                      : '-';
DOT                         : '.';

IDENTIFIER_PART             : [a-z0-9-]+
                            ;

//date
fragment YEAR               : DIGIT DIGIT DIGIT DIGIT;
fragment MONTH              : [1-9]
                            | '0' [1-9]
                            | '1' [0-2]
                            ;

fragment DAY                : [1-9]
                            | '0' [1-9]
                            | [1-2] [0-9]
                            | '3' [0-1]
                            ;

fragment DATE               : YEAR '.' MONTH '.' DAY;
fragment TIME               : DIGIT DIGIT COLON DIGIT DIGIT (COLON DIGIT DIGIT)?;

TIME_START_STOP    : DIGIT? DIGIT COLON DIGIT DIGIT HYPHEN DIGIT? DIGIT COLON DIGIT DIGIT;

TIME_START         : DIGIT? DIGIT COLON DIGIT DIGIT HYPHEN;
TIME_STOP          : HYPHEN DIGIT? DIGIT COLON DIGIT DIGIT;

//numbers
fragment DIGIT              : [0-9];

fragment PUNCTUATION_CHAR   : [.];

//whitespace
WS                          : [ \t\r\n\u000C]+ -> skip;