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
    public void convertConvertsManual() {
        assertEquals(Reference.ReferenceType.MANUAL, StringToType.convert("MaNuAL"));
    }
    
    @Test
    public void convertConvertsMastersThesis() {
        assertEquals(Reference.ReferenceType.MASTERSTHESIS, StringToType.convert("mASTER'S Thesis"));
    }
    
    @Test
    public void convertConvertsMisc() {
        assertEquals(Reference.ReferenceType.MISC, StringToType.convert("MISc"));
    }
    
    @Test
    public void convertConvertsPHDThesis() {
        assertEquals(Reference.ReferenceType.PHDTHESIS, StringToType.convert("PHD THESIS"));
    }
    
    @Test
    public void convertConvertsProceedings() {
        assertEquals(Reference.ReferenceType.PROCEEDINGS, StringToType.convert("Proceedings"));
    }
    
    @Test
    public void convertConvertsTechReport() {
        assertEquals(Reference.ReferenceType.TECHREPORT, StringToType.convert("TECh REport"));
    }
    
    @Test
    public void convertConvertsUnpublished() {
        assertEquals(Reference.ReferenceType.UNPUBLISHED, StringToType.convert("unpublished"));
    }
    
    @Test
    public void convertDoesNotConvertMastersThesisWithoutApostrophe() {
        assertNull(StringToType.convert("masters thesis"));
    }
    
    @Test
    public void convertDoesNotConvertMastersThesisWithoutSpace() {
        assertNull(StringToType.convert("mastersthesis"));
    }
    
    @Test
    public void convertDoesNotConvertPhDThesisWithoutSpace() {
        assertNull(StringToType.convert("phdthesis"));
    }
    
    @Test
    public void convertDoesNotConvertTechReportWIthoutSpace() {
        assertNull(StringToType.convert("techreport"));
    }
    
    @Test
    public void convertDoesNotConvertInvalidInput() {
        assertNull(StringToType.convert("apina"));
    }
}
