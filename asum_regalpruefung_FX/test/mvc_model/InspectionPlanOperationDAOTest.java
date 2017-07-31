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
        assertEquals(expResult.get(1).getDescription(), "Regalprüfung nach DIN EN 9999");
        
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
        inspectionPlanOperationDAO.delete(inspectionPlanOperationDTO);
      
    }

    /**
     * Test of update method, of class InspectionPlanOperationDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        InspectionPlanOperationDTO inspectionPlanOperationDTO = null;
        InspectionPlanOperationDAO instance = new InspectionPlanOperationDAO();
        instance.update(inspectionPlanOperationDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class InspectionPlanOperationDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        InspectionPlanOperationDTO inspectionPlanOperationDTO = null;
        InspectionPlanOperationDAO instance = new InspectionPlanOperationDAO();
        instance.delete(inspectionPlanOperationDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
