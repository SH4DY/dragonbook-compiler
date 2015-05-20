package com.shady.java.compiler.lexer;

/**
 * Created by shady on 20/05/15.
 */
public class Word extends Token {

    /**
     * Reserved words
     */
    public static final Word and = new Word("&&", Tag.AND);
    public static final Word or = new Word("||", Tag.OR);
    public static final Word eq = new Word("==", Tag.EQ);
    public static final Word neq = new Word("!=", Tag.NEQ);
    public static final Word leq = new Word("<=", Tag.LEQ);
    public static final Word geq = new Word(">=", Tag.GEQ);
    public static final Word minus = new Word("minus", Tag.MINUS);
    public static final Word _true = new Word("true", Tag.TRUE);
    public static final Word _false = new Word("false", Tag.FALSE);
    public static final Word temp = new Word("temp", Tag.TEMP);
    /**
     * _________________________
     */

    String mLexeme;

    public Word(String lexeme, int tag){
        super(tag);
        mLexeme = lexeme;
    }
}
