/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danie
 */
public class CharacteristicTemplateDAOTest {
    CharacteristicTemplateDAO characteristicTemplateDAO;
    public CharacteristicTemplateDAOTest() {
        characteristicTemplateDAO = new CharacteristicTemplateDAO();
    }
    
    

    /**
     * Test of selectSingleCharacteristicTemplate method, of class CharacteristicTemplateDAO.
     */
    @Test
    public void testSelectSingleCharacteristicTemplate() throws Exception {
        System.out.println("selectSingleCharacteristicTemplate");
        int id = 1;
        CharacteristicTemplateDTO expResult = characteristicTemplateDAO.selectSingleCharacteristicTemplate(id);
        assertEquals(expResult.getDescription(), "Entspricht der Aufbau des Regals den Herstellerangaben");
    }

    /**
     * Test of selectAllCharacteristicTemplate method, of class CharacteristicTemplateDAO.
     */
    @Test
    public void testSelectAllCharacteristicTemplate() throws Exception {
        System.out.println("selectAllCharacteristicTemplate");
        ArrayList<CharacteristicTemplateDTO> expResult = characteristicTemplateDAO.selectAllCharacteristicTemplate();
        assertEquals(expResult.get(0).getDescription(), "Entspricht der Aufbau des Regals den Herstellerangaben");
        assertEquals(expResult.get(1).getPosition(),1);

    }

    /**
     * Test of insertCharacteristicTemplate method, of class CharacteristicTemplateDAO.
     */
    @Test
    public void testInsertCharacteristicTemplate() throws Exception {
        System.out.println("insertCharacteristicTemplate");
        CharacteristicTemplateDTO characteristicTemplate = null;
        CharacteristicTemplateDAO instance = new CharacteristicTemplateDAO();
        instance.insertCharacteristicTemplate(characteristicTemplate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCharacteristicTemplate method, of class CharacteristicTemplateDAO.
     */
    @Test
    public void testUpdateCharacteristicTemplate() throws Exception {
        System.out.println("updateCharacteristicTemplate");
        CharacteristicTemplateDTO characteristicTemplate = null;
        CharacteristicTemplateDAO instance = new CharacteristicTemplateDAO();
        instance.updateCharacteristicTemplate(characteristicTemplate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCharacteristicTemplate method, of class CharacteristicTemplateDAO.
     */
    @Test
    public void testDeleteCharacteristicTemplate() throws Exception {
        System.out.println("deleteCharacteristicTemplate");
        int generatedkey;
        CharacteristicTemplateDTO characteristicTemplate = new CharacteristicTemplateDTO();
 
        characteristicTemplate.setDescription("I will be deleted");
        characteristicTemplate.setPosition(0);
        CharacteristicGroupTemplateDTO characteristicGroupTemplateDTO = new CharacteristicGroupTemplateDTO();
        characteristicGroupTemplateDTO.setId(1);
        characteristicTemplate.setCharacteristicgroup(characteristicGroupTemplateDTO);
        CharacteristicTypeDTO characteristicTypeDTO = new CharacteristicTypeDTO();
        characteristicTypeDTO.setId(1);
        characteristicTemplate.setCharacteristicType(characteristicTypeDTO);
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO = new InspectionPlanTemplateDTO();
        inspectionPlanTemplateDTO.setId(1);
        characteristicTemplate.setInspectionPlan(inspectionPlanTemplateDTO);
        
        generatedkey = characteristicTemplateDAO.insertCharacteristicTemplate(characteristicTemplate);
        
        characteristicTemplate = characteristicTemplateDAO.selectSingleCharacteristicTemplate(generatedkey);
        characteristicTemplateDAO.deleteCharacteristicTemplate(characteristicTemplate);

            characteristicTemplate = characteristicTemplateDAO.selectSingleCharacteristicTemplate(generatedkey);
        if(characteristicTemplate == null)
            assertTrue(true);
        else
            assertTrue(false);
    }
    
}
