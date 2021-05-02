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
public class MainPanelTest {
    
    public MainPanelTest() {
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
     * Test of getTopPanelWidth method, of class MainPanel.
     */
    @Test
    public void testGetTopPanelWidth() {
        System.out.println("getTopPanelWidth");
        MainPanel instance = null;
        Integer expResult = null;
        Integer result = instance.getTopPanelWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTopPanelHeight method, of class MainPanel.
     */
    @Test
    public void testGetTopPanelHeight() {
        System.out.println("getTopPanelHeight");
        MainPanel instance = null;
        Integer expResult = null;
        Integer result = instance.getTopPanelHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMonthYearLabelText method, of class MainPanel.
     */
    @Test
    public void testSetMonthYearLabelText() {
        System.out.println("setMonthYearLabelText");
        MainPanel instance = null;
        instance.setMonthYearLabelText();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateFieldText method, of class MainPanel.
     */
    @Test
    public void testSetDateFieldText() {
        System.out.println("setDateFieldText");
        MainPanel instance = null;
        instance.setDateFieldText();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
