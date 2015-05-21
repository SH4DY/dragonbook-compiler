package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Lexer;

/**
 * Represents nodes in the syntax tree.
 * This class furthermore saves the line number for error reporting reasons
 * Created by shady on 21/05/15.
 */
public class Node {
    int mLexline = 0;

    Node(){
        mLexline = Lexer.line;
    }

    void error(String e){
        throw new Error("near line " + mLexline + ": " + e);
    }

    static int labels = 0;

    public int newlabel(){
        return ++labels;
    }

    public void emitlabel(int i){
        System.out.print("L" + i + ":");
    }

    public void emit(String s){
        System.out.println("\t" + s);
    }
}
