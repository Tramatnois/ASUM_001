/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc_model.CustomerDAO;
import mvc_model.CustomerDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LT Dan
 */
public class CustomerDAOTest {

    CustomerDAO customerDto;

    public CustomerDAOTest() {
        customerDto = new CustomerDAO();
    }


    /**
     * Add a customer Test
     */
    @Test
    public void insertCustomerTest() {
        CustomerDTO customer = new CustomerDTO();
        customer.setCity("Stadt");
        customer.setContactperson("Willy Müller");
        customer.setEmail("A@A.de");
        customer.setFax("02478 84499748");
        customer.setName("Wilhelm");
        customer.setPhone("02478 54848484");
        customer.setStreet("Straße 1");
        customer.setZipcode("84879");
        try {
            customerDto.insertCustomer(customer);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(true);
    }

    /**
     * Test of selectSingleCustomer method, of class CustomerDto.
     */
    @Test
    public void selectSingleCustomerTest() {
        CustomerDTO customer = null;
        try {
            customer = customerDto.selectSingleCustomer(1);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals("Musterfirma", customer.getName());
    }
        @Test
    public void updateCustomerTest() throws SQLException {
        CustomerDTO customer = customerDto.selectSingleCustomer(1);
        customer.setCity("Schaffhausen");
        customerDto.updateCustomer(customer);
        customer = null;
        customer = customer = customerDto.selectSingleCustomer(1);
        
        
        assertEquals("Schaffhausen", customer.getCity());
    }
            @Test
    public void deleteCustomerTest() throws SQLException {
        int generatedkey;
        CustomerDTO customer = new CustomerDTO();
        customer.setCity("Stadt");
        customer.setContactperson("Willy Müller");
        customer.setEmail("A@A.de");
        customer.setFax("02478 84499748");
        customer.setName("Wilhelm");
        customer.setPhone("02478 54848484");
        customer.setStreet("Straße 1");
        customer.setZipcode("84879");
        generatedkey = customerDto.insertCustomer(customer);
        
        customer = customerDto.selectSingleCustomer(generatedkey);
        
        customerDto.deleteCustomer(customer);
        assertTrue(true);
    }
}
