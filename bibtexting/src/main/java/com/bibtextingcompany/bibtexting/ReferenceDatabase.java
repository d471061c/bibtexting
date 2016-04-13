package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Article;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ReferenceDatabase {
    private Map<String, Article> referencemap;

    public ReferenceDatabase() {
        referencemap = new HashMap();
    }
    
    public void add(Article article) {
        referencemap.put(article.getTag(), article);
    }
    
    public List<Article> find(String title) {
        List<Article> list = new ArrayList();
        for (Article article : referencemap.values()) {
            String articleTitle = article.getTitle().toLowerCase();
            if (articleTitle.equals(title)) {
                list.add(article);
            }
        }
        return list;
    }
}
