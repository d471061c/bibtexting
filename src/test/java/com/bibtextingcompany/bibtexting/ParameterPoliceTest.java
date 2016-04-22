/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibtextingcompany.bibtexting;
package com.bibtextingcompany.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
 
 public class ParameterPoliceTest {
    
    public ParameterPoliceTest() {
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
    
    /**
     * Test empty parameter fields.
     */
    @Test
    public void testPlaceholderParameters() {
    
     Reference reference = new Reference(Reference.ReferenceType.ARTICLE);
     
      boolean noErrors = true;
        
        for (int i = 0; i<24; i++) {
          if (!(params[i].contains("<") && params[i].contains(">"))) {
              noErrors=false;
          }
        }
        
        assertEquals(noErrors, true);
    
    }
    /**
     * Test empty parameter fields.
     */
    @Test
    public void testEmpties() {
        String[] params = new String[24];
        
        for (int i = 0; i<24; i++) {
          params[i]="";
        }
        
        params = ParameterPolice.process(params);
        
        boolean allErrors = true;
        
        for (int i = 0; i<24; i++) {
          if (!(params[i].contains("<") && params[i].contains(">"))) {
              allErrors=false;
          }
        }
        
        
        assertEquals(allErrors, true);

        params = ParameterPolice.clean(params);
        
        boolean allNulls = true;
        
        for (int i = 0; i<24; i++) {
          if (params[i]!=null) {
              allNulls=false;
          }
        }
        
             assertEquals(allNulls, true);

    }
    
}
