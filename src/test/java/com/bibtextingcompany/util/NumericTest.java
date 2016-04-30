package com.bibtextingcompany.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class NumericTest {
    
    public NumericTest() {
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
     * Test of confirmInteger method, of class Numeric.
     */
    @Test
    public void testConfirmInteger() {
        String number = "10";
        boolean numberResult = true;
        
        String longNumber = "1000000000000000000000";
        boolean longNumberResult = false;
        
        String notNumber = "Aaa";
        boolean notNumberResult = false;
        
        String numbersAndLetters = "11123afdaFADS1";
        boolean numbersAndLettersResult = false;
        
        
        assertEquals(numberResult, Numeric.confirmInteger(number));
        
        assertEquals(longNumberResult, Numeric.confirmInteger(longNumber));
        
        assertEquals(notNumberResult, Numeric.confirmInteger(notNumber));
        
        assertEquals(numbersAndLettersResult, Numeric.confirmInteger(numbersAndLetters));
        
        assertEquals(false, Numeric.confirmInteger(""));
        
        assertEquals(false, Numeric.confirmInteger(null));
        
        assertEquals(true, Numeric.confirmInteger("-10"));
        assertEquals(true, Numeric.confirmInteger("0"));
        
    }
    
}
