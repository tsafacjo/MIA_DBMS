grammar SQL;

sqlStatement
    : selectStatement EOF
    ;

selectStatement
    : 'SELECT' columnList 'FROM' tableSource (joinClause)* ('WHERE' whereClause)?
    ;

subquery
    : '(' selectStatement ')'
    ;

columnList
    : '*' | columnItem (',' columnItem)*
    ;

columnItem
    : columnName
    | subquery
    ;

tableSource
    : tableName (alias)?
    | subquery
    ;

joinClause
    : joinType 'JOIN' tableSource 'ON' '(' condition ')'
    ;

joinType
    : 'INNER'
    | 'LEFT' ('OUTER')?
    | 'RIGHT' ('OUTER')?
    | 'FULL' ('OUTER')?
    ;

whereClause
    : condition (('AND' | 'OR') condition)*
    ;

condition
    : '(' condition ')'
    | columnName operator value
    | columnName operator columnName
    | columnName operator subquery
    | 'EXISTS' subquery
    | columnName ('IN' | 'NOT IN') subquery
    ;

operator
    : '=' | '>' | '<' | '>=' | '<=' | '<>'
    ;

value
    : NUMBER | STRING
    ;

columnName
    : qualifiedIdentifier
    ;

tableName
    : IDENTIFIER
    ;

alias
    : IDENTIFIER
    ;

qualifiedIdentifier
    : IDENTIFIER ('.' IDENTIFIER)?  // Support both qualified and unqualified identifiers
    ;

IDENTIFIER
    : [a-zA-Z_] [a-zA-Z_0-9]*
    ;

NUMBER
    : [0-9]+ ('.' [0-9]+)?
    ;

STRING
    : '\'' (~('\''))* '\''
    ;

WS
    : [ \t\r\n]+ -> skip
    ;
