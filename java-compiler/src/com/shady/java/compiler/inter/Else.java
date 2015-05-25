package com.shady.java.compiler.inter;

import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 25/05/15.
 */
public class Else extends Stmt {
    Expr mExpr;
    Stmt mStmt1;
    Stmt mStmt2;

    public Else(Expr expr, Stmt stmt1, Stmt stmt2){
        mExpr = expr;
        mStmt1 = stmt1;
        mStmt2 = stmt2;
        if(expr.mType != Type.Bool) mExpr.error("boolean required in if");
    }

    public void gen(int b, int a){
        int label1 = newlabel();
        int label2 = newlabel();
        mExpr.jumping(0, label2);
        emitlabel(label1); mStmt1.gen(label1, a); emit("goto L" + a);
        emitlabel(label2); mStmt1.gen(label2, a);
    }
}
