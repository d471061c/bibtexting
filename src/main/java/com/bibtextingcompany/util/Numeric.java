package com.bibtextingcompany.util;

/**
 *
 * 
 */
public class Numeric {
   
    /**
     * Confirms whether a String contains a valid integer.
     * Please note: Integers near Int.MAX_VALUE/Int.MIN_VALUE will be bounced back as false.
     * @param myInt string containing integer
     * @return will return true if the string is safe for a ParseInt
     */
    public static boolean confirmInteger(String myInt) {
        
        if (myInt == null) return false;
        
        int intMaxLength = Integer.toString(Integer.MAX_VALUE).length();
        
        // minus sign, only as the 1st char, and chars 0-9 accepted
        // min. length 1 chars, max. length intMaxLength
        return myInt.matches("^-?[0-9]{1," + intMaxLength + "}$");
        
    }
    
}
