package com.shady.java.compiler.symbols;

import com.shady.java.compiler.lexer.Tag;

/**
 * Array is currently the only constructed type in the language.
 * The value of size which is passed to the superclass is used
 * for size calculations.
 * Created by shady on 21/05/15.
 */
public class Array extends Type{

    public Type mType;

    public int mSize = 1;

    public Array(int size, Type type){
        super("[]", Tag.INDEX, size*type.mWidth);
        mSize = size;
        mType = type;
    }

    public String toString(){
        return "[" + mSize +"] " + mType.toString();
    }
}
