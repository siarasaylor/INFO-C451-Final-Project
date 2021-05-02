/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patienttracker;

import java.awt.Component;
import javax.swing.JTable;
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
public class ButtonEditorTest {
    
    public ButtonEditorTest() {
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
     * Test of getTableCellEditorComponent method, of class ButtonEditor.
     */
    @Test
    public void testGetTableCellEditorComponent() {
        System.out.println("getTableCellEditorComponent");
        JTable table = null;
        Object value = null;
        boolean isSelected = false;
        int row = 0;
        int column = 0;
        ButtonEditor instance = null;
        Component expResult = null;
        Component result = instance.getTableCellEditorComponent(table, value, isSelected, row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCellEditorValue method, of class ButtonEditor.
     */
    @Test
    public void testGetCellEditorValue() {
        System.out.println("getCellEditorValue");
        ButtonEditor instance = null;
        Object expResult = null;
        Object result = instance.getCellEditorValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopCellEditing method, of class ButtonEditor.
     */
    @Test
    public void testStopCellEditing() {
        System.out.println("stopCellEditing");
        ButtonEditor instance = null;
        boolean expResult = false;
        boolean result = instance.stopCellEditing();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
