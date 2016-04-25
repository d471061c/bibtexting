package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates a HashMap containing all the references
 */
public class ReferenceDatabase {

    private final String filename;
    private Map<String, Reference> referencemap;
    private List<Reference> references;

    /**
     * Creates a new ReferenceDatabase Object
     *
     * @param filename Name of the file where references are stored
     */
    public ReferenceDatabase(String filename) {
        referencemap = new HashMap();
        this.filename = filename;
        loadDatabase();
    }

    private void loadDatabase() {
        references = (ArrayList<Reference>) FileIO.loadFileIntoObject(references, filename);
        if (references == null) {
            references = new ArrayList();
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
     * @param filename Name of the file where the refenences are stored.
     * @param referencemap A Map Object with a String key and Article value
     * where the articles are stored.
     */
    public ReferenceDatabase(String filename, Map<String, Reference> referencemap) {
        this.referencemap = referencemap;
        this.filename = filename;
        loadDatabase();
    }

    /**
     * Adds an Article Object to the database and the file associated with it
     *
     * 
     */
    public void add(Reference reference) {
        reference.setTag(String.valueOf(this.numberOfEntries() + 1)); //should be some better algo
        if (!references.contains(reference)) {
            references.add(reference);
            FileIO.saveObjectIntoFile(references, filename);
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
        List<Reference> list = new ArrayList();
       // System.out.println("search title: "+searchTitle);
        String starSearch = searchTitle;

        if (starSearch.contains("*")) {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < searchTitle.length(); i++) {
                if (searchTitle.charAt(i) != '*') {
                    sb.append(searchTitle.charAt(i));
                } else {
                    starSearch = sb.toString();
                    break;
                }
            }
        } else {
            starSearch = null;
        }
      //  System.out.println("find: "+searchTitle);
        for (Reference reference : referencemap.values()) {
        //    System.out.println("Ref: "+reference.getTitle());
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
        
//        StringBuilder sb = new StringBuilder(lowercased);
        lowercased=lowercased.replace("ä", "\\\"{a}");
        lowercased=lowercased.replace("ö", "\\\"{o}");
        lowercased=lowercased.replace("å", "\\aa");
        
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

    public void clearDatabase() {
        this.referencemap = new HashMap();
        FileIO.clearFile(filename);
    }

    public Collection<Reference> getAll() {
        return referencemap.values();
    }
}
