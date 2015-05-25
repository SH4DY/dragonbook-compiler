package com.shady.java.compiler.inter;

import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 25/05/15.
 */
public class If extends Stmt {

    Expr mExpr;
    Stmt mStmt;

    public If(Expr x, Stmt s){
        if(mExpr.mType != Type.Bool) mExpr.error("boolean required i if");
    }

    public void gen(int b, int a){
        int label = newlabel();
        mExpr.jumping(0, a);
        emitlabel(label);
        mStmt.gen(label, a);
    }
}
