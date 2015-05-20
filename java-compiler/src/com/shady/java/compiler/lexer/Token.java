package com.shady.java.compiler.lexer;

/**
 * Created by shady on 20/05/15.
 */
public class Token {
    public final int mTag;

    public Token(int tag) {
        mTag = tag;
    }

    public String toString(){
        return "" + (char) mTag;
    }
}
