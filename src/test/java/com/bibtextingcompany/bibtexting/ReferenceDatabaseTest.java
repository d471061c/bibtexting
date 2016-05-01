package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import java.util.ArrayList;
import java.util.Collection;
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
        articleToBeAdded = ReferenceCreator.createArticle("T. S. Garp", "BibteX and You", "2014", "Useless Proceedings in Computer Science", "6");
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
        refDB.add(ReferenceCreator.createArticle("Yölevi Äänekoski", "Validating Strings in BibteX", "1995", "Useless Proceedings in Computer Science", "3"));
        refDB.add(ReferenceCreator.createBook("John and Jane Doe", "Editor123" , "Validating Strings in BibteX", "Tammi", "1993"));
        refDB.add(ReferenceCreator.createInproceedings("Janne Keskinen", "Täällä Pohjantähden Alla", "Booktitle123", "2005"));
    }

    @After
    public void tearDown() {
        refDB.clearDatabase();
    }
    
    @Test
    public void testgetMatching() {
        refDB.add(articleToBeAdded);
        Collection<Reference> results = null;
        results=refDB.getMatching("@inproceedings @article keskinen", "useless");
        assertEquals(results.size(), 1);
        results=refDB.getMatching("@inproceedings @article", "baseless");
        assertEquals(results.size(), 3);
    }

    @Test
    public void testAdd() {
        
        refDB.add(articleToBeAdded);
        Reference retrieved = null;
        for (Reference ref : refDB.getAll()) {
            if (ref.equals(articleToBeAdded)) {
                retrieved=ref;
            }
        }
        assertEquals(articleToBeAdded, retrieved);//refDB.getReferencemap().get("4"));
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
        assertEquals("T\\\"{a}\\\"{a}ll\\\"{a} Pohjant\\\"{a}hden Alla", refDB.find("   t\\\"{a}\\\"{a}ll\\\"{a} pohjant\\\"{a}hden alla ").get(0).getTitle());
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

    @Test
    public void starSearchFindsBySubstring() {
        boolean bool = true;
        List<Reference> references = refDB.find("STRING*");

        if (references.isEmpty()) {
            bool = false;
        } else {
            for (Reference reference : references) {
                if (!reference.getTitle().contains("String")) {
                    bool = false;
                }
            }
        }
        assertTrue(bool);
    }
}
