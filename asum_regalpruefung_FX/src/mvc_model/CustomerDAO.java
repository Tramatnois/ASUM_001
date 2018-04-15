/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mvc_model_sqlconnector.DBConnection;

/**
 *
 * @author LT Dan
 */
public class CustomerDAO extends AbstractDAO{


    /**
     * Returns a single customer ordered by ID
     *
     * @param idcustomer = ID of the customer
     * @return returns a single customer which is ordered by ID
     */

    public CustomerDTO selectSingleCustomer(int idcustomer) throws SQLException {
        CustomerDTO customer = new CustomerDTO();
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM customer_tab where idcustomer = ?";

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
     * returns a List of all Customer in the database
     *
     * @return Array List with all customer
     */
    public ArrayList<CustomerDTO> selectAllCustomer() throws SQLException {
        ArrayList<CustomerDTO> customerList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM customer_tab where active = 1";
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery(query);
        while (rs.next()) {
            customerList.add(this.mapCustomer(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return customerList;
    }

    /**
     * Adds a customer to the Database. Id of the customer object is ignored
     *
     * @param customer
     * @throws SQLException
     * @ return generated key of inserted customer
     */
    public int insertCustomer(CustomerDTO customer) throws SQLException {
        ResultSet rs;
        int generatedKey =-1;

        String query = " insert into customer_tab (name, street, zipcode, city, contactperson, phone, fax, email, active)"
                + " values (?, ?, ?, ?, ?, ? , ? , ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, customer.getName());
        preparedStmt.setString(2, customer.getStreet());
        preparedStmt.setString(3, customer.getZipcode());
        preparedStmt.setString(4, customer.getCity());
        preparedStmt.setString(5, customer.getContactperson());
        preparedStmt.setString(6, customer.getPhone());
        preparedStmt.setString(7, customer.getFax());
        preparedStmt.setString(8, customer.getEmail());
        preparedStmt.setInt(9, customer.getActive());

        // execute the preparedstatement
        preparedStmt.execute();
        rs = preparedStmt.getGeneratedKeys();
        if (rs.next())
            generatedKey = rs.getInt(1);
        preparedStmt.close();
        return generatedKey;
    }
    /**
     * Updates a customer in the database
     * 
     * @param customer customer who is updated. All fields of the object
     * @throws SQLException 
     */
    public void updateCustomer(CustomerDTO customer) throws SQLException {

        String query = "Update customer_tab SET name=?, street=?, zipcode=?, city=?, contactperson=?, phone=?, fax=?, email=?, active=? WHERE idcustomer=?";

        // create the mysql update preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setString(1, customer.getName());
        preparedStmt.setString(2, customer.getStreet());
        preparedStmt.setString(3, customer.getZipcode());
        preparedStmt.setString(4, customer.getCity());
        preparedStmt.setString(5, customer.getContactperson());
        preparedStmt.setString(6, customer.getPhone());
        preparedStmt.setString(7, customer.getFax());
        preparedStmt.setString(8, customer.getEmail());
        preparedStmt.setInt(9, customer.getActive());
        preparedStmt.setInt(10, customer.getId());
        // execute the preparedstatement
        preparedStmt.execute();
        preparedStmt.close();

    }
    /**
     * Deletes a customer from Database
     * It is enough if the customer object contains an id
     * @param customer customer who should be deleted
     * @throws SQLException 
     */
    public void deleteCustomer(CustomerDTO customer) throws SQLException {

        String query = "DELETE FROM customer_tab WHERE idcustomer=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, customer.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }
    /**
     * Maps a resutlSet to a CustomerDTO2 Object
     * 
     * 
     * @param rs ResultSet. The Pointer must be set on the customer who should be mapped
     * @return mapped CustomerDTO2 object
     * @throws SQLException 
     */
    private CustomerDTO mapCustomer(ResultSet rs) throws SQLException {
        CustomerDTO customer;
        customer = new CustomerDTO();
        customer.setId(rs.getInt("idcustomer"));
        customer.setName(rs.getString("name"));
        customer.setStreet(rs.getString("street"));
        customer.setZipcode(rs.getString("zipcode"));
        customer.setCity(rs.getString("city"));
        customer.setContactperson(rs.getString("contactperson"));
        customer.setPhone(rs.getString("phone"));
        customer.setFax(rs.getString("fax"));
        customer.setEmail(rs.getString("email"));
        customer.setActive(rs.getInt("active"));
        return customer;
    }

}













