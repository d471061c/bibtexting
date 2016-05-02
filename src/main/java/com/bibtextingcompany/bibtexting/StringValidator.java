/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibtextingcompany.bibtexting;

/**
 * As BibteX file can only be written in 7-bit ASCII (chars 0-127), BibteX
 * strings need to be validated before saving the actual BibteX reference.
 */
public class StringValidator {

    /**
     * This method is used to re-format Strings into 7-bit ASCII extended with
     * BibteX special character codes.
     *
     * It's especially important to catch Scandinavian characters and re-format
     * them into the valid character codes. If the character does not fit into
     * the ASCII character space, the character is replaced with a '<?>'.
     *
     * @param input Any String
     * @return Same string re-formatted into 7-bit ASCII.
     */
    public static String Validate(String input) {
        if (input == null) {
            return "<null>";
        }

        if (input.length() < 1) {
            return "<empty>";
        }
        StringBuilder sb = new StringBuilder();

        // Information about the special symbol codes can be found at:
        // http://www.bibtex.org/SpecialSymbols/
        // We probably don't need to implement the most exotic ones...
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            appendSpecialCharacter(sb, character);

        }
        return sb.toString();
    }

    private static void appendSpecialCharacter(StringBuilder sb, char c) {
        if (c== 34 || c == 36 || c == '{') {
            sb.append("\\");                                             // These chars need to be preceeded with a backslash \ . There are other similar cases as well!
        }

        if (c <= 127) {
            sb.append(c);
        } else if (c == 'Ä') {
            sb.append("\\\"{A}");
        } else if (c == 'ä' || c == 'ã') {      // As far as I can tell there is no code for a capital 'Ä' or 'Ö'
            sb.append("\\\"{a}");
        } else if (c == 'Ö' || c == 'ö') {
            sb.append("\\\"{o}");
        } else if (c == 'Å') {
            sb.append("\\AA");
        } else if (c == 'å') {
            sb.append("\\aa");
        } else {
            sb.append("<?>");                                             // '<?>' is mostly for debugging. In the final product invalid chars might be stored as spaces...?
        }
    }

}
