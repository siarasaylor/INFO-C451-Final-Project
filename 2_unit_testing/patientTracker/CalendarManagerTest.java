/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.sql.Time;
import java.util.ArrayList;
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
public class CalendarManagerTest {
    
    public CalendarManagerTest() {
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
     * Test of getAppointments method, of class CalendarManager.
     */
    @Test
    public void testGetAppointments() {
        System.out.println("getAppointments");
        Date date = null;
        CalendarManager instance = new CalendarManager();
        ArrayList<Appointment> expResult = null;
        ArrayList<Appointment> result = instance.getAppointments(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAppointment method, of class CalendarManager.
     */
    @Test
    public void testAddAppointment() {
        System.out.println("addAppointment");
        Date date = null;
        String title = "";
        String description = "";
        String location = "";
        Time startTime = null;
        Time endTime = null;
        CalendarManager instance = new CalendarManager();
        boolean expResult = false;
        boolean result = instance.addAppointment(date, title, description, location, startTime, endTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAppointment method, of class CalendarManager.
     */
    @Test
    public void testDeleteAppointment() {
        System.out.println("deleteAppointment");
        Integer appointmentId = null;
        CalendarManager instance = new CalendarManager();
        boolean expResult = false;
        boolean result = instance.deleteAppointment(appointmentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editAppointment method, of class CalendarManager.
     */
    @Test
    public void testEditAppointment() {
        System.out.println("editAppointment");
        Integer appointmentId = null;
        CalendarManager instance = new CalendarManager();
        boolean expResult = false;
        boolean result = instance.editAppointment(appointmentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
