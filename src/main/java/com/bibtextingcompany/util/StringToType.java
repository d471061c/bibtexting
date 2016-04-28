package com.bibtextingcompany.util;

import com.bibtextingcompany.domain.Reference.ReferenceType;

/**
 * Converts a String to a matching Reference.ReferenceType 
 */
public class StringToType {
    
    /**
     * Checks if a string matches an existing Reference.ReferenceType
     * @param input input from user
     * @return A matching Ref.type or NULL
     */
     public static ReferenceType convert(String input) {  
        for (ReferenceType referenceType : ReferenceType.values()) {
            if (input.toLowerCase().contentEquals(referenceType.getName().toLowerCase())) {
                return referenceType;
            }
        }
        return null;
    }
}
