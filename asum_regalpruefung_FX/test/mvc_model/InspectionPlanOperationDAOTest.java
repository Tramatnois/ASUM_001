/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import java.sql.Date;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danie
 */
public class InspectionPlanOperationDAOTest {
    
  InspectionPlanOperationDAO inspectionPlanOperationDAO;
  
    public InspectionPlanOperationDAOTest() {
            inspectionPlanOperationDAO = new InspectionPlanOperationDAO();
    }

    /**
     * Test of selectAllInspectionPlanOperations method, of class InspectionPlanOperationDAO.
     */
    
    @Test
    public void testSelectAllInspectionPlanOperations() throws Exception {
        
        
        
        System.out.println("selectAllInspectionPlanOperations");
        
       
        ArrayList<InspectionPlanOperationDTO> expResult = inspectionPlanOperationDAO.selectAllInspectionPlanOperations();
        assertEquals(expResult.get(0).getNorm(),"DIN EN 15635");
        assertEquals(expResult.get(0).getInspectionPlanOperationStatus().getDescription(), "neu");
        assertEquals(expResult.get(1).getDescription(), "Beschreibung");
        
    }

    /**
     * Test of selectSingleById method, of class InspectionPlanOperationDAO.
     */
    @Test
    public void testSelectSingleById() throws Exception {
        System.out.println("selectSingleById");
        
        int id = 1;
        InspectionPlanOperationDTO expResult = inspectionPlanOperationDAO.selectSingleById(id);
        assertEquals(expResult.getNorm(), "DIN EN 15635");
        assertEquals(expResult.getInspectionPlanOperationStatus().getDescription(), "neu");
    }
    @Test
    public void testSelectSingleFullByIdWithoutTemplate() throws Exception {
        System.out.println("selectSingleById");
        
        int id = 1;
        InspectionPlanOperationDTO expResult = inspectionPlanOperationDAO.selectSingleFullLoadByIdWithoutTemplate(id);
        assertEquals(expResult.getNorm(), "DIN EN 15635");
        assertEquals("neu", expResult.getInspectionPlanOperationStatus().getDescription());
        assertEquals( "Schaffhausen", expResult.getCustomer().getCity());
        assertEquals("Hindenburgstrasse 30",expResult.getInspector().getStreet());
    }
    /**
     * Test of insert method, of class InspectionPlanOperationDAO.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        int generatedkey;
        InspectionPlanOperationDTO inspectionPlanOperationDTO = new InspectionPlanOperationDTO();
        InspectionPlanOperationDAO instance = new InspectionPlanOperationDAO();
        CharacteristicGroupOperationDTO characteristicGroupOperationDTO = new CharacteristicGroupOperationDTO();
        characteristicGroupOperationDTO.setId(1);
        inspectionPlanOperationDTO.setDate(new Date(2017, 5, 1));
        
        
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1);
        inspectionPlanOperationDTO.setCustomer(customerDTO);
        
        inspectionPlanOperationDTO.setDescription("Beschreibung");
                
        InspectionPlanOperationStatusDTO inspectionPlanOperationStatusDTO = new InspectionPlanOperationStatusDTO();
        inspectionPlanOperationStatusDTO.setId(1);
        inspectionPlanOperationDTO.setInspectionPlanOperationStatus(inspectionPlanOperationStatusDTO);
        
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO = new InspectionPlanTemplateDTO();
        inspectionPlanTemplateDTO.setId(1);
        inspectionPlanOperationDTO.setInspectionplanTemplate(inspectionPlanTemplateDTO);
        
        InspectorDTO inspectorDTO = new InspectorDTO();
        inspectorDTO.setId(1);
        inspectionPlanOperationDTO.setInspector(inspectorDTO);
        
        inspectionPlanOperationDTO.setLocation("Oben");
        
        inspectionPlanOperationDTO.setNorm("Norm 1");
        
        inspectionPlanOperationDTO.setStorageRack("4711");
        
        generatedkey = inspectionPlanOperationDAO.insert(inspectionPlanOperationDTO);
        
        inspectionPlanOperationDTO = null;
        inspectionPlanOperationDTO = inspectionPlanOperationDAO.selectSingleById(generatedkey);
                
        
        assertEquals(inspectionPlanOperationDTO.getNorm(), "Norm 1");
        assertEquals(inspectionPlanOperationDTO.getInspectionPlanOperationStatus().getDescription(), "neu");
        inspectionPlanOperationDAO.delete(inspectionPlanOperationDTO);
      
    }

    /**
     * Test of update method, of class InspectionPlanOperationDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        int generatedkey;
        InspectionPlanOperationDTO inspectionPlanOperationDTO = new InspectionPlanOperationDTO();
        InspectionPlanOperationDAO instance = new InspectionPlanOperationDAO();
        CharacteristicGroupOperationDTO characteristicGroupOperationDTO = new CharacteristicGroupOperationDTO();
        characteristicGroupOperationDTO.setId(1);
        inspectionPlanOperationDTO.setDate(new Date(2017, 5, 1));
        
        
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1);
        inspectionPlanOperationDTO.setCustomer(customerDTO);
        
        inspectionPlanOperationDTO.setDescription("Beschreibung");
                
        InspectionPlanOperationStatusDTO inspectionPlanOperationStatusDTO = new InspectionPlanOperationStatusDTO();
        inspectionPlanOperationStatusDTO.setId(1);
        inspectionPlanOperationDTO.setInspectionPlanOperationStatus(inspectionPlanOperationStatusDTO);
        
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO = new InspectionPlanTemplateDTO();
        inspectionPlanTemplateDTO.setId(1);
        inspectionPlanOperationDTO.setInspectionplanTemplate(inspectionPlanTemplateDTO);
        
        InspectorDTO inspectorDTO = new InspectorDTO();
        inspectorDTO.setId(1);
        inspectionPlanOperationDTO.setInspector(inspectorDTO);
        
        inspectionPlanOperationDTO.setLocation("Oben");
        
        inspectionPlanOperationDTO.setNorm("Norm 1");
        
        inspectionPlanOperationDTO.setStorageRack("4711");
        
        generatedkey = inspectionPlanOperationDAO.insert(inspectionPlanOperationDTO);
        
        inspectionPlanOperationDTO = null;
        inspectionPlanOperationDTO = inspectionPlanOperationDAO.selectSingleById(generatedkey);
                
        inspectionPlanOperationDTO.setStorageRack("4712");
        inspectionPlanOperationDAO.update(inspectionPlanOperationDTO);
        inspectionPlanOperationDTO = null;
        inspectionPlanOperationDTO = inspectionPlanOperationDAO.selectSingleById(generatedkey);
        assertEquals(inspectionPlanOperationDTO.getStorageRack(), "4712");
        
        
        inspectionPlanOperationDAO.delete(inspectionPlanOperationDTO);
    }

    /**
     * Test of delete method, of class InspectionPlanOperationDAO.
     */
    @Test
    public void testDelete() throws Exception {
        int generatedkey;
        InspectionPlanOperationDTO inspectionPlanOperationDTO = new InspectionPlanOperationDTO();
        InspectionPlanOperationDAO instance = new InspectionPlanOperationDAO();
        CharacteristicGroupOperationDTO characteristicGroupOperationDTO = new CharacteristicGroupOperationDTO();
        characteristicGroupOperationDTO.setId(1);
        inspectionPlanOperationDTO.setDate(new Date(2017, 5, 1));
        
        
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1);
        inspectionPlanOperationDTO.setCustomer(customerDTO);
        
