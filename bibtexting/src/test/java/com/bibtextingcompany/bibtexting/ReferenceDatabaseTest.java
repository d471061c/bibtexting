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
    Article articleToBeAdded;
    final static String filename = "DATABASE_TEST";

    public ReferenceDatabaseTest() {
        articleToBeAdded = new Article("a04", "T. S. Garp", "BibteX and You", 2014, "Useless Proceedings in Computer Science", 6, 1, "16-19");
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
        refDB = new ReferenceDatabase(filename, refMap);
        refDB.add(createArticle("a01", "Yölevi Äänekoski", "Validating Strings in BibteX", 1995, "Useless Proceedings in Computer Science", 3, 4, "15 - 25"));
        refDB.add(createArticle("a02", "John and Jane Doe", "Validating Strings in BibteX", 2006, "The Computer Journal", 10, 2, "30 - 34"));
        refDB.add(createArticle("a03", "Janne Keskinen", "Täällä Pohjantähden Alla", 1998, "The Journaali", 5, 4, "28-29"));
    }

    private Article createArticle(String tag, String author, String title, int year, String journal, int volume, int numbers, String pages) {
        String validatedTitle = StringValidator.Validate(title);
        return new Article(tag, author, validatedTitle, year, journal, volume, numbers, pages);
    }

    @After
    public void tearDown() {
        refDB.clearDatabase();
    }

    @Test
    public void testAdd() {
        refDB.add(articleToBeAdded);
        assertEquals(articleToBeAdded, refDB.getReferencemap().get("a04"));
    }

    @Test
    public void testFindArticlesWithSameTitle() {
        boolean sameTitles = true;
        List<Article> results = refDB.find("   validating strings in bibtex   ");

        if (results.isEmpty()) {
            sameTitles = false;
        } else {
            for (Article article : results) {
                if (!article.getTitle().equals("Validating Strings in BibteX")) {
                    sameTitles = false;
                    break;
                }
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
        refDB.add(articleToBeAdded);
        assertEquals(articleToBeAdded, refDB.find("    BIBTEX AND YOU    ").get(0));
    }

    @Test
    public void ifSameArticleIsAddedTwiceOnlyOneObjectInDatabase() {
        refDB.add(articleToBeAdded);
        refDB.add(articleToBeAdded);
        assertEquals(1, refDB.find(" bibtex and you      ").size());
    }

    @Test
    public void newDatabaseIsEmptyWithEmptyFile() {
        refDB.clearDatabase();
        refDB = new ReferenceDatabase(filename);
        assertTrue(refDB.getReferencemap().isEmpty());
    }

    @Test
    public void onStartupFileContentsAreParsedIntoTheDatabase() {
        refDB = new ReferenceDatabase(filename);
        assertEquals(3, refDB.getReferencemap().size());
    }
}
