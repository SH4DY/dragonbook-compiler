package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Num;
import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.lexer.Word;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 22/05/15.
 */
public class Constant extends Expr {
    public Constant(Token token, Type p){
        super(token, p);
    }

    public Constant(int i){
        super(new Num(i), Type.Int);
    }

    public static final Constant _true = new Constant(Word._true, Type.Bool);
    public static final Constant _false = new Constant(Word._false, Type.Bool);

    public void jumping(int t, int f){
        if(this == _true && t != 0) emit("goto L" + t);
        else if(this == _false && f != 0) emit ("goto L" + f);
    }
}
