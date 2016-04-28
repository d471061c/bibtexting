package com.bibtextingcompany.util;

import com.bibtextingcompany.domain.Reference;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eero
 */
public class StringToTypeTest {
    
    public StringToTypeTest() {
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

    @Test
    public void convertConvertsArticle() {
        assertEquals(Reference.ReferenceType.ARTICLE, StringToType.convert("ARTICLE"));
    }
    
    @Test
    public void convertConvertsBook() {
        assertEquals(Reference.ReferenceType.BOOK, StringToType.convert("boOK"));
    }
    
    @Test
    public void convertConvertsBooklet() {
        assertEquals(Reference.ReferenceType.BOOKLET, StringToType.convert("BoOkLeT"));
    }
    
    @Test
    public void convertConvertsConference() {
        assertEquals(Reference.ReferenceType.CONFERENCE, StringToType.convert("CONFERENCE"));
    }
    
    @Test
    public void convertConvertsInbook() {
        assertEquals(Reference.ReferenceType.INBOOK, StringToType.convert("inbook"));
    }
    
    @Test
    public void convertConvertsIncollection() {
        assertEquals(Reference.ReferenceType.INCOLLECTION, StringToType.convert("incollection"));
    }
    
    @Test
    public void convertConvertsInproceedings() {
        assertEquals(Reference.ReferenceType.INPROCEEDINGS, StringToType.convert("inproceedings"));
    }
    
    @Test
    public void convertDoesNotConvertInvalidInput() {
        assertNull(StringToType.convert("apina"));
    }
}
