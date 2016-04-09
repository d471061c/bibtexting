package com.bibtextingcompany.bibtexting;

/**
 *
 * @author Eero
 */
public class UI {

    private final IO io;

    public UI(IO io) {
        this.io = io;
    }

    public void run() {
        help();

        while (true) {
            io.println("");
            io.print("> ");
            String command = io.readLine();
            if (processCommand(command)) {
                break;
            }
        }
    }

    private void help() {
        io.println("Available commands:");
        io.println("view");
        io.println("exit");
        io.println("help");
    }

    private boolean processCommand(String command) {
        if (command.equals("exit")) {
            return true;
        } else if (command.equals("view")) {
            view();
        } else if (command.equals("help")) {
            help();
        }
        return false;
    }

    private void view() {
        io.print("Enter title: ");
        String title = io.readLine();
        System.out.println("TBD");
    }

}
