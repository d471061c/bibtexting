package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
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
    Reference articleToBeAdded;
    final static String FILENAME = "DATABASE_TEST";

    public ReferenceDatabaseTest() {
        articleToBeAdded = createArticle("T. S. Garp", "BibteX and You", 2014, "Useless Proceedings in Computer Science", 6);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Map<String, Reference> refMap = new HashMap();
        refDB = new ReferenceDatabase(FILENAME, refMap);
        refDB.add(createArticle("Yölevi Äänekoski", "Validating Strings in BibteX", 1995, "Useless Proceedings in Computer Science", 3));
        refDB.add(createArticle("John and Jane Doe", "Validating Strings in BibteX", 2006, "The Computer Journal", 10));
        refDB.add(createArticle("Janne Keskinen", "Täällä Pohjantähden Alla", 1998, "The Journaali", 5));
    }

    private Reference createArticle(String author, String title, int year, String journal, int volume) {
        Reference reference = new Reference(Reference.ReferenceType.ARTICLE);
        String[] params = new String[24];
        params[2] = author;
        params[20] = title;
        params[23] = "" + year;
        params[10] = journal;
        params[22] = "" + volume;
        reference.setParameters(params);
        return reference;
    }

    @After
    public void tearDown() {
        refDB.clearDatabase();
    }

    @Test
    public void testAdd() {
        refDB.add(articleToBeAdded);
        assertEquals(articleToBeAdded, refDB.getReferencemap().get("4"));
    }

    @Test
    public void testFindArticlesWithSameTitle() {
        boolean sameTitles = true;
        List<Reference> results = refDB.find("   validating strings in bibtex   ");

        if (results.isEmpty()) {
            sameTitles = false;
        } else {
            for (Reference reference : results) {
                if (!reference.getTitle().equals("Validating Strings in BibteX")) {
                    sameTitles = false;
                    break;
                }
            }
        }
        assertTrue(sameTitles);
    }

    @Test
    public void testFindArticleWithUnicodeTitle() {
        assertEquals("3", refDB.find("   täällä pohjantähden alla ").get(0).getTag());
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
    public void newDatabaseIsEmptyWithEmptyFile() {
        refDB.clearDatabase();
        refDB = new ReferenceDatabase(FILENAME);
        assertTrue(refDB.getReferencemap().isEmpty());
    }

    @Test
    public void onStartupFileContentsAreParsedIntoTheDatabase() {
        refDB = new ReferenceDatabase(FILENAME);
        assertEquals(3, refDB.getReferencemap().size());
    }
}
