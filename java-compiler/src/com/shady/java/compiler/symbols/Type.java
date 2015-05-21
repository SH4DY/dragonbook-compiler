package com.shady.java.compiler.symbols;

import com.shady.java.compiler.lexer.Tag;
import com.shady.java.compiler.lexer.Word;

/**
 * Created by shady on 20/05/15.
 */
public class Type extends Word {

    public static final Type Int = new Type("int", Tag.BASIC, 4);
    public static final Type Float = new Type("float", Tag.BASIC, 8);
    public static final Type Char = new Type("char", Tag.BASIC, 1);
    public static final Type Bool = new Type("bool", Tag.BASIC, 1);

    public int mWidth = 0;

    public Type(String lexeme, int tag, int width){
        super(lexeme, tag);
        mWidth=width;
    }

    public static boolean numeric(Type p){
        if(p == Type.Char ||p == Type.Int || p == Type.Float) return true;
        return false;
    }

    public static Type max(Type p1, Type p2){
        //cant make comparisons if on of those is not numeric
        if(!numeric(p1) || !numeric(p2)) return null;

        if(p1 == Type.Float || p2 == Type.Float) return Type.Float;
        if(p1 == Type.Int || p2 == Type.Int) return Type.Int;
        return Type.Char;
    }
}
