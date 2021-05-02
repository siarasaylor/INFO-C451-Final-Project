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
public class CMonthTest {
    
    public CMonthTest() {
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
     * Test of getPreviousMonth method, of class CMonth.
     */
    @Test
    public void testGetPreviousMonth() {
        System.out.println("getPreviousMonth");
        CMonth instance = new CMonth();
        Integer expResult = null;
        Integer result = instance.getPreviousMonth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextMonth method, of class CMonth.
     */
    @Test
    public void testGetNextMonth() {
        System.out.println("getNextMonth");
        CMonth instance = new CMonth();
        Integer expResult = null;
        Integer result = instance.getNextMonth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentMonth method, of class CMonth.
     */
    @Test
    public void testGetCurrentMonth() {
        System.out.println("getCurrentMonth");
        CMonth instance = new CMonth();
        Integer expResult = null;
        Integer result = instance.getCurrentMonth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveMonth method, of class CMonth.
     */
    @Test
    public void testGetActiveMonth() {
        System.out.println("getActiveMonth");
        CMonth instance = new CMonth();
        Integer expResult = null;
        Integer result = instance.getActiveMonth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousMonth method, of class CMonth.
     */
    @Test
    public void testSetPreviousMonth() {
        System.out.println("setPreviousMonth");
        CMonth instance = new CMonth();
        instance.setPreviousMonth();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNextMonth method, of class CMonth.
     */
    @Test
    public void testSetNextMonth() {
        System.out.println("setNextMonth");
        CMonth instance = new CMonth();
        instance.setNextMonth();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentMonth method, of class CMonth.
     */
    @Test
    public void testSetCurrentMonth() {
        System.out.println("setCurrentMonth");
        CMonth instance = new CMonth();
        instance.setCurrentMonth();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActiveMonth method, of class CMonth.
     */
    @Test
    public void testSetActiveMonth() {
        System.out.println("setActiveMonth");
        Integer month = null;
        CMonth instance = new CMonth();
        instance.setActiveMonth(month);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonthName method, of class CMonth.
     */
    @Test
    public void testGetMonthName() {
        System.out.println("getMonthName");
        Integer month = null;
        CMonth instance = new CMonth();
        String expResult = "";
        String result = instance.getMonthName(month);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDayCount method, of class CMonth.
     */
    @Test
    public void testGetDayCount() {
        System.out.println("getDayCount");
        Integer month = null;
        Integer year = null;
        CMonth instance = new CMonth();
        Integer expResult = null;
        Integer result = instance.getDayCount(month, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstWeekDay method, of class CMonth.
     */
    @Test
    public void testGetFirstWeekDay() {
        System.out.println("getFirstWeekDay");
        Integer month = null;
        Integer year = null;
        CMonth instance = new CMonth();
        Integer expResult = null;
        Integer result = instance.getFirstWeekDay(month, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
