
package com.bibtextingcompany.bibtexting;

import java.util.Scanner;

/**
 * Class which provides I/0 through the console
 */
public class ConsoleIO implements IO{
    private final Scanner scanner;

    /**
     * Creates a new ConsoleIO object
     */
    public ConsoleIO() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void print(String msg) {
        System.out.print(msg);
    } 
}
