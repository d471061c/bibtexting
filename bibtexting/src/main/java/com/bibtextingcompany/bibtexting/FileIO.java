package com.bibtextingcompany.bibtexting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIO {

    public static Scanner readFile(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return scanner;
    }
    
    public static void appendToFile(String filename, String data) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
