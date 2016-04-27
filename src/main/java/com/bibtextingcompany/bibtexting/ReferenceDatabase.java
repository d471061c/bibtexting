package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import com.bibtextingcompany.util.TypeToString;
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

    public boolean clearDatabase() {
        this.referencemap = new HashMap();
        return FileIO.clearFile(filename);
    }

    public Collection<Reference> getAll() {
        return referencemap.values();
    }
    
    public Collection<Reference> getMatching(String include, String exclude) {
        
        Collection<Reference> matchingReferences = new ArrayList<Reference>();
        
        HashSet<Reference.ReferenceType> includedReferences = new HashSet<Reference.ReferenceType>();
        HashSet<String> includedKeywords = new HashSet<String>();
        HashSet<Reference.ReferenceType> excludedReferences = new HashSet<Reference.ReferenceType>();
        HashSet<String> excludedKeywords = new HashSet<String>();
        
        StringBuilder sb = new StringBuilder();
        boolean referenceFound = false;
        
        for (int i=0; i<include.length(); i++) {
            if (include.charAt(i)=='@') {
                referenceFound=true;
            } else if (include.charAt(i)==' ' || include.charAt(i)==',' || i==include.length()-1) {
                if (i==include.length()-1) {
                    sb.append(include.charAt(i));
                }
                if (referenceFound && sb.length()>0) {
                   // System.out.println("Trying to match: "+sb.toString());
                    Reference.ReferenceType ref = TypeToString.convert(sb.toString());
                    if (ref!=null) {
                    //    System.out.println("Article type "+sb.toString()+" INCLUDED!");
                        includedReferences.add(ref);
                    }
                    referenceFound=false;
                    
                } else if (sb.length()>0) {
             //       System.out.println("word added: "+sb.toString());
                    includedKeywords.add(sb.toString());
                }
                sb = new StringBuilder();
            } else {
                sb.append(include.charAt(i));
            }
            
        }
        
        referenceFound = false;
        sb = new StringBuilder();
        
        for (int i=0; i<exclude.length(); i++) {
            if (exclude.charAt(i)=='@') {
                referenceFound=true;
            } else if (exclude.charAt(i)==' ' || exclude.charAt(i)==',' || i==exclude.length()-1) {
                 if (i==exclude.length()-1) {
                    sb.append(exclude.charAt(i));
                }
                if (referenceFound && sb.length()>0) {
                   // System.out.println("Trying to match: "+sb.toString());
                    Reference.ReferenceType ref = TypeToString.convert(sb.toString());
                    if (ref!=null) {
                 //       System.out.println("Article type "+sb.toString()+" EXCLUDED!");
                        excludedReferences.add(ref);
                    }
                    referenceFound=false;
                    
                } else if (sb.length()>0) {
              //      System.out.println("word excluded: "+sb.toString());
                    excludedKeywords.add(sb.toString());
                }
                sb = new StringBuilder();
            } else {
                sb.append(exclude.charAt(i));
            }
            
        }
        
        boolean includeAllRefs=false;
        if (includedReferences.size()<1) {
            includeAllRefs=true;
        }
        boolean includeAllWords=false;
        if (includedKeywords.size()<1) {
            includeAllWords=true;
        }
        System.out.println("Incl refs: "+includedReferences.size()+"; excluded refs: "+excludedReferences.size()+", keys inc: "+includedKeywords.toString()+", excluded keys: "+excludedKeywords.toString());
        for (Reference reference : referencemap.values()) {
            boolean notExcluded=true;
            
            if (excludedReferences.contains(reference.getReferenceType())) {
                notExcluded=false;
                continue;
            }
            for (String word : excludedKeywords) {
            
             if (reference.toString().toLowerCase().contains(word.toLowerCase())) {
                notExcluded=false;
                 //System.out.println("WORD: "+word+" excluded!");
                continue;
            }
            }
            
            boolean included=false;
            
            if (notExcluded && (includeAllRefs || includedReferences.contains(reference.getReferenceType()))) {
                
                if (!includeAllWords) {
                    
                    for (String word : includedKeywords) {
            
                        if (reference.toString().toLowerCase().contains(word.toLowerCase())) {
                            
                           // System.out.println("WORD: "+word+" included!");
                            included=true;
                        continue;
                        }
                    }
                    
                 } else {
                    included=true;
                }
            
        }
            if (included && notExcluded) {
                System.out.println(reference.toString());
                matchingReferences.add(reference);
            } else {
                //System.out.println(reference.getTitle()+" excluded!!");
            }
            
        
        
    }
        return matchingReferences;
    }
}
