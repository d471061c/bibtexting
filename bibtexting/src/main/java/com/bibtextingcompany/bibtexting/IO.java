
package com.bibtextingcompany.bibtexting;

/**
 * Interface providing methods for data input/output
 */
public interface IO {

    /**
     * Reads a line of text from the user.
     * 
     * @return Input text from the user as a String
     */
    String readLine();

    /**
     * Prints a message to the output
     * 
     * @param msg A String message to be printed
     */
    void print(String msg);
}
