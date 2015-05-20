package com.shady.java.compiler.lexer;

/**
 * Represents real numbers
 * Created by shady on 20/05/15.
 */
public class Real extends Token{
    public final float mValue;

    public Real(float value){
        super(Tag.REAL);
        mValue = value;
    }

    public String toString(){
        return mValue+"";
    }
}
