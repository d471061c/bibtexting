package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Article;
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
     * @param refDB A ReferenceDatabase Object where all the references are stored.
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
            add();
        } else {
            io.print("Invalid command; type help for a list of commands\n");
        }
        return false;
    }
    
    private void add() {
       io.print("TBD\n");
    }

    private void view() {
        io.print("Enter title: ");
        String title = io.readLine();
        StringValidator.Validate(title);
        printResults(refDB.find(title.toLowerCase()));
    }
    
    private void printResults(List<Article> articles) {
        if (articles.isEmpty()) {
            io.print("No references found with the specified title!\n");
        }
        
        for (Article article : articles) {
            io.print(article.toString());
        }
    }

}
