/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mvc.model.sqlconnector.DBConnection;

/**
 *
 * @author LT Dan
 */
public class InspectorDAO {

    DBConnection connection = new DBConnection();

    /**
     * Returns a single inspector ordered by ID
     *
     * @param idinspector = ID of the inspector
     * @return returns a single inspector which is ordered by ID
     * @throws SQLException
     */
    public InspectorDTO selectSingleInspector(int idinspector) throws SQLException {
        InspectorDTO inspector;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM inspector_tab where idinspector=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, idinspector);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            inspector = this.mapInspector(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return inspector;
    }

    /**
     * returns a List of all Customer in the database
     *
     * @return Array List with all customer
     */
    /*
    public ArrayList<CustomerDTO> selectAllCustomer() throws SQLException {
        ArrayList<CustomerDTO> customerList = new ArrayList<>();
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
*/
    /**
     * Adds a customer to the Database. Id of the customer object is ignored
     *
     * @param customer
     * @throws SQLException
     *//*
    public void insertCustomer(CustomerDTO customer) throws SQLException {

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
*/
    /**
     * Updates a customer in the database
     *
     * @param customer customer who is updated. All fields of the object are
     * updated.
     * @throws SQLException
     */
    /*
    public void updateCustomer(CustomerDTO customer) throws SQLException {

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

    }*/

    /**
     * Deletes a customer from Database It is enough if the customer object
     * contains an id
     *
     * @param customer customer who should be deleted
     * @throws SQLException
     *//*
    public void deleteCustomer(CustomerDTO customer) throws SQLException {

        String query = "DELETE FROM customer_tab WHERE idcustomer=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, customer.getIdcustomer());
        // execute the preparedstatement
        preparedStmt.execute();

    }
*/
    /**
     * Maps a resutlSet to a CustomerDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the customer who should
     * be mapped
     * @return mapped CustomerDTO object
     * @throws SQLException
     */
    private InspectorDTO mapInspector(ResultSet rs) throws SQLException {
        InspectorDTO inspector;
        inspector = new InspectorDTO();
        inspector.setIdinspector(rs.getInt("idinspector"));
        inspector.setName(rs.getString("name"));
        inspector.setStreet(rs.getString("street"));
        inspector.setZipcode(rs.getString("zipcode"));
        inspector.setCity(rs.getString("city"));
        return inspector;
    }

}
