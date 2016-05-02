package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import com.bibtextingcompany.domain.Reference.ReferenceType;
import com.bibtextingcompany.util.StringToType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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

    public void loadDatabaseFromList(List<Reference> references) {
        referencemap = new HashMap<String, Reference>();
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
     * Adds a Reference Object to the database and the file associated with it
     *
     * @param reference Reference which is added to the database.
     */
    public void add(Reference reference) {
        reference.setTag(String.valueOf(System.currentTimeMillis()));//this.numberOfEntries() + 1)); //should be some better algo
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
        if (searchTitle.contains("*")) {
            searchTitle = getStarSearchString(searchTitle);
        }
        return search(searchTitle);
    }

    private String getStarSearchString(String searchTitle) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < searchTitle.length(); i++) {
            if (searchTitle.charAt(i) != '*') {
                sb.append(searchTitle.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }

    private List<Reference> search(String searchTitle) {
        List<Reference> results = new ArrayList();
        for (Reference reference : referencemap.values()) {
            String referenceTitle = trimAndLowercaseString(reference.getTitle());
            if (referenceTitle.contains(searchTitle)) {
                results.add(reference);
            }

        }
        return results;
    }

    private String trimAndLowercaseString(String string) {
        String trimmed = string.trim();
        String lowercased = trimmed.toLowerCase();

//        StringBuilder sb = new StringBuilder(lowercased);
        lowercased = lowercased.replace("ä", "\\\"{a}");
        lowercased = lowercased.replace("ö", "\\\"{o}");
        lowercased = lowercased.replace("å", "\\aa");

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

    public boolean clearDatabase() {
        this.referencemap = new HashMap();
        return FileIO.clearFile(filename);
    }

    public Collection<Reference> getAll() {
        return referencemap.values();
    }

    public Collection<Reference> getMatching(String include, String exclude) {      
        HashSet<ReferenceType> includedReferences = new HashSet<ReferenceType>();
        HashSet<String> includedKeywords = new HashSet<String>();
        HashSet<ReferenceType> excludedReferences = new HashSet<ReferenceType>();
        HashSet<String> excludedKeywords = new HashSet<String>();

        addKeywordsAndReferences(include, includedReferences, includedKeywords);
        addKeywordsAndReferences(exclude, excludedReferences, excludedKeywords);
        boolean includeAllRefs = includedReferences.size() < 1;
        boolean includeAllWords = includedKeywords.size() < 1;

        return this.findMatchingReferences(includedReferences, excludedReferences, 
                includedKeywords, excludedKeywords, includeAllRefs, includeAllWords);
    }

    private void addKeywordsAndReferences(String string, HashSet<ReferenceType> references, HashSet<String> keywords) {
        StringBuilder sb = new StringBuilder();
        boolean referenceFound = false;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '@') {
                referenceFound = true;
            } else if (string.charAt(i) == ' ' || string.charAt(i) == ',' || i == string.length() - 1) {
                if (i == string.length() - 1) {
                    sb.append(string.charAt(i));
                }
                addKeywordOrReference(referenceFound, sb, references, keywords);
                sb = new StringBuilder();
            } else {
                sb.append(string.charAt(i));
            }

        }
    }

    private void addKeywordOrReference(boolean referenceFound, StringBuilder sb, HashSet<ReferenceType> references, HashSet<String> keywords) {
        if (referenceFound && sb.length() > 0) {
            Reference.ReferenceType ref = StringToType.convert(sb.toString());
            if (ref != null) {
                references.add(ref);
            }
            referenceFound = false;

        } else if (sb.length() > 0) {
            keywords.add(sb.toString());
        }
    }

    private boolean keywordIncludedInHashSet(HashSet<String> keywords, Reference reference) {
        for (String word : keywords) {
            if (reference.toString().toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    private Collection<Reference> findMatchingReferences(HashSet<ReferenceType> includedReferences,
            HashSet<ReferenceType> excludedReferences, HashSet<String> includedKeywords, 
            HashSet<String> excludedKeywords, boolean includeAllRefs, 
            boolean includeAllWords) {
        
        Collection<Reference> matchingReferences = new ArrayList<Reference>();
        
        for (Reference reference : referencemap.values()) {
            if (excludedReferences.contains(reference.getReferenceType()) || 
                    keywordIncludedInHashSet(excludedKeywords, reference)) {
                continue;
            }

            boolean included = false;

            if (includeAllRefs || includedReferences.contains(reference.getReferenceType())) {
                if (!includeAllWords) {
                    included = this.keywordIncludedInHashSet(includedKeywords, reference);
                } else {
                    included = true;
                }

            }
            
            if (included) {
                matchingReferences.add(reference);
            }
        }
        return matchingReferences;
    
    }
}
