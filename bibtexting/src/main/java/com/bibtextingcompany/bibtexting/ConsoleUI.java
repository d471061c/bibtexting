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
            add();
        } else {
            io.print("Invalid command; type help for a list of commands\n");
        }
        return false;
    }

    private void add() {
        String[] args = new String[7];
        io.print("author: ");
        args[0] = io.readLine();
        io.print("title: ");
        args[1] = io.readLine();
        io.print("year: ");
        args[2] = io.readLine();
        io.print("journal: ");
        args[3] = io.readLine();
        io.print("volume: ");
        args[4] = io.readLine();
        io.print("number: ");
        args[5] = io.readLine();
        io.print("pages: ");
        args[6] = io.readLine();
        Article article = new Article("A1", args[0], args[1], Integer.parseInt(args[2]), 
                        args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]), args[6]);
        this.refDB.add(article);
        
    }

    // asks the user for a title and searches for a reference in the database with a matching title
    private void view() {
        io.print("Enter title (use * to match partial string): ");
        String title = io.readLine();
        printResults(refDB.find(title));
    }

    private void printResults(List<Article> articles) {
        if (articles.isEmpty()) {
            io.print("No references found with the specified search terms!\n");
        }

        for (Article article : articles) {
            io.print(article.toString());
        }
    }

}
