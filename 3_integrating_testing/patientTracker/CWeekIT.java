/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.util.Date;
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
public class CWeekIT {
    
    public CWeekIT() {
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
     * Test of getPreviousWeek method, of class CWeek.
     */
    @Test
    public void testGetPreviousWeek() {
        System.out.println("getPreviousWeek");
        CWeek instance = new CWeek();
        Integer expResult = null;
        Integer result = instance.getPreviousWeek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextWeek method, of class CWeek.
     */
    @Test
    public void testGetNextWeek() {
        System.out.println("getNextWeek");
        CWeek instance = new CWeek();
        Integer expResult = null;
        Integer result = instance.getNextWeek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentWeek method, of class CWeek.
     */
    @Test
    public void testGetCurrentWeek() {
        System.out.println("getCurrentWeek");
        CWeek instance = new CWeek();
        Integer expResult = null;
        Integer result = instance.getCurrentWeek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveWeek method, of class CWeek.
     */
    @Test
    public void testGetActiveWeek() {
        System.out.println("getActiveWeek");
        CWeek instance = new CWeek();
        Integer expResult = null;
        Integer result = instance.getActiveWeek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousWeek method, of class CWeek.
     */
    @Test
    public void testSetPreviousWeek() {
        System.out.println("setPreviousWeek");
        CWeek instance = new CWeek();
        instance.setPreviousWeek();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNextWeek method, of class CWeek.
     */
    @Test
    public void testSetNextWeek() {
        System.out.println("setNextWeek");
        CWeek instance = new CWeek();
        instance.setNextWeek();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentWeek method, of class CWeek.
     */
    @Test
    public void testSetCurrentWeek() {
        System.out.println("setCurrentWeek");
        CWeek instance = new CWeek();
        instance.setCurrentWeek();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActiveWeek method, of class CWeek.
     */
    @Test
    public void testSetActiveWeek() {
        System.out.println("setActiveWeek");
        Integer week = null;
        CWeek instance = new CWeek();
        instance.setActiveWeek(week);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeekNumber method, of class CWeek.
     */
    @Test
    public void testGetWeekNumber() {
        System.out.println("getWeekNumber");
        Date date = null;
        CWeek instance = new CWeek();
        Integer expResult = null;
        Integer result = instance.getWeekNumber(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeekDayName method, of class CWeek.
     */
    @Test
    public void testGetWeekDayName() {
        System.out.println("getWeekDayName");
        Date date = null;
        CWeek instance = new CWeek();
        String expResult = "";
        String result = instance.getWeekDayName(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
