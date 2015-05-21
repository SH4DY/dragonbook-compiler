package com.shady.java.compiler.symbols;

import com.shady.java.compiler.inter.Id;
import com.shady.java.compiler.lexer.Token;

import java.util.HashMap;

/**
 * Created by shady on 21/05/15.
 */
public class Env {
    private HashMap<Token, Id> mSymtable;
    protected Env mPrev;

    public Env(Env prev){
        mPrev = prev;
        mSymtable = new HashMap<Token, Id>();
    }
    public void put(Token token, Id id){
        mSymtable.put(token, id);
    }

    public Id get(Token token){
        for(Env e = this; e != null; e = e.mPrev){
            Id id = e.mSymtable.get(token);
            if(id != null) return id;
        }
        return null;
    }
}
