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
    private List<Article> articles;
    private final String filename;

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
        articles = (ArrayList<Article>) FileIO.loadFileIntoObject(articles, filename);
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
     * @param filename Name of the file where the refenences are stored.
     * @param referencemap A Map Object with a String key and Article value
     * where the articles are stored.
     */
    public ReferenceDatabase(String filename, Map<String, Article> referencemap) {
        this.referencemap = referencemap;
        this.filename = filename;
        loadDatabase();
    }

    /**
     * Adds an Article Object to the database and the file associated with it
     *
     * @param article Article which is added to the database
     */
    public void add(Article article) {
        // Put validation here
        if (!articles.contains(article)) {
            articles.add(article);
            FileIO.saveObjectIntoFile(articles, filename);
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

    public void clearDatabase() {
        this.referencemap = new HashMap();
        FileIO.clearFile(filename);
    }

}
