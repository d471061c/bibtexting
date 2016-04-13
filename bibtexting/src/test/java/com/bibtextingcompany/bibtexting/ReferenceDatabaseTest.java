package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Article;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceDatabaseTest {

    ReferenceDatabase refDB;

    public ReferenceDatabaseTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Map<String, Article> refMap = new HashMap();
        refMap.put("a01", createArticle("a01", "Yölevi Äänekoski", "Validating Strings in BibteX", 1995, "Useless Proceedings in Computer Science", 3, 4, "15 - 25"));
        refMap.put("a02", createArticle("a02", "John and Jane Doe", "Validating Strings in BibteX", 2006, "The Computer Journal", 10, 2, "30 - 34"));
        refMap.put("a03", createArticle("a03", "Janne Keskinen", "Täällä Pohjantähden Alla", 1998, "The Journaali", 5, 4, "28-29"));
        refDB = new ReferenceDatabase(refMap);
    }

    private Article createArticle(String tag, String author, String title, int year, String journal, int volume, int numbers, String pages) {
        String validatedTitle = StringValidator.Validate(title);
        return new Article(tag, author, validatedTitle, year, journal, volume, numbers, pages);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAdd() {
        Article article = new Article("a04", "T. S. Garp", "BibteX and You", 2014, "Useless Proceedings in Computer Science", 6, 1, "16-19");
        refDB.add(article);
        assertEquals(article, refDB.getReferencemap().get("a04"));
    }

    @Test
    public void testFindArticlesWithSameTitle() {
        boolean sameTitles = true;
        List<Article> results = refDB.find("   validating strings in bibtex   ");
        for (Article article : results) {
            if (!article.getTitle().equals("Validating Strings in BibteX")) {
                sameTitles = false;
                break;
            }
        }
        assertTrue(sameTitles);
    }
    
    @Test
    public void testFindArticleWithUnicodeTitle() {
        assertEquals("a03", refDB.find("   täällä pohjantähden alla ").get(0).getTag());
    }
    
    @Test
    public void testFindReturnsEmptyListIfNothingFound() {
        assertTrue(refDB.find("This Article Does Not Exist").isEmpty());
    }
    
    @Test
    public void testAddedArticleCanBeFoundByFind() {
        Article article = new Article("a04", "T. S. Garp", "BibteX and You", 2014, "Useless Proceedings in Computer Science", 6, 1, "16-19");
        refDB.add(article);
        assertEquals(article, refDB.find("    BIBTEX AND YOU    ").get(0));
    }

}
