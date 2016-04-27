package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
public class FileIOTest {
    
    public FileIOTest() {
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
    public void saveObjectIntoFileReturnsFalseIfUnsuccessful() {
        assertFalse(FileIO.saveObjectIntoFile("hello world", "."));
    }
    
    @Test
    public void clearFileReturnsFalseIfUnsuccessful() {
        assertFalse(FileIO.clearFile("."));
    }
   
    @Test
    public void writeBibtexReturnsFalseIfUnsuccessful() {
        assertFalse(FileIO.writeBibtex("/>><<..*,", new ArrayList<Reference>()));
    }
    
    @Test
    public void validateFilenameRemovesIllegalCharacters () {
        assertEquals("soylent green is people", FileIO.validateFilename("/so\\yl?en%t *gr:\"een< is> pe.op.le,"));
    }
    
    @Test
    public void validateFilenameChangesFilenameIfTooShort() {
        assertEquals("filename_too_short", FileIO.validateFilename("ad"));
    }
}
