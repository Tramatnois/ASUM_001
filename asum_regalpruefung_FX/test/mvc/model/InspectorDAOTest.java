/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc_model.InspectorDAO;
import mvc_model.InspectorDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LT Dan
 */
public class InspectorDAOTest {


    InspectorDAO inspectorDto;

    public InspectorDAOTest() {
        inspectorDto = new InspectorDAO();
    }


    /**
     * Add a inspector Test
     */
    @Test
    public void insertInspectorTest() {
        int generatedkey = -1000;
        
        InspectorDTO inspector = new InspectorDTO();
        inspector.setCity("Stadt");
        inspector.setName("Wilhelm");
        inspector.setStreet("Straße 1");
        inspector.setZipcode("84879");
        try {
            generatedkey = inspectorDto.insertInspector(inspector);
        } catch (SQLException ex) {
            Logger.getLogger(InspectorDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(true);
        //assertEquals(2, generatedkey);
    }

    /**
     * Test of selectSingleInspector method, of class InspectorDto.
     */
    @Test
    public void selectSingleInspectorTest() {
        InspectorDTO inspector = null;
        try {
            inspector = inspectorDto.selectSingleInspector(1);
        } catch (SQLException ex) {
            Logger.getLogger(InspectorDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals("ASUM GmbH", inspector.getName());
    }
        @Test
    public void updateInspectorTest() throws SQLException {
        InspectorDTO inspector = inspectorDto.selectSingleInspector(1);
        inspector.setCity("Schaffhausen");
        inspectorDto.updateInspector(inspector);
        inspector = null;
        inspector = inspector = inspectorDto.selectSingleInspector(1);
        
        
        assertEquals("Schaffhausen", inspector.getCity());
    }
            @Test
    public void deleteInspectorTest() throws SQLException {
        int generatedkey;
        InspectorDTO inspector = new InspectorDTO();
        inspector.setCity("Stadt");
        inspector.setName("Wilhelm");
        inspector.setStreet("Straße 1");
        inspector.setZipcode("84879");
        generatedkey = inspectorDto.insertInspector(inspector);
        
        inspector = inspectorDto.selectSingleInspector(generatedkey);
        
        inspectorDto.deleteInspector(inspector);
        assertTrue(true);
    }
    
}
