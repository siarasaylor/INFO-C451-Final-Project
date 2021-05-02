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
public class CalendarPanelTest {
    
    public CalendarPanelTest() {
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
     * Test of resizeCalendarPanel method, of class CalendarPanel.
     */
    @Test
    public void testResizeCalendarPanel() {
        System.out.println("resizeCalendarPanel");
        CalendarPanel instance = null;
        instance.resizeCalendarPanel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCalendarPanelWidth method, of class CalendarPanel.
     */
    @Test
    public void testGetCalendarPanelWidth() {
        System.out.println("getCalendarPanelWidth");
        CalendarPanel instance = null;
        Integer expResult = null;
        Integer result = instance.getCalendarPanelWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCalendarPanelHeight method, of class CalendarPanel.
     */
    @Test
    public void testGetCalendarPanelHeight() {
        System.out.println("getCalendarPanelHeight");
        CalendarPanel instance = null;
        Integer expResult = null;
        Integer result = instance.getCalendarPanelHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawMonthPanel method, of class CalendarPanel.
     */
    @Test
    public void testDrawMonthPanel() {
        System.out.println("drawMonthPanel");
        CalendarPanel instance = null;
        instance.drawMonthPanel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
