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
public class PatientMenuIT {
    
    public PatientMenuIT() {
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
     * Test of patient method, of class PatientMenu.
     */
    @Test
    public void testPatient() {
        System.out.println("patient");
        PatientMenu.patient();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replaceSelected method, of class PatientMenu.
     */
    @Test
    public void testReplaceSelected() {
        System.out.println("replaceSelected");
        String replaceWith = "";
        String type = "";
        PatientMenu.replaceSelected(replaceWith, type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
