package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.lexer.Word;
import com.shady.java.compiler.symbols.Type;

/**
 * An identifier is represented by its relative address
 * Created by shady on 21/05/15.
 */
public class Id extends Expr{
    public int mOffset; //relative address

    public Id(Word word, Type type, int offset) {
        super(word, type);
        mOffset = offset;
    }
}
