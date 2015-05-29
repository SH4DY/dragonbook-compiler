package com.shady.java.compiler.reader;

import java.io.IOException;

/**
 * Reads characters from standard in. Modularized for easy change
 * for file reading (later to be implemented)
 * Created by shady on 20/05/15.
 */
public class Reader {

    public static char nextChar() {
        try {
            return (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ' ';
    }
}
