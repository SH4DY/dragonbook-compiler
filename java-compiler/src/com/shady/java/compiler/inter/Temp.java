package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.Token;
import com.shady.java.compiler.lexer.Word;
import com.shady.java.compiler.symbols.Type;

/**
 * Class representing unary opeators (like the minus in front of numerics)
 * Created by shady on 21/05/15.
 */
public class Temp extends Expr {
    static int count = 0;
    int number = 0;

    public Temp(Type p){
        super(Word.temp, p);
        number = count++;
    }

    public String toString(){
        return "t" + number;
    }
}
