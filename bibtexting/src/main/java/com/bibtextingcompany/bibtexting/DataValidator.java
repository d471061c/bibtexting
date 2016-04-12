/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibtextingcompany.bibtexting;

/**
 * DataValidator evaluates wether an input string is valid for a specific type
 * of data stored in a BibteX reference.
 */
public class DataValidator {

    public static final int ERROR_SYNTAX = -3;
    public static final int ERROR_EMPTY_NOT_ALLOWED = -2;
    public static final int ERROR_NOT_A_NUMBER = -1;
    public static final int ERROR_DATATYPE_UNRECOGNIZABLE = 0;

    public static final int SINGLE_NUMBER = 1;
    public static final int RANGE_OF_NUMBERS = 2;
    public static final int TEXT = 3;
    public static final int AUTHOR = 4;
    public static final int EMPTY_ALLOWED = 5;
    public static final int UNKNOWN = 5;


    /**
     * Validates wether an input string passes as a certain datatype (int)
     * The datatype codes are listed on @param datatype.
     * @param input user input string to be validated.
     * @param datatype 1 = SINGLE_NUMBER, 2 = RANGE_OF_NUMBERS, 3 = TEXT, 4 = AUTHOR (not supported yet), 5 = EMPTY_ALLOWED
     * @return Returns the same int as the @param datatype if the input was valid. Return of 0, -1, -2, -3 indicates an error.
     */
    public static int Validate(String input, int datatype) {
        if (datatype<SINGLE_NUMBER || datatype>EMPTY_ALLOWED) {
            return ERROR_DATATYPE_UNRECOGNIZABLE;
        }
        
        if (input == null) {
            if (datatype != EMPTY_ALLOWED) {
                return ERROR_SYNTAX;
            } else {
                input = "";
            }
        }

        if (input.length() == 0) {
            if (datatype != EMPTY_ALLOWED) {
                return ERROR_EMPTY_NOT_ALLOWED;
            } else {
                return UNKNOWN;
            }
        }

        
        
        input = StringValidator.Validate(input);

        if (datatype == SINGLE_NUMBER) {
            if (ValidateNumber(input)) {
                return datatype;
            } else {
                return ERROR_NOT_A_NUMBER;
            }
        }

        if (datatype == RANGE_OF_NUMBERS) {
            if (ValidateRangeOfNumbers(input)) {
                return datatype;
            } else {
                return ERROR_NOT_A_NUMBER;
            }
        }

        if (datatype == TEXT) {
            if (ValidateText(input)) {
                return datatype;
            } else {
                return ERROR_SYNTAX;
            }
        }

        return ERROR_DATATYPE_UNRECOGNIZABLE;
    }

    /**
     * Validates a string with a range of numbers (e.g. "52 - 64" or "2004-2005"). 
     * If a range of TWO distinct numbers is not properly defined, the method will return FALSE.
     * @param input user string with two numbers separated with a '-'
     * @return TRUE if range of two numbers is found, FALSE if not.
     */
    public static boolean ValidateRangeOfNumbers(String input) {

        input = removeSpaces(input);
        
        StringBuilder firstNumber = new StringBuilder();
        StringBuilder secondNumber = new StringBuilder();
        boolean rangeIndicatorFound = false;
        int numberOfRanges=0;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            
            if (current=='-') {
                rangeIndicatorFound=true;
                numberOfRanges++;
            }
            
            if (numberOfRanges>2) {
                return false;
            }

            if (current >= '0' && current <= '9') {

                if (rangeIndicatorFound && firstNumber.length()>0) {
                    secondNumber.append(current);
                } else if (!rangeIndicatorFound) {
                    firstNumber.append(current);
                } else {
                    return false;
                }
            } else if ((current == '-') && (firstNumber.length() == 0 || (firstNumber.length()>0 && secondNumber.length()>0))) {
                return false;
            } else if (current =='.' && i<input.length()-1) {
                return false;
            } else if ((current <'0' || current >'9') && current!='-') {
                return false;
            }
            
        }
        
        if (firstNumber.length()>0 && secondNumber.length()>0 && rangeIndicatorFound) {
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * Validates wether the input contains a single number
     * @param input user string containing a number
     * @return TRUE if a single number found, FALSE otherwise
     */
    public static boolean ValidateNumber(String input) {
        boolean validNumber = true;

        if (input.length() > 9) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current < '0' || current > '9') {
                validNumber = false;
            }
        }

        return validNumber;
    }

    /**
     * Very simplistic validation for a user string containing text.
     * @param input user string containing alphabetical letters
     * @return TRUE if alphabet found, FALSE otherwise.
     */
    public static boolean ValidateText(String input) {
        boolean containsAlphabet = false;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if ((current >= 'a' && current <= 'z')
                    || (current >= 'A' && current <= 'Z')) {
                containsAlphabet = true;
            }
        }

        return containsAlphabet;
    }

    public static String removeSpaces(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<input.length(); i++) {
            if (input.charAt(i)!=' ') {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }
}
