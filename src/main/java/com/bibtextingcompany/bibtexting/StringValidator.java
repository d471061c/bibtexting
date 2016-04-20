/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibtextingcompany.bibtexting;

/**
 * As BibteX file can only be written in 7-bit ASCII (chars 0-127), BibteX strings need to be validated before
 * saving the actual BibteX reference. 
 */
public class StringValidator {
    
    /**
     * This method is used to re-format Strings into 7-bit ASCII extended with BibteX special character codes.
     * 
     * It's especially important to catch Scandinavian characters and re-format them into the valid character codes.
     * If the character does not fit into the ASCII character space, the character is replaced with a '<?>'.
     * 
     * @param input Any String
     * @return Same string re-formatted into 7-bit ASCII.
     */
    public static String Validate(String input) {
        if (input==null) {
            return "<null>";
        }
        
        if (input.length()<1) {
            return "<empty>";
        }
        
        StringBuilder sb = new StringBuilder();
        
        // Information about the special symbol codes can be found at:
        // http://www.bibtex.org/SpecialSymbols/
        // We probably don't need to implement the most exotic ones...
        
        for (int i = 0; i<input.length(); i++) {
            if (input.charAt(i)==34 || input.charAt(i)==36 || input.charAt(i)=='{') {
                sb.append("\\");                                             // These chars need to be preceeded with a backslash \ . There are other similar cases as well!
            }
            
            
            if (input.charAt(i)<=127) {
                sb.append(input.charAt(i));
            } else if (input.charAt(i)=='Ä' || input.charAt(i)=='ä') {      // As far as I can tell there is no code for a capital 'Ä' or 'Ö'
                sb.append("\\\"{a}");
            } else if (input.charAt(i)=='Ö' || input.charAt(i)=='ö') {
                sb.append("\\\"{o}");
            } else if (input.charAt(i)=='Å') {
                sb.append("\\AA");
            } else if (input.charAt(i)=='å') {
                sb.append("\\aa");
            } else {
                sb.append("<?>");                                             // '<?>' is mostly for debugging. In the final product invalid chars might be stored as spaces...?
            }
        }
        
        return sb.toString();
    }
    
}
