package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;

/**
 * Created by shady on 22/05/15.
 */
public class And extends Logical {
    public And(Token tok, Expr x1, Expr x2){
        super(tok, x1, x2);
    }

    public void jumping(int t, int f){
        int label = f != 0 ? f : newlabel();
        mExpr1.jumping(0, label);
        mExpr2.jumping(t, f);
        if(f == 0) emitlabel(label);
    }
}
