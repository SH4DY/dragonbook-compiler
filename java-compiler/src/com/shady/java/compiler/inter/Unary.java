package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 21/05/15.
 */
public class Unary extends Op {
    public Expr mExpr;

    public Unary(Token token, Expr expr) { //handles "minus"
        super(token, null);
        mExpr = expr;
        mType = Type.max(Type.Int, expr.mType);
        if (mType == null) error("type error");
    }

    public Expr gen(){
        return new Unary(mOp, mExpr.reduce());
    }

    public String toString(){
        return mOp.toString() + " " + mExpr.toString();
    }
}