        inspectionPlanOperationDTO.setDescription("Beschreibung");
                
        InspectionPlanOperationStatusDTO inspectionPlanOperationStatusDTO = new InspectionPlanOperationStatusDTO();
        inspectionPlanOperationStatusDTO.setId(1);
        inspectionPlanOperationDTO.setInspectionPlanOperationStatus(inspectionPlanOperationStatusDTO);
        
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO = new InspectionPlanTemplateDTO();
        inspectionPlanTemplateDTO.setId(1);
        inspectionPlanOperationDTO.setInspectionplanTemplate(inspectionPlanTemplateDTO);
        
        InspectorDTO inspectorDTO = new InspectorDTO();
        inspectorDTO.setId(1);
        inspectionPlanOperationDTO.setInspector(inspectorDTO);
        
        inspectionPlanOperationDTO.setLocation("Oben");
        
        inspectionPlanOperationDTO.setNorm("Norm 1");
        
        inspectionPlanOperationDTO.setStorageRack("4711");
        
        generatedkey = inspectionPlanOperationDAO.insert(inspectionPlanOperationDTO);
        inspectionPlanOperationDTO = inspectionPlanOperationDAO.selectSingleById(generatedkey);
        inspectionPlanOperationDAO.delete(inspectionPlanOperationDTO);
        inspectionPlanOperationDTO = null;
        inspectionPlanOperationDTO = inspectionPlanOperationDAO.selectSingleById(generatedkey);
        if (inspectionPlanOperationDTO == null)
            assertTrue(true);
        else
            assertTrue(false);
            
        
    }
    
}
