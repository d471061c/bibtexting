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

        for (int i = 0; i<required.length; i++) {
            if (required[i]!=0) {
                params[i]="Placeholder";
            }
            if (optional[i]!=0) {
                params[i]="Placeholder";
            }
            if (i==4 || i == 12 || i == 14 || i == 22 || i == 23) {
                params[i]=""+(i*100);
            }
            if (i==16) {
                params[i]="15--25";
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
        
        for (int i = 0; i<24; i++) {
            params[i]="Bullshit";
               
            if (i==4 || i == 12 || i == 14 || i == 22 || i == 23) {
                params[i]="666";
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
    public void testEquals() {
        System.out.println("equals");
        
        Reference refA = new Reference(Reference.ReferenceType.INPROCEEDINGS);
        Reference refB = new Reference(Reference.ReferenceType.INPROCEEDINGS);
        
        boolean expResult = true;
        
        boolean result = refA.equals(refB);
        
        assertEquals(expResult, result);
        
    }
    
}
