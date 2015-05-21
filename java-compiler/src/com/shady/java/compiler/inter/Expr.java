package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 21/05/15.
 */
public class Expr extends Node {
    public Token mOp;
    public Type mType;

    public Expr(Token token, Type type){
        mOp = token;
        mType = type;
    }

    public Expr gen(){
        return this;
    }

    public Expr reduce(){
        return this;
    }

    public void jumping(int t, int f){
        emitJumps(toString(), t, f);
    }

    public void emitJumps(String test, int t, int f){
        if(t != 0 &&  f != 0){
            emit("if " + test + " goto L" + t);
            emit("goto L" + f);
        }
        else if(t != 0) emit("if " + test + " goto L" + t);
        else if(f != 0) emit("iffalse " + test + " goto L" + f);
        //neither t nor f fall through, do nothing
    }

    public String toString(){
        return mOp.toString();
    }
}
