package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference.ReferenceType;
import com.bibtextingcompany.domain.Reference;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
     *
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
        io.print("create\n");
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
            chooseReferenceType();
        } else if (command.equals("create")) {
            create();
        } else {
            io.print("Invalid command; type help for a list of commands\n");
        }
        return false;
    }
    
    private void chooseReferenceType() {
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
        Reference reference = new Reference(type);
        String[] params = new String[24];
        params = askParameters(reference, true, params);
        params = askParameters(reference, false, params);
        if (validateParameters(params)) {
            reference.setParameters(params);
            refDB.add(reference);
            io.print("Success: Reference added.\n");
//            BufferedWriter writer = new BufferedWriter(new FileWriter("BibteX.bib", true));
//            writer.write(reference.toString());
//            writer.close();
        } else {
            io.print("Error: one or more parameters were invalid. Reference was not saved.\n");
        }
    }
    
    private String[] askParameters(Reference reference, boolean parametersRequired, String[] params) {
        String input;
        int parameterIndex;
        int length = getParameterArrayLength(reference, parametersRequired);
        String[] appendedParams = params;
        
        for (int i = 0; i < length; i++) {
            parameterIndex = getParameterIndex(reference, parametersRequired, i);
            printParameterName(reference, parametersRequired, parameterIndex);
            input = io.readLine();
            if (parametersRequired || (!parametersRequired && !input.isEmpty())) {
                appendedParams[parameterIndex] = input;
            }
        }
        return appendedParams;
    }
    
    private int getParameterArrayLength(Reference reference, boolean parametersRequired) {
        if (parametersRequired) {
            return reference.requiredParameters().length;
        }
        return reference.optionalParameters().length;
    }
    
    private int getParameterIndex(Reference reference, boolean parametersRequired, int i) {
        if (parametersRequired) {
            return reference.requiredParameters()[i];
        }
        return reference.optionalParameters()[i];
    }
    
    private void printParameterName(Reference reference, boolean parametersRequired, int parameterIndex) {
        String message = "";
        if (!parametersRequired) {
            message = " (optional, press enter without inputting text to skip)";
        }
        io.print(reference.paramNames[parameterIndex] + message + ": ");
    }
    
    private boolean validateParameters(String[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] != null) {
                if (!validateParameter(parameters[i], i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean validateParameter(String parameter, int parameterIndex) {
        if (isParameterSupposedToBeANumber(parameterIndex)) {
            return DataValidator.Validate(parameter, DataValidator.SINGLE_NUMBER) == DataValidator.SINGLE_NUMBER;
        } else if (isParameterSupposedToBeARangeOfPages(parameterIndex)) {
            boolean singleNumber = false;
            boolean rangeOfNumbers = false;
            if (DataValidator.Validate(parameter, DataValidator.RANGE_OF_NUMBERS) == DataValidator.RANGE_OF_NUMBERS) {
                rangeOfNumbers = true;
            }
            if (DataValidator.Validate(parameter, DataValidator.SINGLE_NUMBER) == DataValidator.SINGLE_NUMBER) {
                singleNumber = true;
            }
            if (rangeOfNumbers || singleNumber) {
                return true;
            }
            return false;
            //return DataValidator.Validate(parameter, DataValidator.RANGE_OF_NUMBERS) == DataValidator.RANGE_OF_NUMBERS;
        } else { // else it's supposed to be a regular string
            return DataValidator.Validate(parameter, DataValidator.TEXT) == DataValidator.TEXT;
        }
    }
    
    private boolean isParameterSupposedToBeANumber(int parameterIndex) {
        return parameterIndex == Reference.NUMBER || parameterIndex == Reference.VOLUME || parameterIndex == Reference.YEAR;
    }
    
    private boolean isParameterSupposedToBeARangeOfPages(int parameterIndex) {
        return parameterIndex == Reference.PAGES;
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
    
    // asks user for a file name and tries to create a file with the specified name
    private void create() {
        io.print("File name: ");
        String filename = io.readLine();
        
        if (!FileIO.writeBibtex(filename, refDB.getAll())) {
            io.print("Writing to file failed\n");
        }
    }
    
}
