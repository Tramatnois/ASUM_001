/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

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
     * Test of selectSingleCustomer method, of class CustomerDto.
     */
    @Test
    public void selectSingleCustomer(){
        CustomerDao customer;
        customer = customerDto.selectSingleCustomer(1);
        assertEquals("Daniel GmbH", customer.getName());
    }
    
}
