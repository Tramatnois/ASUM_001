/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.sqlconnector;

import java.sql.ResultSet;
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
public class DBConnectionTest {
    DBConnection connection;
    
    public DBConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        connection = new DBConnection();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSelectStatement() throws Throwable{
        ResultSet rs = connection.selectStatement("customer_tab");
        rs.next();
        String name = rs.getString("name");
        assertEquals("Daniel GmbH", name);
    }
}
