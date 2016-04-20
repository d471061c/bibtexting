package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;


public class ReferenceCreator {
    public static Reference createArticle(String author, String title, int year, String journal, int volume) {
        Reference reference = new Reference(Reference.ReferenceType.ARTICLE);
        String[] params = new String[24];
        params[2] = author;
        params[20] = title;
        params[23] = "" + year;
        params[10] = journal;
        params[22] = "" + volume;
        reference.setParameters(params);
        return reference;
    }
}
