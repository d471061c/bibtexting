package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Article;
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

    private Map<String, Article> referencemap;
    private List<Article> articles;

    /**
     * Creates a new ReferenceDatabase Object
     */
    public ReferenceDatabase() {
        referencemap = new HashMap();
        loadDatabase();
    }

    private void loadDatabase() {
        articles = (ArrayList<Article>) FileIO.loadFileIntoObject(articles, "DATABASE");
        if (articles == null) {
            articles = new ArrayList<>();;
        }
        for (Article article : articles) {
            this.add(article);
        }

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
        if (!articles.contains(article)) {
            articles.add(article);
            FileIO.saveObjectIntoFile(articles, "DATABASE");
        }
        referencemap.put(article.getTag(), article);
    }

    /**
     * Returns a list of articles which have a title that matches the parameter
     * You can also use star search to search for a partial match (e.g. >
     * validating* ) given.
     *
     * @param title Name of the title.
     * @return A List of Articles with a specific title.
     */
    public List<Article> find(String title) {
        String searchTitle = trimAndLowercaseString(title);

        searchTitle = StringValidator.Validate(searchTitle);
        List<Article> list = new ArrayList();

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

        for (Article article : referencemap.values()) {
            String articleTitle = trimAndLowercaseString(article.getTitle());
            if (articleTitle.equals(searchTitle)) {
                list.add(article);
            } else if (starSearch != null) {
                if (articleTitle.contains(starSearch)) {
                    list.add(article);
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
