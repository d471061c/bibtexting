package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;

/**
 * Class for easier creation of references for tests.
 * 
 * @author joqtyyppi
 */
public class ReferenceCreator {


    public static Reference createArticle(String author, String title, String year, String journal, String volume) {
        Reference reference = new Reference(Reference.ReferenceType.ARTICLE);
        String[] params = new String[24];
        params[2] = author;
        params[20] = title;
        params[23] = year;
        params[10] = journal;
        params[22] = volume;
        reference.setParameters(params);
        return reference;
    }
    

    public static Reference createBook(String author, String editor, String title, String publisher, String year) {
        Reference reference = new Reference(Reference.ReferenceType.BOOK);
        String[] params = new String[24];
        params[2] = author;
        params[7] = editor;
        params[20] = title;
        params[17] = publisher;
        params[23] = year;
        reference.setParameters(params);
        return reference;
    }
    
    public static Reference createInproceedings(String author, String title, String booktitle, String year) {
        Reference reference = new Reference(Reference.ReferenceType.INPROCEEDINGS);
        String[] params = new String[24];
        params[2] = author;
        params[20] = title;
        params[3] = booktitle;
        params[23] = year;
        reference.setParameters(params);
        return reference;
    }
}
