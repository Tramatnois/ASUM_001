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
public class InspectorDAO extends AbstractDAO{


      //DBConnection connection = new DBConnection();

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
     * returns a List of all Inspector in the database
     *
     * @return Array List with all inspector
     */
    
    public ArrayList<InspectorDTO> selectAllInspector() throws SQLException {
        ArrayList<InspectorDTO> inspectorList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM inspector_tab";
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery("SELECT * FROM inspector_tab");
        while (rs.next()) {
            inspectorList.add(this.mapInspector(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return inspectorList;
    }

    /**
     * Adds a inspector to the Database. Id of the inspector object is ignored
     *
     * @param inspector
     * @throws SQLException
     * @return id of inserted Inspector or -1 = Error
     */
    public int insertInspector(InspectorDTO inspector) throws SQLException {
        ResultSet rs;
        int generatedKey =-1;
        String query = " insert into inspector_tab (name, street, zipcode, city)"
                + " values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, inspector.getName());
        preparedStmt.setString(2, inspector.getStreet());
        preparedStmt.setString(3, inspector.getZipcode());
        preparedStmt.setString(4, inspector.getCity());
        // execute the preparedstatement
        preparedStmt.execute();
        rs = preparedStmt.getGeneratedKeys();
        if (rs.next())
            generatedKey = rs.getInt(1);
        return generatedKey;
    }

    /**
     * Updates a inspector in the database
     *
     * @param inspector inspector who is updated. All fields of the object are
     * updated.
     * @throws SQLException
     */
    
    public void updateInspector(InspectorDTO inspector) throws SQLException {

        String query = "Update inspector_tab SET name=?, street=?, zipcode=?, city=? WHERE idinspector=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setString(1, inspector.getName());
        preparedStmt.setString(2, inspector.getStreet());
        preparedStmt.setString(3, inspector.getZipcode());
        preparedStmt.setString(4, inspector.getCity());
        preparedStmt.setInt(5, inspector.getId());
        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }

    /**
     * Deletes a inspector from Database It is enough if the inspector object
     * contains an id
     *
     * @param inspector inspector who should be deleted
     * @throws SQLException
     */
    public void deleteInspector(InspectorDTO inspector) throws SQLException {

        String query = "DELETE FROM inspector_tab WHERE idinspector=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, inspector.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }

    /**
     * Maps a resutlSet to a InspectorDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the inspector who should
     * be mapped
     * @return mapped InspectorDTO object
     * @throws SQLException
     */
    private InspectorDTO mapInspector(ResultSet rs) throws SQLException {
        InspectorDTO inspector;
        inspector = new InspectorDTO();
        inspector.setId(rs.getInt("idinspector"));
        inspector.setName(rs.getString("name"));
        inspector.setStreet(rs.getString("street"));
        inspector.setZipcode(rs.getString("zipcode"));
        inspector.setCity(rs.getString("city"));
        return inspector;
    }

}
