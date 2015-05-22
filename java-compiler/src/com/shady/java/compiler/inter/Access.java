package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Tag;
import com.shady.java.compiler.lexer.Word;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 22/05/15.
 */
public class Access extends Op {
    public Id mArray;
    public Expr mIndex;
    public Access(Id a, Expr i, Type p){
        super(new Word("[]", Tag.INDEX), p);
        mArray = a;
        mIndex = i;
    }

    public Expr gen(){
        return new Access(mArray, mIndex.reduce(), mType);
    }

    public void jumping(int t, int f){
        emitJumps(reduce().toString(), t,f);
    }

    public String toString(){
        return mArray.toString()+" [ " + mIndex.toString() + " ]";
    }
}
