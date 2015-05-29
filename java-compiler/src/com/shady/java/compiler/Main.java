package com.shady.java.compiler;

import com.shady.java.compiler.inter.Parser;
import com.shady.java.compiler.lexer.Lexer;

/**
 * Created by shady on 29/05/15.
 */
public class Main {
    public static void main(String[] args){
        Lexer lex = new Lexer();
        Parser parse = new Parser(lex);
        parse.program();
        System.out.write('\n');
    }
}
