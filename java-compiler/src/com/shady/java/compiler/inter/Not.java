package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;

/**
 * Created by shady on 22/05/15.
 */
public class Not extends Logical {
    public Not(Token tok, Expr x2){
        super(tok, x2, x2);
    }

    public void jumping(int t, int f){
        mExpr2.jumping(f, t);
    }

    public String toString(){
        return mOp.toString()+" "+mExpr2.toString();
    }
}
