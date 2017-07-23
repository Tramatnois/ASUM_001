/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import mvc_model.CustomerDTO;
import mvc_model.CustomerDAO;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tramatnois
 */
public class CustomerDAOTest {
    
    public CustomerDAOTest() {
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
     * Test of selectSingleCustomer method, of class CustomerDAO.
     */
    @Test
    public void testSelectSingleCustomer() throws Exception {
        System.out.println("selectSingleCustomer");
        int idcustomer = 0;
        CustomerDAO instance = new CustomerDAO();
        CustomerDTO expResult = null;
        CustomerDTO result = instance.selectSingleCustomer(idcustomer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectAllCustomer method, of class CustomerDAO.
     */
    @Test
    public void testSelectAllCustomer() throws Exception {
        System.out.println("selectAllCustomer");
        CustomerDAO instance = new CustomerDAO();
        ArrayList<CustomerDTO> expResult = null;
        ArrayList<CustomerDTO> result = instance.selectAllCustomer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCustomer method, of class CustomerDAO.
     */
    @Test
    public void testInsertCustomer() throws Exception {
        System.out.println("insertCustomer");
        CustomerDTO customer = null;
        CustomerDAO instance = new CustomerDAO();
        instance.insertCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateCustomer method, of class CustomerDAO.
     */
    @Test
    public void testUpdateCustomer() throws Exception {
        System.out.println("updateCustomer");
        CustomerDTO customer = null;
        CustomerDAO instance = new CustomerDAO();
        instance.updateCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomer method, of class CustomerDAO.
     */
    @Test
    public void testDeleteCustomer() throws Exception {
        System.out.println("deleteCustomer");
        CustomerDTO customer = null;
        CustomerDAO instance = new CustomerDAO();
        instance.deleteCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
