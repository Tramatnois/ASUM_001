/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CustomerDtoTest {

    CustomerDto customerDto;

    public CustomerDtoTest() {
        customerDto = new CustomerDto();
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
     * Hinzufügren eines Kunden
     */
    @Test
    public void InsertCustomer() {
        CustomerDao customer = new CustomerDao();
        customer.setCity("Stadt");
        customer.setContactperson("Willy Müller");
        customer.setEmail("A@A.de");
        customer.setFax("02478 84499748");
        customer.setName("Wilhelm");
        customer.setPhone("02478 54848484");
        customer.setStreet("Stratße 1");
        customer.setZipcode("84879");
        try {
            customerDto.insertCustomer(customer);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDtoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(true);
    }

    /**
     * Test of selectSingleCustomer method, of class CustomerDto.
     */
    @Test
    public void selectSingleCustomer() {
        CustomerDao customer = null;
        try {
            customer = customerDto.selectSingleCustomer(1);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDtoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals("Wilhelm", customer.getName());
    }
}
