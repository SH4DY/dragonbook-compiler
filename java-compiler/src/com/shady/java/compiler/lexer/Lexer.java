package com.shady.java.compiler.lexer;

/**
 * Scanner for the following language, taken from the Dragon Book Appendix A
 *
 * program -> block
 * block -> {decls stmts}
 * decls -> decls decl | nil
 * decl -> type id ;
 * type -> type[ num ] | basic
 * stmts -> stmts stmt | nil
 *
 * stmt -> loc = bool;
 *          | if ( bool ) stmt
 *          | if ( bool ) stmt else stmt
 *          | while ( bool ) stmt
 *          | do stmt while ( bool ) ;
 *          | break;
 *          | block
 * loc -> loc [ bool ] | id
 *
 * bool -> bool || join | join
 * join -> join && equality | equality
 * equality -> equality == rel | equality != rel | rel
 * rel -> expr < expr | expr <= expr | expr >= expr | expr > expr | expr
 * term -> term * unary | term / unary | unary
 * unary -> ! unary | - unary | factor
 * factor -> (bool) | loc | num | real | true | false
 * 
 * Created by shady on 20/05/15.
 */
public class Lexer {
}
