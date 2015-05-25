package com.shady.java.compiler.inter;

import com.shady.java.compiler.symbols.Type;

/**
 * Implements assignment with an identifier on the left side and an expression
 * on the right side.
 * Created by shady on 25/05/15.
 */
public class Set extends Stmt{
    public Id mId;
    public Expr mExpr;

    public Set(Id id, Expr expr){
        mId = id;
        mExpr = expr;
        if(check(id.mType, mExpr.mType) == null) error("type error");
    }

    public Type check(Type p1, Type p2){
        if(Type.numeric(p1) && Type.numeric(p2)) return p2;
        else if(p1 == Type.Bool && p2 == Type.Bool) return p2;
        else return null;
    }

    public void gen(int b, int a){
        emit(mId.toString() + " = " + mExpr.gen().toString());
    }
}
