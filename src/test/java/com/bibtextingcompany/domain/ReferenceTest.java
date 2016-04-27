package com.bibtextingcompany.domain;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikromafia
 */
public class ReferenceTest {

    Reference reference;
    Reference refA;
    Reference refB;

    public ReferenceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private Reference createArticle(Reference.ReferenceType tyyppi) {//String author, String title, int year, String journal, int volume) {
        Reference reference = new Reference(tyyppi);
        String[] params = new String[24];
        int[] required = null;
        int[] optional = null;
        required = reference.requiredParameters();
        optional = reference.optionalParameters();

        for (int i = 0; i < required.length; i++) {
            if (required[i] != 0) {
                params[i] = "Placeholder";
            }
            if (optional[i] != 0) {
                params[i] = "Placeholder";
            }
            if (i == 4 || i == 12 || i == 14 || i == 22 || i == 23) {
                params[i] = "" + (i * 100);
            }
            if (i == 16) {
                params[i] = "15--25";
            }

        }
        reference.setParameters(params);

        reference.setTag("test_tag");

        return reference;
    }

    /**
     * Test of getTag method, of class Reference.
     */
    @Test
    public void testSetAndGetTag() {

        reference = new Reference(Reference.ReferenceType.ARTICLE);

        String newTag = "a tag!";
        reference.setTag(newTag);
        String result = reference.getTag();
        assertEquals(newTag, result);

    }

    /**
     * Test of getTitle method, of class Reference.
     */
    @Test
    public void testGetTitle() {

        for (Reference.ReferenceType rt : Reference.ReferenceType.values()) {
            reference = new Reference(Reference.ReferenceType.ARTICLE);

            System.out.println(reference.toString());

            String expResult = "Placeholder";
            String result = reference.getTitle();
            assertEquals(expResult, result);
        }

    }

    /**
     * Test of requiredParameters method, of class Reference.
     */
    @Test
    public void testRequiredParameters() {
        for (Reference.ReferenceType rt : Reference.ReferenceType.values()) {
            reference = new Reference(rt);

            Reference altReference = new Reference(rt);

            String str_result = Arrays.toString(reference.requiredParameters());
            String str_expResult = Arrays.toString(altReference.requiredParameters());

            assertEquals(str_result, str_expResult);

        }
    }

    /**
     * Test of optionalParameters method, of class Reference.
     */
    @Test
    public void testOptionalParameters() {
        for (Reference.ReferenceType rt : Reference.ReferenceType.values()) {
            reference = new Reference(rt);

            Reference altReference = new Reference(rt);

            String str_result = Arrays.toString(reference.optionalParameters());
            String str_expResult = Arrays.toString(altReference.optionalParameters());

            assertEquals(str_result, str_expResult);

        }
    }

    /**
     * Test of setParameters method, of class Reference.
     */
    @Test
    public void testSetParameters() {

        reference = new Reference(Reference.ReferenceType.BOOK);

        String[] params = new String[24];

        for (int i = 0; i < 24; i++) {
            params[i] = "Bullshit";

            if (i == 4 || i == 12 || i == 14 || i == 22 || i == 23) {
                params[i] = "666";
            }
        }

        reference.setParameters(params);

        String output = reference.toString();
        boolean result = false;

        if (output.contains("Bullshit")) {
            result = true;
        }

        assertTrue(result);

    }

    /**
     * Test of toString method, of class Reference.
     */
    @Test
    public void testToString() {
        reference = new Reference(Reference.ReferenceType.BOOK);

        String output = reference.toString();
        boolean result = false;

        if (output.contains("Placeholder")) {
            result = true;
        }

        assertTrue(result);

    }

    /**
     * Test of equals method, of class Reference.
     */
    @Test
    public void testEqualsReturnsTrueIfBothSame() {
        System.out.println("equals");

        refA = new Reference(Reference.ReferenceType.INPROCEEDINGS);
        refB = new Reference(Reference.ReferenceType.INPROCEEDINGS);
        boolean expResult = true;

        boolean result = refA.equals(refB);

        assertEquals(expResult, result);

    }
    
    @Test
    public void equalsReturnsFalseIfDifferentType() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.BOOK);
        assertFalse(refA.equals(refB));
    }

    @Test
    public void equalsReturnsFalseIfDifferentAddress() {
        refA = new Reference(Reference.ReferenceType.BOOK);
        refB = new Reference(Reference.ReferenceType.BOOK);
        refA.setAddress("A.I virtasen katu");
        refB.setAddress("Tietotie");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentAnnote() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setAnnote("what is annote");
        refB.setAnnote("I have no idea");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentAuthor() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setAuthor("author1");
        refB.setAuthor("author2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentBooktitle() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setBooktitle("booktitle1");
        refB.setBooktitle("booktitle2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentChapter() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setChapter("chapter1");
        refB.setChapter("chapter2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentCrossref() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setCrossref("crossref1");
        refB.setCrossref("crossref2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentEdition() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setEdition("edition1");
        refB.setEdition("edition2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentEditor() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setEditor("editor1");
        refB.setEditor("editor2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentHowPublished() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setHowpublished("how1");
        refB.setHowpublished("how2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentInstitution() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setInstitution("ins1");
        refB.setInstitution("ins2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentJournal() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setJournal("journal1");
        refB.setJournal("journal2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentKey() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setKey("key1");
        refB.setKey("key2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentMonth() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setMonth("june");
        refB.setMonth("May");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentNote() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setNote("note1");
        refB.setNote("note2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentNumber() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setNumber(1);
        refA.setNumber(2);
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentOrganization() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setOrganization("org1");
        refB.setOrganization("org2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentRangeOfPages() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setPages("2-3");
        refB.setPages("3-2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentPublisher() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setPublisher("publisher1");
        refB.setPublisher("publisher2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentSchool() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setSchool("school1");
        refB.setSchool("school2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentSeries() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setSeries("series1");
        refB.setSeries("series2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentTitle() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setTitle("title1");
        refB.setTitle("title2");
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentVolume() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setVolume(1);
        refB.setVolume(2);
        assertFalse(refA.equals(refB));
    }
    
    @Test
    public void equalsReturnsFalseIfDifferentYear() {
        refA = new Reference(Reference.ReferenceType.ARTICLE);
        refB = new Reference(Reference.ReferenceType.ARTICLE);
        refA.setYear(1993);
        refB.setYear(1994);
        assertFalse(refA.equals(refB));
    }
}
