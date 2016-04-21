package com.bibtextingcompany.util;

/**
 *
 * 
 */
public class Numeric {
   
    /**
     * Confirms in a really quick and dirty way wether a String contains a valid integer.
     * Please note: Integers near Int.MAX_VALUE/Int.MIN_VALUE will be bounced back as false.
     * @param myInt string containing integer
     * @return will return true if the string is safe for a ParseInt
     */
    public static boolean confirmInteger(String myInt) {
        if (myInt==null) {
            return false;
        }
        
        if (myInt.length()<1 || myInt.length()>=(""+Integer.MAX_VALUE).length()) {
            return false;
        }
        
        for (int i = 0; i<myInt.length(); i++) {
            if (myInt.charAt(i)<'0' || myInt.charAt(i)>'9') {
                return false;
            }
        }
        return true;
    }
    
}
