grammar Rule;

@header {
package ext.antlr4.expr.entity;
}

parse
 : expr+
 ;

expr
 /* children expression */
 : OPAR expr CPAR                                    #childExpr
 
 | DLLR OMBRAC expr CMBRAC                           #mutilMsgExpr
 
 | IF OPAR expr CPAR OBRACE expr CBRACE (ELSEIF OPAR expr CPAR OBRACE expr CBRACE)* (ELSE OBRACE expr CBRACE)? #ifExpr
 | expr op=(IN|NOTIN) OPAR (expr COMMA)*? expr CPAR  #inExpr
 
 | expr LIKE (MOD expr MOD | MOD expr | expr MOD)    #likeExpr
 
 /* atomic elements, work with 'FelEngine' */
 | atom                                              #atomExpr
 
 | <assoc=right>expr POW expr                        #powExpr
 | MINUS expr                                        #unaryMinusExpr
 | NOT expr                                          #notExpr
 
 | expr op=(MULT | DIV | MOD) expr                   #multiplicationExpr
 | expr op=(PLUS | MINUS) expr                       #additiveExpr
 | expr op=(LTEQ | GTEQ | LT | GT) expr              #relationalExpr
 | expr op=(EQ | NEQ) expr                           #equalityExpr
 | expr AND expr                                     #andExpr
 | expr OR expr                                      #orExpr
 /*| OTHER {System.err.println("unknown char: " + $OTHER.text);} #blank */
 ;

/*
if_stat
 : IF condition_block (ELSE IF condition_block)* (ELSE stat_block)?
 ;

condition_block
 : OPAR stat CPAR stat_block
 ;

stat_block
 : OBRACE stat CBRACE
 | stat
 ;
 */
 
/* in or not in statement */
/* 
in_stat
 : expr op=(IN|NOTIN) OPAR in_block CPAR
 ;
 
in_block
 : (expr COMMA)*? expr
 ;
*/

/*
log
 : LOG expr SCOL
 ;
 */
 
/*
fel
 : <assoc=right>fel POW fel           #powExpr
 | MINUS fel                          #unaryMinusExpr
 | NOT fel                            #notExpr
 | fel op=(MULT | DIV | MOD) fel      #multiplicationExpr
 | fel op=(PLUS | MINUS) fel          #additiveExpr
 | fel op=(LTEQ | GTEQ | LT | GT) fel #relationalExpr
 | fel op=(EQ | NEQ) fel              #equalityExpr
 | fel AND fel                        #andExpr
 | fel OR fel                         #orExpr
 | (INT | FLOAT)                      #numberAtom
 | (TRUE | FALSE)                     #booleanAtom
 | ID                                 #idAtom
 | STRING                             #stringAtom
 ;
*/
/*
like
 : expr LIKE MOD expr MOD      #contianLike
 | expr LIKE MOD expr          #startLike
 | expr LIKE expr MOD          #endLike
 ;
 */
atom
 : DICT           #dictAtom
 /* | (INT | FLOAT)  #numberAtom*/
 | INT            #intAtom
 | FLOAT          #floatAtom
 | (TRUE | FALSE) #booleanAtom
 | ID             #idAtom
 | STRING         #stringAtom
 ;

OR : '||';
AND : '&&';
EQ : '==';
NEQ : '!=';
GT : '>';
LT : '<';
GTEQ : '>=';
LTEQ : '<=';
PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
MOD : '%';
POW : '^';
NOT : '!';
DLLR: '$';

SCOL : ';';
ASSIGN : '=';
OPAR : '(';
CPAR : ')';
OBRACE : '{';
CBRACE : '}';
OMBRAC : '[';
CMBRAC : ']';

TRUE : 'true';
FALSE : 'false';
/*NIL : 'nil';*/
IF : 'if';
ELSEIF : 'else if';
ELSE : 'else';
/*WHILE : 'while';*/
/*LOG : 'log';*/

IN : 'in';
NOTIN : 'not in';
COMMA : ',';

LIKE : 'like';

DICT
 : '_' [0-9 _]+ ('.' [0-9 _]+)? '_'
 | '_' 'r' [0-9]+ '_'
 ;

ID
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

INT
 : [0-9]+
 ;

FLOAT
 : [0-9]+ '.' [0-9]*
 | '.' [0-9]+
 ;

STRING
 : '"' (~["\r\n] | '""')* '"'
 | '\'' (~[\\'\r\n])* '\''
 | ([a-zA-Z_0-9] | '\u4e00'..'\u9fa5')+
 ;

COMMENT
 : '#' ~[\r\n]* -> skip
 ;

SPACE
 : [ \t\r\n] -> skip
 ;

OTHER
 : .
 ;