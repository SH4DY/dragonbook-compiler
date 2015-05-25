package com.shady.java.compiler.inter;

import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 25/05/15.
 */
public class While extends Stmt{
    Expr mExpr;
    Stmt mStmt;

    public While(){
        mExpr = null;
        mStmt = null;
    }

    public void init(Expr x, Stmt s){
        mExpr = x;
        mStmt = s;
        if(mExpr.mType != Type.Bool) mExpr.error("boolean required in a while");
    }

    public void gen(int b, int a){
        after = a;
        mExpr.jumping(0, a);
        int label = newlabel();
        emitlabel(label);
        mStmt.gen(label, b);
        emit("goto L" + b);
    }
}
