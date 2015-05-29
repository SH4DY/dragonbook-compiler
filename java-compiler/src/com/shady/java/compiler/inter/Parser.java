package com.shady.java.compiler.inter;

import com.shady.java.compiler.lexer.*;
import com.shady.java.compiler.symbols.Array;
import com.shady.java.compiler.symbols.Env;
import com.shady.java.compiler.symbols.Type;

/**
 * Created by shady on 29/05/15.
 */
public class Parser {
    private Lexer mLex;
    private Token mLook; //lookahead token
    Env top = null; //current or top symbol table
    int used = 0;
    public Parser(Lexer lex) {
        mLex = lex;
        move();
    }

    void move(){
        mLook = mLex.scan();
    }

    void error(String s){
        throw new Error("near line " + mLex.line +": " + s);
    }

    void match(int t) {
        if(mLook.mTag == t) move();
        else error("syntax error");
    }

    public void program(){ //program -> block
        Stmt s = block();
        int begin = s.newlabel();
        int after = s.newlabel();
        s.emitlabel(begin);
        s.gen(begin,after);
        s.emitlabel(after);
    }

    Stmt block(){ //block -> { decls stmts}
        match('{');
        Env savedEnv = top; //save link to previous symbol table
        top = new Env(top);
        decls();
        Stmt s = stmts();
        match('}');
        top = savedEnv; //after block switch to previous symbol table
        return s;
    }

    void decls(){
        while (mLook.mTag == Tag.BASIC) { //D -> type ID;
            Type p = type();
            Token tok = mLook;
            match(Tag.ID);
            match(';');
            Id id = new Id((Word) tok, p,used);
            top.put(tok, id);
            used = used + p.mWidth;
        }
    }

    Type type(){
        Type p = (Type)mLook; //expect look.tag == Tag.BASIC
        match(Tag.BASIC);
        if(mLook.mTag != '[') return p; //T -> basic
        else return dims(p); //return array type
    }

    Type dims(Type p){
        match('[');
        Token tok = mLook;
        match(Tag.NUM);
        match(']');
        if(mLook.mTag == '[') p = dims(p); //Nested array
        return new Array(((Num)tok).value, p);
    }

    Stmt stmts(){
        if(mLook.mTag == '}') return Stmt.Null;
        else return new Seq(stmt(), stmts());
    }

    Stmt stmt(){
        Expr x;
        Stmt s, s1, s2;
        Stmt savedStmt; //enclosing loop for breaks

        switch(mLook.mTag){
            case ';':
                move();
                return Stmt.Null;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                x = bool();
                match(')');
                s1 = stmt();
                if(mLook.mTag != Tag.ELSE){
                    return new If(x, s1);
                }
                match(Tag.ELSE);
                s2 = stmt();
                return new Else(x, s1, s2);
            case Tag.WHILE:
                While whilenode = new While();
                savedStmt = Stmt.Enclosing;
                Stmt.Enclosing = whilenode;
                match(Tag.WHILE);
                match('(');
                x = bool();
                match(')');
                s1 = stmt();
                whilenode.init(x, s1);
                Stmt.Enclosing = savedStmt; //reset Stmt.Enclosing
                return whilenode;
            case Tag.DO:
                Do donode = new Do();
                savedStmt = Stmt.Enclosing;
                Stmt.Enclosing = donode;
                match(Tag.DO);
                s1 = stmt();
                match(Tag.WHILE);
                match('(');
                x = bool();
                match(')');
                match(';');
                donode.init(s1, x);
                Stmt.Enclosing = savedStmt;
                return donode;
            case Tag.BREAK:
                match(Tag.BREAK);
                match(';');
                return new Break();
            case '{':
                return block();
            default:
                return assign();
        }
    }

    Stmt assign(){
        Stmt stmt;
        Token t = mLook;
        match(Tag.ID);
        Id id = top.get(t);
        if(id == null) error(t.toString() + " undeclared");
        if(mLook.mTag == '='){ //S -> id = E ;
            move();
            stmt = new Set(id, bool());
        }else{ //S -> L = E ;
            Access x = offset(id);
            match('=');
            stmt = new SetElem(x, bool());
        }
        match(';');
        return stmt;
    }

    Expr bool(){
        Expr x = join();
        while(mLook.mTag == Tag.OR){
            Token tok = mLook;
            move();
            x = new Or(tok, x, join());
        }
        return x;
    }

    Expr join(){
        Expr x = rel();
        while (mLook.mTag == Tag.EQ || mLook.mTag == Tag.NEQ){
            Token tok = mLook;
            move();
            x = new Rel(tok, x, rel());
        }
        return x;
    }

    Expr rel(){
        Expr x = expr();
        switch (mLook.mTag){
            case '<':
            case Tag.LEQ:
            case Tag.GEQ:
            case '>':
                Token tok = mLook;
                move();
                return new Rel(tok, x, expr());
            default:
                return x;
        }
    }

    Expr expr(){
        Expr x = term();
        while (mLook.mTag == '+' || mLook.mTag == '-'){
            Token tok = mLook;
            move();
            x = new Arith(tok, x, term());
        }
        return x;
    }

    Expr term(){
        Expr x = unary();
        while (mLook.mTag == '*' || mLook.mTag == '/'){
            Token tok = mLook;
            move();
            x = new Arith(tok, x, unary());
        }
        return x;
    }

    Expr unary(){
        if(mLook.mTag == '-'){
            move();
            return new Unary(Word.minus, unary());
        }else if(mLook.mTag == '!'){
            Token tok = mLook;
            move();
            return new Not(tok, unary());
        }
        else return factor();
    }

    Expr factor(){
        Expr x = null;
        switch (mLook.mTag){
            case '(':
                move();
                x = bool();
                match(')');
                return x;
            case Tag.NUM:
                x = new Constant(mLook, Type.Int);
                move();
                return x;
            case Tag.REAL:
                x = new Constant(mLook, Type.Float);
                move();
                return x;
            case Tag.TRUE:
                x = Constant._true;
                move();
                return x;
            case Tag.FALSE:
                x = Constant._false;
                move();
                return x;
            default:
                error("syntax error");
                return x;
            case Tag.ID:
                String s = mLook.toString();
                Id id = top.get(mLook);
                if(id == null) error(mLook.toString() + " undeclared");
                move();
                if(mLook.mTag != '[') return id;
                else return offset(id);
        }
    }

    Access offset(Id a){ //I -> [E] | [E] I
        Expr i;
        Expr w;
        Expr t1, t2;
        Expr loc; //inherit id
        Type type = a.mType;
        match('[');
        i = bool();
        match(']'); //first index, I -> [E]
        type = ((Array)type).mType;
        w = new Constant(type.mWidth);
        t1 = new Arith(new Token('*'), i, w);
        loc = t1;
        while (mLook.mTag == '[') { //nested array I -> [E] I
            match('[');
            i = bool();
            match(']');
            type = ((Array)type).mType;
            w = new Constant(type.mWidth);
            t1 = new Arith(new Token('*'), i, w);
            t2 = new Arith(new Token('+'), loc, t1);
            loc = t2;
        }
        return new Access(a, loc, type);
    }
}
