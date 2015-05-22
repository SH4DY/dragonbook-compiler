package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.symbols.Array;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 22/05/15.
 */
public class Rel extends Logical{
    public Rel(Token tok, Expr x1, Expr x2){
        super(tok, x1, x2);
    }

    public Type check(Type p1, Type p2){
        if(p1 instanceof Array || p2 instanceof Array) return null;
        if(p1 == p2) return Type.Bool;
        return null;
    }

    public void jumping(int t, int f){
        Expr a = mExpr1.reduce();
        Expr b = mExpr2.reduce();

        String test = a.toString() + " " + mOp.toString() + " " + b.toString();
        emitJumps(test, t, f);
    }
}
