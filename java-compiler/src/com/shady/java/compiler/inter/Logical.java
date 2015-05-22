package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 22/05/15.
 */
public class Logical extends Expr {
    public Expr mExpr1, mExpr2;

    public Logical(Token tok, Expr x1, Expr x2){
        super(tok, null);
        mExpr1 = x1;
        mExpr2 = x2;
        mType = check(mExpr1.mType, mExpr2.mType);
        if(mType == null) error("type error");
    }

    public Type check(Type p1, Type p2){
        if(p1 == Type.Bool && p2 == Type.Bool) return Type.Bool;
        else return null;
    }

    public Expr gen(){
        int f = newlabel();
        int a = newlabel();
        Temp temp = new Temp(mType);
        this.jumping(0, f);
        emit(temp.toString() + " = true");
        emit("goto L" + a);
        emitlabel(f);
        emit(temp.toString() + " = false" );
        emitlabel(a);
        return temp;
    }

    public String toString(){
        return mExpr1.toString() +  " " + mOp.toString()+" "+mExpr2.toString();
    }
}
