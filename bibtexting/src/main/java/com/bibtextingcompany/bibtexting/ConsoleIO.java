
package com.bibtextingcompany.bibtexting;

import java.util.Scanner;

/**
 *
 * @author Eero
 */
public class ConsoleIO implements IO{
    private final Scanner scanner;

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

    @Override
    public void println(String msg) {
        System.out.println(msg);
    }     
}
