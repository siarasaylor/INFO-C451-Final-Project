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
public class CYearIT {
    
    public CYearIT() {
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
     * Test of getPreviousYear method, of class CYear.
     */
    @Test
    public void testGetPreviousYear() {
        System.out.println("getPreviousYear");
        CYear instance = new CYear();
        Integer expResult = null;
        Integer result = instance.getPreviousYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextYear method, of class CYear.
     */
    @Test
    public void testGetNextYear() {
        System.out.println("getNextYear");
        CYear instance = new CYear();
        Integer expResult = null;
        Integer result = instance.getNextYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentYear method, of class CYear.
     */
    @Test
    public void testGetCurrentYear() {
        System.out.println("getCurrentYear");
        CYear instance = new CYear();
        Integer expResult = null;
        Integer result = instance.getCurrentYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveYear method, of class CYear.
     */
    @Test
    public void testGetActiveYear() {
        System.out.println("getActiveYear");
        CYear instance = new CYear();
        Integer expResult = null;
        Integer result = instance.getActiveYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActiveYear method, of class CYear.
     */
    @Test
    public void testSetActiveYear() {
        System.out.println("setActiveYear");
        Integer year = null;
        CYear instance = new CYear();
        instance.setActiveYear(year);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousYear method, of class CYear.
     */
    @Test
    public void testSetPreviousYear() {
        System.out.println("setPreviousYear");
        CYear instance = new CYear();
        instance.setPreviousYear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNextYear method, of class CYear.
     */
    @Test
    public void testSetNextYear() {
        System.out.println("setNextYear");
        CYear instance = new CYear();
        instance.setNextYear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentYear method, of class CYear.
     */
    @Test
    public void testSetCurrentYear() {
        System.out.println("setCurrentYear");
        CYear instance = new CYear();
        instance.setCurrentYear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
