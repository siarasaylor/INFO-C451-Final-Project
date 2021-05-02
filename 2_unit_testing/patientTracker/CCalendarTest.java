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
public class CCalendarTest {
    
    public CCalendarTest() {
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
     * Test of initCalendar method, of class CCalendar.
     */
    @Test
    public void testInitCalendar() {
        System.out.println("initCalendar");
        CCalendar instance = new CCalendar();
        instance.initCalendar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toPrevMonth method, of class CCalendar.
     */
    @Test
    public void testToPrevMonth() {
        System.out.println("toPrevMonth");
        CCalendar instance = new CCalendar();
        instance.toPrevMonth();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toCurrentMonth method, of class CCalendar.
     */
    @Test
    public void testToCurrentMonth() {
        System.out.println("toCurrentMonth");
        CCalendar instance = new CCalendar();
        instance.toCurrentMonth();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toNextMonth method, of class CCalendar.
     */
    @Test
    public void testToNextMonth() {
        System.out.println("toNextMonth");
        CCalendar instance = new CCalendar();
        instance.toNextMonth();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toDate method, of class CCalendar.
     */
    @Test
    public void testToDate() {
        System.out.println("toDate");
        Integer month = null;
        Integer day = null;
        Integer year = null;
        CCalendar instance = new CCalendar();
        instance.toDate(month, day, year);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class CCalendar.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Integer month = null;
        Integer day = null;
        Integer year = null;
        CCalendar instance = new CCalendar();
        Date expResult = null;
        Date result = instance.getDate(month, day, year);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
