package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference.ReferenceType;
import com.bibtextingcompany.domain.Reference;
import java.util.List;

/**
 * A command line based user interface.
 */
public class ConsoleUI {
    
    private final IO io;
    private final ReferenceDatabase refDB;

    /**
     * Creates a new ConsoleUI object
     *
     * @param io I/O method used by the user interface.
     * @param refDB A ReferenceDatabase Object where all the references are
     * stored.
     */
    public ConsoleUI(IO io, ReferenceDatabase refDB) {
        this.io = io;
        this.refDB = refDB;
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
        io.print("add\n");
        io.print("exit\n");
        io.print("help\n");
        io.print("view\n");
        
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
        } else if (command.equals("add")) {
            viewTypes();
            String subcommand = io.readLine();
            if (subcommand.equals("back")) {
                help();
            } else if (!validType(subcommand)) {
                processCommand("add");
            } else {
                ReferenceType type = ReferenceType.values()[Integer.parseInt(subcommand) - 1];
                add(type);
            }
        } else {
            io.print("Invalid command; type help for a list of commands\n");
        }
        return false;
    }
    
    private boolean validType(String subcommand) {
        int typeNumber = 0;
        try {
            typeNumber = Integer.parseInt(subcommand);
        } catch (Exception e) {
            return false;
        }
        return typeNumber >= 1 && typeNumber <= ReferenceType.values().length;
    }
    
    private void viewTypes() {
        io.print("enter type number or back to return to main menu\n");
        ReferenceType[] types = ReferenceType.values();
        for (int i = 0; i < types.length; i++) {
            io.print(i + 1 + ": " + types[i].toString().toLowerCase() + "\n");
        }
        io.print("\n> ");
    }
    
    private void add(ReferenceType type) {
        String[] args = new String[24];
        Reference reference = new Reference(type);
        for (int i = 0; i < reference.requiredParameters().length; i++) {
            io.print(reference.paramNames[reference.requiredParameters()[i]] + ": ");
            args[reference.requiredParameters()[i]] = io.readLine();
        }
        reference.setParameters(args);
        this.refDB.add(reference);
    }

    // asks the user for a title and searches for a reference in the database with a matching title
    private void view() {
        io.print("Enter title (use * to match partial string): ");
        String title = io.readLine();
        printResults(refDB.find(title));
    }
    
    private void printResults(List<Reference> references) {
        if (references.isEmpty()) {
            io.print("No references found with the specified search terms!\n");
        }
        
        for (Reference reference : references) {
            io.print(reference.toString());
        }
    }
    
}
