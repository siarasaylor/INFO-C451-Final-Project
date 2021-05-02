/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author siarasaylor
 */
public class CDayIT {
    
    public CDayIT() {
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
     * Test of getPreviousDay method, of class CDay.
     */
    @Test
    public void testGetPreviousDay() {
        System.out.println("getPreviousDay");
        CDay instance = new CDay();
        Integer expResult = null;
        Integer result = instance.getPreviousDay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextDay method, of class CDay.
     */
    @Test
    public void testGetNextDay() {
        System.out.println("getNextDay");
        CDay instance = new CDay();
        Integer expResult = null;
        Integer result = instance.getNextDay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentDay method, of class CDay.
     */
    @Test
    public void testGetCurrentDay() {
        System.out.println("getCurrentDay");
        CDay instance = new CDay();
        Integer expResult = null;
        Integer result = instance.getCurrentDay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveDay method, of class CDay.
     */
    @Test
    public void testGetActiveDay() {
        System.out.println("getActiveDay");
        CDay instance = new CDay();
        Integer expResult = null;
        Integer result = instance.getActiveDay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActiveDay method, of class CDay.
     */
    @Test
    public void testSetActiveDay() {
        System.out.println("setActiveDay");
        Integer day = null;
        Integer monthDays = null;
        CDay instance = new CDay();
        instance.setActiveDay(day, monthDays);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousDay method, of class CDay.
     */
    @Test
    public void testSetPreviousDay() {
        System.out.println("setPreviousDay");
        CDay instance = new CDay();
        instance.setPreviousDay();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNextDay method, of class CDay.
     */
    @Test
    public void testSetNextDay() {
        System.out.println("setNextDay");
        CDay instance = new CDay();
        instance.setNextDay();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentDay method, of class CDay.
     */
    @Test
    public void testSetCurrentDay() {
        System.out.println("setCurrentDay");
        CDay instance = new CDay();
        instance.setCurrentDay();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
