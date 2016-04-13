package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Article;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates a HashMap containing all the references
 */
public class ReferenceDatabase {

    private Map<String, Article> referencemap;

    /**
     * Creates a new ReferenceDatabase Object
     */
    public ReferenceDatabase() {
        referencemap = new HashMap();
    }

    /**
     * Creates a new ReferenceDatabase Object
     *
     * @param referencemap A Map Object with a String key and Article value
     * where the articles are stored.
     */
    public ReferenceDatabase(Map<String, Article> referencemap) {
        this.referencemap = referencemap;
    }

    /**
     * Adds an Article Object to the database
     *
     * @param article Article which is added to the database
     */
    public void add(Article article) {
        // Put validation here
        
        referencemap.put(article.getTag(), article);
    }

    /**
     * Returns a list of articles which have a title that matches the parameter
     * given.
     *
     * @param title Name of the title.
     * @return A List of Articles with a specific title.
     */
    public List<Article> find(String title) {
        String searchTitle = trimAndLowercaseString(title);
        searchTitle = StringValidator.Validate(searchTitle);
        List<Article> list = new ArrayList();

        for (Article article : referencemap.values()) {
            String articleTitle = trimAndLowercaseString(article.getTitle());
            if (articleTitle.equals(searchTitle)) {
                list.add(article);
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
    public Map<String, Article> getReferencemap() {
        return referencemap;
    }

    /**
     * Sets the Map where the articles are stored.
     *
     * @param referencemap Map where the articles are stored.
     */
    public void setReferencemap(Map<String, Article> referencemap) {
        this.referencemap = referencemap;
    }

}
