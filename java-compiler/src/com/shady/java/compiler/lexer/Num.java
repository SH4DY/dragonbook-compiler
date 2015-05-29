package com.shady.java.compiler.lexer;

/**
 * Created by shady on 20/05/15.
 */
public class Num extends Token {
    public final int value;

    public Num(int v){
        super(Tag.NUM);
        value = v;
    }

    public String toString(){
        return "" + value;
    }
}
