package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;

/**
 * Created by shady on 22/05/15.
 */
public class Or extends Logical {
    public Or(Token tok, Expr x1, Expr x2){
        super(tok, x1, x2);
    }

    public void jumping(int t, int f){
        int label = t != 0 ? t : newlabel();
        mExpr1.jumping(label, 0);
        mExpr2.jumping(t,f);
        if(t==0) emitlabel(label);
    }
}
