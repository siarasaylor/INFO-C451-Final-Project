/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import javax.swing.ButtonGroup;
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
public class NewAppointment2IT {
    
    public NewAppointment2IT() {
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
     * Test of schedNewAppt method, of class NewAppointment2.
     */
    @Test
    public void testSchedNewAppt() {
        System.out.println("schedNewAppt");
        NewAppointment2.schedNewAppt();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedButtonText method, of class NewAppointment2.
     */
    @Test
    public void testGetSelectedButtonText() {
        System.out.println("getSelectedButtonText");
        ButtonGroup buttonGroup = null;
        NewAppointment2 instance = new NewAppointment2();
        String expResult = "";
        String result = instance.getSelectedButtonText(buttonGroup);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
