/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mvc.model.sqlconnector.DBConnection;

/**
 *
 * @author LT Dan
 */
public class CustomerDto {

    DBConnection connection = new DBConnection();
    /*
     * Returns a single customer ordered by ID
     */

    public CustomerDao selectSingleCustomer(int idcustomer) throws SQLException {
        CustomerDao customer = new CustomerDao();
        Statement stmt;
        ResultSet rs;
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM customer_tab where idcustomer=" + idcustomer);
            if (rs.next()) {
                customer = this.mapCustomer(rs);
            } else {
                return null;
            }
            rs.close();
            stmt.close();

        return customer;
    }

    /**
     * returns a List of all Customer
     */
    public ArrayList<CustomerDao> selectAllCustomer() throws SQLException {
        ArrayList<CustomerDao> customerList = new ArrayList<>();
                Statement stmt;
        ResultSet rs;
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM customer_tab");
            while (rs.next())
                customerList.add(this.mapCustomer(rs));
            rs.close();
            stmt.close();
        return customerList;
    }
    public void insertCustomer(CustomerDao customer) throws SQLException{
        
        
        String query = " insert into customer_tab (name, street, zipcode, city, contactperson, phone, fax, email)"
        + " values (?, ?, ?, ?, ?, ? , ? , ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt =  connection.getConnection().prepareStatement(query);
      preparedStmt.setString (1, customer.getName());
      preparedStmt.setString (2, customer.getStreet());
      preparedStmt.setString (3, customer.getZipcode());
      preparedStmt.setString (4, customer.getCity());
      preparedStmt.setString (5, customer.getContactperson());
      preparedStmt.setString (6, customer.getPhone());
      preparedStmt.setString (7, customer.getFax());
      preparedStmt.setString (8, customer.getEmail());

      // execute the preparedstatement
      preparedStmt.execute();
        
    }

    private CustomerDao mapCustomer(ResultSet rs) throws SQLException {
        CustomerDao customer;
        customer = new CustomerDao();
        customer.setIdcustomer(rs.getInt("idcustomer"));
        customer.setName(rs.getString("name"));
        customer.setStreet("street");
        customer.setZipcode("zipcode");
        customer.setCity(rs.getString("city"));
        customer.setContactperson(rs.getString("contactperson"));
        customer.setPhone("phone");
        customer.setFax(rs.getString("fax"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }

}
