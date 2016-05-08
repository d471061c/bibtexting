package com.bibtextingcompany.bibtexting;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ReferenceDatabase refDB = new ReferenceDatabase("DATABASE");
        ConsoleUI ui = new ConsoleUI(new ConsoleIO(), refDB);
        ui.run();
    }
}
