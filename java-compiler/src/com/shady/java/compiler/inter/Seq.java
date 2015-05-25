package com.shady.java.compiler.inter;

/**
 * Implements a sequence of statements
 * Created by shady on 25/05/15.
 */
public class Seq extends Stmt {
    Stmt mStmt1;
    Stmt mStmt2;

    public Seq(Stmt s1, Stmt s2){
        mStmt1 = s1;
        mStmt2 = s2;
    }

    public void gen(int b, int a){
        if(mStmt1 == Stmt.Null) mStmt2.gen(b,a);
        else if(mStmt2 == Stmt.Null) mStmt1.gen(b,a);
        else{
            int label = newlabel();
            mStmt1.gen(b, label);
            emitlabel(label);
            mStmt2.gen(label,a);
        }
    }
}
