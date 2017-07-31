/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

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
        InspectionPlanOperationDTO inspectionPlanOperationDTO = null;
        InspectionPlanOperationDAO instance = new InspectionPlanOperationDAO();
        int expResult = 0;
        int result = instance.insert(inspectionPlanOperationDTO);
        assertEquals(expResult, "DIN EN 15635");
      
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
