/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bibtextingcompany.bibtexting;

import java.util.Random;
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
public class DataValidatorTest {

    public DataValidatorTest() {
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
     * Test of ValidateRangeOfNumbers method, of class DataValidator.
     */
    @Test
    public void testValidateText() {
        String input = "Something";
        int expResult = DataValidator.TEXT;
        int result = DataValidator.Validate(input, DataValidator.TEXT);

        System.out.println("Validating text: " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);

        input = "1234329423";
        expResult = DataValidator.ERROR_SYNTAX;
        result = DataValidator.Validate(input, DataValidator.TEXT);
        System.out.println("Validating non-text: " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);
    }

    /**
     * Test of ValidateRangeOfNumbers method, of class DataValidator.
     */
    @Test
    public void testBasicErrors() {
        Random randomi = new Random();

        String input = null;
        int expResult = DataValidator.ERROR_SYNTAX;
        int codeToTry = randomi.nextInt(3) + 1;
        int result = DataValidator.Validate(input, codeToTry);

        System.out.println("Validate null input: " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);

        input = "";
        expResult = DataValidator.ERROR_EMPTY_NOT_ALLOWED;
        codeToTry = randomi.nextInt(3) + 1;
        result = DataValidator.Validate(input, codeToTry);

        System.out.println("Validate empty input: " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);

        input = "Something or the other!";
        expResult = DataValidator.ERROR_DATATYPE_UNRECOGNIZABLE;
        codeToTry = 0 + (randomi.nextInt(2)*10) + (randomi.nextInt(2)*(-40));
        result = DataValidator.Validate(input, codeToTry);

        System.out.println("Validate false datatype {of type: "+codeToTry+"} with input: " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);

        
    }

    /**
     * Test of ValidateRangeOfNumbers method, of class DataValidator.
     */
    @Test
    public void testValidateTrueNumbers() {
        Random randomi = new Random();

        for (int i = 0; i < 10; i++) {
            int first = randomi.nextInt(10 + (i * i * i * i * i));

            String input = "" + first;
            int expResult = DataValidator.SINGLE_NUMBER;
            int result = DataValidator.Validate(input, DataValidator.SINGLE_NUMBER);

            System.out.println("{" + i + "} Validate TRUE single number " + input + ", expect code: " + expResult + ", receive code: " + result);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of ValidateRangeOfNumbers method, of class DataValidator.
     */
    @Test
    public void testValidateTrueRangeOfNumbers() {
        Random randomi = new Random();

        for (int i = 0; i < 10; i++) {
            int first = randomi.nextInt(1000);
            int second = randomi.nextInt(1000) + first;
            String middle = "-";
            if ((first + second) % 2 == 0) {
                middle = "--";
            }

            String input = first + middle + second;
            int expResult = DataValidator.RANGE_OF_NUMBERS;
            int result = DataValidator.Validate(input, DataValidator.RANGE_OF_NUMBERS);

            System.out.println("{" + i + "} Validate TRUE range of numbers " + input + ", expect code: " + expResult + ", receive code: " + result);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of ValidateRangeOfNumbers method, of class DataValidator.
     */
    @Test
    public void testValidateFalseRangeOfNumbers() {
        String input = "derp-0-15";
        int expResult = DataValidator.ERROR_NOT_A_NUMBER;
        int result = DataValidator.Validate(input, DataValidator.RANGE_OF_NUMBERS);

        System.out.println("i) Validate FALSE range of numbers " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);

        input = "25...15";
        result = DataValidator.Validate(input, DataValidator.RANGE_OF_NUMBERS);
        System.out.println("ii) Validate FALSE range of numbers " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);

        input = "15";
        result = DataValidator.Validate(input, DataValidator.RANGE_OF_NUMBERS);
        System.out.println("iii) Validate FALSE range of numbers " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);

        input = "15---25";
        result = DataValidator.Validate(input, DataValidator.RANGE_OF_NUMBERS);
        System.out.println("iv) Validate FALSE range of numbers " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);

        input = "15 -- 25";
        result = DataValidator.Validate(input, DataValidator.RANGE_OF_NUMBERS);
        System.out.println("v) Validate FALSE range of numbers " + input + ", expect code: " + expResult + ", receive code: " + result);
        assertEquals(result, expResult);
    }

}
