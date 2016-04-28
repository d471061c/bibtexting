package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikromafia
 */
public class BibReaderTest {
    
    public BibReaderTest() {
    }

    /**
     * Test of readToReference method, of class BibReader.
     */
    @Test
    public void testReadToReference() {
        String content="123";
        List<Reference> lista =BibReader.readToReference(content);
          assertEquals(lista,null);
          
         content = "@book{1,"
    +"author  = \"auttori\","
    +"editor  = \"jofasfadstain\","
    +"number  = \"10\","
    +"publisher  = \"publisher\","
    +"title  = \"joskus\","
    +"volume  = \"10\","
    +"year  = \"1000\",";
        
         lista =BibReader.readToReference(content);
          assertEquals(lista.size(),1);

        
    }
    
}
