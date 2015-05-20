package com.shady.java.compiler.lexer;

import com.shady.java.compiler.reader.Reader;

import java.util.HashMap;

/**
 * Scanner for the following language, taken from the Dragon Book Appendix A
 *
 * program -> block
 * block -> {decls stmts}
 * decls -> decls decl | nil
 * decl -> type id ;
 * type -> type[ num ] | basic
 * stmts -> stmts stmt | nil
 *
 * stmt -> loc = bool;
 *          | if ( bool ) stmt
 *          | if ( bool ) stmt else stmt
 *          | while ( bool ) stmt
 *          | do stmt while ( bool ) ;
 *          | break;
 *          | block
 * loc -> loc [ bool ] | id
 *
 * bool -> bool || join | join
 * join -> join && equality | equality
 * equality -> equality == rel | equality != rel | rel
 * rel -> expr < expr | expr <= expr | expr >= expr | expr > expr | expr
 * term -> term * unary | term / unary | unary
 * unary -> ! unary | - unary | factor
 * factor -> (bool) | loc | num | real | true | false
 *
 * Any context-free grammar can be parsed in O(n^3) but effectively
 * all real programming languages can be parsed in linear time.
 *
 * Created by shady on 20/05/15.
 */
public class Lexer {
    public static int line = 1;
    char peek = ' ';

    HashMap<String, Word> words = new HashMap<String, Word>();

    public void reserveWord(Word w){
        words.put(w.mLexeme, w);
    }

    public Lexer(){
        reserveWord(new Word("if", Tag.IF));
        reserveWord(new Word("else", Tag.ELSE));
        reserveWord(new Word("while", Tag.WHILE));
        reserveWord(new Word("do", Tag.DO));
        reserveWord(new Word("break", Tag.BREAK));
        reserveWord(Word._true);
        reserveWord(Word._false);
        reserveWord(Type.Int);
        reserveWord(Type.Bool);
        reserveWord(Type.Char);
        reserveWord(Type.Float);
    }

    void readch(){
        peek = Reader.nextChar();
    }

    boolean readch(char c){
        readch();
        if(peek != c) return false;
        peek = ' ';
        return true;
    }

    public Token scan(){
        //Skip spaces, tabs and new lines
        for(;;readch()){
            if(peek == ' ' || peek == '\t') continue;
            else if(peek == '\n') line++;
            else break;
        }

        //Check for composite tokens like != etc.
        switch(peek){
            case '&':
                if(readch('&')) return Word.and; else return new Token('&');
            case '|':
                if(readch('|')) return Word.or; else return new Token('|');
            case '=':
                if(readch('=')) return Word.eq; else return new Token('=');
            case '!':
                if(readch('=')) return Word.neq; else return new Token('!');
            case '<':
                if(readch('=')) return Word.leq; else return new Token('<');
            case '>':
                if(readch('=')) return Word.geq; else return new Token('>');
        }

        //Put together digits
        if(Character.isDigit(peek)){
            int v = 0;
            do{
                v = 10*v + Character.digit(peek, 10);
                readch();
            }while (Character.isDigit(peek));

            //Are we dealing with a comma value?
            if (peek != '.') return new Num(v);
            float x = v;
            float d = 10;
            for(;;){
                readch();
                if(!Character.isDigit(peek)) break;
                x *= Character.digit(peek, 10) / d;
                d *= 10;
            }
            return new Real(x);
        }

        //Read words or identifiers
        if(Character.isLetter(peek)){
            StringBuffer sb = new StringBuffer();
            do{
                sb.append(peek);
                readch();
            }while(Character.isLetterOrDigit(peek));

            String s = sb.toString();
            Word w = words.get(s);
            if(w == null){
                w = new Word(s, Tag.ID);
                words.put(s, w);
            }
            return w;
        }
        Token token = new Token(peek);
        peek = ' ';

        return token;
    }
}