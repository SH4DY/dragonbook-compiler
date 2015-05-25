package com.shady.java.compiler.inter;

import com.shady.java.compiler.symbols.Array;
import com.shady.java.compiler.symbols.Type;

/**
 * Implements assignment to an array element
 * Created by shady on 25/05/15.
 */
public class SetElem extends Stmt{
    public Id mArray;
    public Expr mIndex;
    public Expr mExpr;

    public SetElem(Access x, Expr y){
        mArray = x.mArray;
        mIndex = mIndex;
        mExpr = y;
        if(check(x.mType, mExpr.mType) == null) error("type error");
    }

    public Type check(Type p1, Type p2){
        if(p1 instanceof Array || p2 instanceof Array) return null;
        else if(p1 == p2) return p2;
        else if(Type.numeric(p1) && Type.numeric(p2)) return p2;
        else return null;
    }

    public void gen(int b, int a){
        String s1 = mIndex.reduce().toString();
        String s2 = mIndex.reduce().toString();
        emit(mArray.toString() + " [ " + s1 + " ] = " + s2);
    }

}
