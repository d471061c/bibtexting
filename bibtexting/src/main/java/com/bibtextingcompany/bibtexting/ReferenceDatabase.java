package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Encapsulates a HashMap containing all the references
 */
public class ReferenceDatabase {

    private Map<String, Reference> referencemap;
    private List<Reference> references;

    /**
     * Creates a new ReferenceDatabase Object
     */
    public ReferenceDatabase() {
        referencemap = new HashMap();
        loadDatabase();
    }

    private void loadDatabase() {
        references = (ArrayList<Reference>) FileIO.loadFileIntoObject(references, "DATABASE");
        if (references == null) {
            references = new ArrayList<>();;
        }
        for (Reference reference : references) {
            referencemap.put(reference.getTag(), reference);
        }
    }

    public int numberOfEntries() {
        return references.size();
    }

    /**
     * Creates a new ReferenceDatabase Object
     *
     * @param referencemap A Map Object with a String key and Article value
     * where the articles are stored.
     */
    public ReferenceDatabase(Map<String, Reference> referencemap) {
        this.referencemap = referencemap;
    }

    /**
     * Adds an Article Object to the database
     *
     * @param article Article which is added to the database
     */
    public void add(Reference reference) {
        // Put validation here
        reference.setTag(String.valueOf(this.numberOfEntries() + 1)); //should be some better algo
        if (!references.contains(reference)) {
            references.add(reference);
            FileIO.saveObjectIntoFile(references, "DATABASE");
        }
        referencemap.put(reference.getTag(), reference);
    }

    /**
     * Returns a list of articles which have a title that matches the parameter
     * You can also use star search to search for a partial match (e.g. >
     * validating* ) given.
     *
     * @param title Name of the title.
     * @return A List of Articles with a specific title.
     */
    public List<Reference> find(String title) {
        String searchTitle = trimAndLowercaseString(title);

        searchTitle = StringValidator.Validate(searchTitle);
        List<Reference> list = new ArrayList();

        String starSearch = searchTitle;

        if (starSearch.contains("*")) {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < searchTitle.length(); i++) {
                if (searchTitle.charAt(i) != '*') {
                    sb.append(searchTitle.charAt(i));
                } else if (searchTitle.charAt(i) == '*') {
                    starSearch = sb.toString();
                    break;
                }
            }
        } else {
            starSearch = null;
        }

        for (Reference reference : referencemap.values()) {
            String referenceTitle = trimAndLowercaseString(reference.getTitle());
            if (referenceTitle.equals(searchTitle)) {
                list.add(reference);
            } else if (starSearch != null) {
                if (referenceTitle.contains(starSearch)) {
                    list.add(reference);
                }
            }

        }
        return list;
    }

    private String trimAndLowercaseString(String string) {
        String trimmed = string.trim();
        String lowercased = trimmed.toLowerCase();
        return lowercased;
    }

    /**
     * Returns the Map where the articles are stored
     *
     * @return Map where the articles are stored.
     */
    public Map<String, Reference> getReferencemap() {
        return referencemap;
    }

    /**
     * Sets the Map where the articles are stored.
     *
     * @param referencemap Map where the articles are stored.
     */
    public void setReferencemap(Map<String, Reference> referencemap) {
        this.referencemap = referencemap;
    }

}
