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
public class MainFrameTest {
    
    public MainFrameTest() {
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
     * Test of getMainFrameHeight method, of class MainFrame.
     */
    @Test
    public void testGetMainFrameHeight() {
        System.out.println("getMainFrameHeight");
        MainFrame instance = new MainFrame();
        Integer expResult = null;
        Integer result = instance.getMainFrameHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMainFrameWidth method, of class MainFrame.
     */
    @Test
    public void testGetMainFrameWidth() {
        System.out.println("getMainFrameWidth");
        MainFrame instance = new MainFrame();
        Integer expResult = null;
        Integer result = instance.getMainFrameWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrameDimension method, of class MainFrame.
     */
    @Test
    public void testSetFrameDimension() {
        System.out.println("setFrameDimension");
        boolean resized = false;
        MainFrame instance = new MainFrame();
        instance.setFrameDimension(resized);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
