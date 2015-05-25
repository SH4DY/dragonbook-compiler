package com.shady.java.compiler.inter;

/**
 * A break statement sends control out of an eclosing loop
 * or switch statement.
 * Created by shady on 25/05/15.
 */
public class Break extends Stmt {
    Stmt mStmt;
    public Break(){
        if(Stmt.Enclosing == Stmt.Null) error("unenclosed break");
        mStmt = Stmt.Enclosing;
    }

    public void gen(int b, int a){
        emit("goto L" + mStmt.after);
    }
}
