/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mvc.model.sqlconnector.DBConnection;

/**
 *
 * @author LT Dan
 */
public class CustomerDto {
    DBConnection connection = new DBConnection();
    
    public CustomerDao selectSingleCustomer(int idcustomer){
        CustomerDao customer = new CustomerDao();
            Statement stmt;
            ResultSet rs;
        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM customer_tab where idcustomer=" + idcustomer );
            if (rs.next()) {
                customer.setIdcustomer(rs.getInt(idcustomer));
                customer.setName(rs.getString("name"));
                customer.setStreet("street");
                customer.setZipcode("zipcode");
                customer.setCity(rs.getString("city"));
                customer.setContactperson(rs.getString("contactperson"));
                customer.setPhone("phone");
                customer.setFax(rs.getString("fax"));
                customer.setEmail(rs.getString("email"));
            
            }
            else{
                return null;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
            
        return customer;
    }
    
}
