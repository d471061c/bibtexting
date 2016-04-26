package com.bibtextingcompany.util;

import com.bibtextingcompany.domain.Reference;

/**
 * Converts a String to a matching Reference.ReferenceType 
 */
public class TypeToString {
    
    /**
     * Checks if a string matches an existing Reference.ReferenceType
     * @param input input from user
     * @return A matching Ref.type or NULL
     */
     public static Reference.ReferenceType convert(String input) {
        Reference.ReferenceType ref = null;
        if (input.toLowerCase().contentEquals("article")) {
            ref=Reference.ReferenceType.ARTICLE;
        }
        else if (input.toLowerCase().contentEquals("book")) {
            ref=Reference.ReferenceType.BOOK;
        }
        else if (input.toLowerCase().contentEquals("booklet")) {
            ref=Reference.ReferenceType.BOOKLET;
        }
        else if (input.toLowerCase().contentEquals("conference")) {
            ref=Reference.ReferenceType.CONFERENCE;
        }
        else if (input.toLowerCase().contentEquals("inbook")) {
            ref=Reference.ReferenceType.INBOOK;
        }
        else if (input.toLowerCase().contentEquals("incollection")) {
            ref=Reference.ReferenceType.INCOLLECTION;
        }
         else if (input.toLowerCase().contentEquals("inproceedings")) {
            ref=Reference.ReferenceType.INPROCEEDINGS;
        }
        return ref;
    }
}
