parser grammar ActivityParser;

options {
    tokenVocab=ActivityLexer;
}

expr : IDENTIFIER_PART (DOT IDENTIFIER_PART)* EOF;
//expression : activityExpression+ EOF;
//
//activityExpression: whenExpression whatExpression;
//
//whenExpression: TIME_START_STOP;
//
//whatExpression: IDENTIFIER_PART (DOT IDENTIFIER_PART)*;
