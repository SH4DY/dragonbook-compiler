package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 21/05/15.
 */
public class Op extends Expr{
    public Op(Token token, Type p){
        super(token, p);
    }

    public Expr reduce(){
        Expr expr = gen();
        Temp temp = new Temp(mType);
        emit(temp.toString() + " = " + expr.toString());
        return temp;
    }
}
