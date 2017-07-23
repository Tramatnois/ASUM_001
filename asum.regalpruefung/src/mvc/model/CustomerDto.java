/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.beans.Customizer;
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
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM customer_tab where idcustomer=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, idcustomer);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            customer = this.mapCustomer(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return customer;
    }

    /**
     * returns a List of all Customer
     */
    public ArrayList<CustomerDao> selectAllCustomer() throws SQLException {
        ArrayList<CustomerDao> customerList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM customer_tab";
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery("SELECT * FROM customer_tab");
        while (rs.next()) {
            customerList.add(this.mapCustomer(rs));
        }
        // execute the preparedstatement
        
        rs.close();
        preparedStmt.close();
        return customerList;
    }

    public void insertCustomer(CustomerDao customer) throws SQLException {

        String query = " insert into customer_tab (name, street, zipcode, city, contactperson, phone, fax, email)"
                + " values (?, ?, ?, ?, ?, ? , ? , ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setString(1, customer.getName());
        preparedStmt.setString(2, customer.getStreet());
        preparedStmt.setString(3, customer.getZipcode());
        preparedStmt.setString(4, customer.getCity());
        preparedStmt.setString(5, customer.getContactperson());
        preparedStmt.setString(6, customer.getPhone());
        preparedStmt.setString(7, customer.getFax());
        preparedStmt.setString(8, customer.getEmail());

        // execute the preparedstatement
        preparedStmt.execute();
        preparedStmt.close();
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

    public void updateCustomer(CustomerDao customer) throws SQLException {

        String query = "Update customer_tab SET name=?, street=?, zipcode=?, city=?, contactperson=?, phone=?, fax=?, email=? WHERE idcustomer=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setString(1, customer.getName());
        preparedStmt.setString(2, customer.getStreet());
        preparedStmt.setString(3, customer.getZipcode());
        preparedStmt.setString(4, customer.getCity());
        preparedStmt.setString(5, customer.getContactperson());
        preparedStmt.setString(6, customer.getPhone());
        preparedStmt.setString(7, customer.getFax());
        preparedStmt.setString(8, customer.getEmail());
        preparedStmt.setInt(9, customer.getIdcustomer());
        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }
        public void deleteCustomer(CustomerDao customer) throws SQLException {
  
        String query = "DELETE FROM customer_tab WHERE idcustomer=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, customer.getIdcustomer());
        // execute the preparedstatement
        preparedStmt.execute();

    }

}
