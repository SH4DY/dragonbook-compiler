package com.shady.java.compiler.inter;

import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 25/05/15.
 */
public class Do extends Stmt {
    Expr mExpr;
    Stmt mStmt;
    public Do(){
        mExpr = null;
        mStmt = null;
    }

    public void init(Stmt s, Expr x){
        mExpr = x;
        mStmt = s;
        if(mExpr.mType != Type.Bool) mExpr.error("boolean required in a while");
    }

    public void gen(int b, int a){
        after = a;
        int label = newlabel();
        mStmt.gen(b, label);
        emitlabel(label);
        mExpr.jumping(b, 0);
    }
}
