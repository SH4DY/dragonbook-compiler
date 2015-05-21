package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 21/05/15.
 */
public class Arith extends Op{
    public Expr mExpr1, mExpr2;

    public Arith(Token token, Expr expr1, Expr expr2) {
        super(token, null);
        mExpr1 = expr1;
        mExpr2 = expr2;
        mType = Type.max(expr1.mType, expr2.mType);
        if(mType == null) error("type error");
    }

    public Expr gen(){
        return new Arith(mOp, mExpr1.reduce(), mExpr2.reduce());
    }

    public String toString(){
        return mExpr1.toString() + " " + mOp.toString() + " "+mExpr2.toString();
    }

}
