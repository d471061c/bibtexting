package com.bibtextingcompany.bibtexting;

/**
 * 
 */
public class ConsoleUI {

    private final IO io;

    /**
     * Creates a new UI object
     * 
     * @param io I/O method used by the user interface
     */
    public ConsoleUI(IO io) {
        this.io = io;
    }

    /**
     * Starts up the user interface and prompts the user for inputs.
     */
    public void run() {
        help();

        while (true) {
            io.print("\n");
            io.print("> ");
            String command = io.readLine();
            if (processCommand(command)) {
                break;
            }
        }
    }

    private void help() {
        io.print("Available commands:\n");
        io.print("view\n");
        io.print("exit\n");
        io.print("help\n");
    }

    // Returns true if the user wishes to end program execution, false otherwise.
    private boolean processCommand(String command) {
        if (command.equals("exit")) {
            io.print("Goodbye!\n");
            return true;
        } else if (command.equals("view")) {
            view();
        } else if (command.equals("help")) {
            help();
        } else {
            io.print("Invalid command; type help for a list of commands\n");
        }
        return false;
    }

    private void view() {
        io.print("Enter title: ");
        String title = io.readLine();
        io.print("TBD");
    }

}
