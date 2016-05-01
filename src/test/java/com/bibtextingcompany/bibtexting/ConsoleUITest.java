package com.bibtextingcompany.bibtexting;

import static com.bibtextingcompany.bibtexting.ReferenceDatabaseTest.FILENAME;
import com.bibtextingcompany.domain.Reference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author mikromafia
 */
public class ConsoleUITest {
     ReferenceDatabase refDB;
        IO io;
            final static String FILENAME = "DATABASE_TEST";
            
    public ConsoleUITest() {
       
    }

    
   

    
    /**
     * Test of run method, of class ConsoleUI.
     */
    @Test
    public void testQuickAndDirty() {
            Map<String, Reference> refMap = new HashMap();
        refDB = new ReferenceDatabase(FILENAME, refMap);
        refDB.add(ReferenceCreator.createArticle("Yölevi Äänekoski", "Validating Strings in BibteX", "1995", "Useless Proceedings in Computer Science", "3"));
        refDB.add(ReferenceCreator.createBook("John and Jane Doe", "Editor123" , "Validating Strings in BibteX", "Tammi", "1993"));
        refDB.add(ReferenceCreator.createInproceedings("Janne Keskinen", "Täällä Pohjantähden Alla", "Booktitle123", "2005"));
        
        ConsoleUI ui = new ConsoleUI(new ConsoleIO(), refDB);
        ArrayList<String> debug = ui.getDebug();
        String longString = "";
        for (String line : debug) {
            longString=longString+line;
        }
        assertEquals(longString.contains("Yölevi"), true);
        assertEquals(longString.contains("BibteX"), true);
        assertEquals(longString.contains("Keskinen"), true);
        refDB.clearDatabase();
        debug = ui.getDebug();
        longString = "";
        for (String line : debug) {
            longString=longString+line;
        }
        assertEquals(longString.contains("No references found with the specified search terms!"), true);
    }

    /**
     * Test of getDebug method, of class ConsoleUI.
     */
    @Test
    public void testGetDebug() {
    }
    
}
