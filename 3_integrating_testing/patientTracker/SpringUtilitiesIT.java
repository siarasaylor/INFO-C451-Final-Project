/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.Component;
import java.awt.Container;
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
public class SpringUtilitiesIT {
    
    public SpringUtilitiesIT() {
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
     * Test of printSizes method, of class SpringUtilities.
     */
    @Test
    public void testPrintSizes() {
        System.out.println("printSizes");
        Component c = null;
        SpringUtilities.printSizes(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeGrid method, of class SpringUtilities.
     */
    @Test
    public void testMakeGrid() {
        System.out.println("makeGrid");
        Container parent = null;
        int rows = 0;
        int cols = 0;
        int initialX = 0;
        int initialY = 0;
        int xPad = 0;
        int yPad = 0;
        SpringUtilities.makeGrid(parent, rows, cols, initialX, initialY, xPad, yPad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeCompactGrid method, of class SpringUtilities.
     */
    @Test
    public void testMakeCompactGrid() {
        System.out.println("makeCompactGrid");
        Container parent = null;
        int rows = 0;
        int cols = 0;
        int initialX = 0;
        int initialY = 0;
        int xPad = 0;
        int yPad = 0;
        SpringUtilities.makeCompactGrid(parent, rows, cols, initialX, initialY, xPad, yPad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
