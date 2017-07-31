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

/**
 *
 * @author LT Dan
 */
public class InspectionPlanOperationDAO extends AbstractDAO {

    /**
     * returns a List of all Customer in the database
     *
     * @return Array List with all customer
     */
    public ArrayList<InspectionPlanOperationDTO> selectAllInspectionPlanOperations() throws SQLException {
        ArrayList<InspectionPlanOperationDTO> inspectionPlanOperationList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM inspectionplan_operation_tab";
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery("SELECT * FROM inspectionplan_operation_tab");
        while (rs.next()) {
            inspectionPlanOperationList.add(this.mapInspectionPlanOperation(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return inspectionPlanOperationList;
    }

          //DBConnection connection = new DBConnection();

    
    
    /**
     * Returns a single inspector ordered by ID
     *
     * @param id = ID of the InspectionPlanOperationDTO
     * @return returns a single InspectionPlanOperationDTO which is ordered by ID
     * @throws SQLException
     */
    public InspectionPlanOperationDTO selectSingleById(int id) throws SQLException {
        InspectionPlanOperationDTO inspectionPlanOperationDTO;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM inspectionplan_operation_tab where idinspectionplan_operation=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            inspectionPlanOperationDTO = this.mapInspectionPlanOperation(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return inspectionPlanOperationDTO;
    }
    
    
    
    
    
    
    
    
    /**
     * Adds a inspector to the Database. Id of the inspector object is ignored
     *
     * @param inspectionPlanOperationDTO
     * @throws SQLException
     * @return id of inserted Inspector or -1 = Error
     */
    public int insert(InspectionPlanOperationDTO inspectionPlanOperationDTO) throws SQLException {
        ResultSet rs;
        int generatedKey =-1;
        String query = " insert into inspectionplan_operation_tab (date, norm, description, storage_Rack, location, customer_id, inspector_id, inspectionplan_template_id , inspectionplan_operation_status_id)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setDate(1, inspectionPlanOperationDTO.getDate());
        preparedStmt.setString(2, inspectionPlanOperationDTO.getNorm());
        preparedStmt.setString(3, inspectionPlanOperationDTO.getDescription());
        preparedStmt.setString(4, inspectionPlanOperationDTO.getStorageRack());
        preparedStmt.setString(5, inspectionPlanOperationDTO.getLocation());
        preparedStmt.setInt(6, inspectionPlanOperationDTO.getCustomer().getId());
        preparedStmt.setInt(7, inspectionPlanOperationDTO.getInspector().getId());
        preparedStmt.setInt(8, inspectionPlanOperationDTO.getInspectionplanTemplate().getId());
        preparedStmt.setInt(9, inspectionPlanOperationDTO.getInspectionPlanOperationStatus().getId());
        
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
    
    public void update(InspectionPlanOperationDTO inspectionPlanOperationDTO) throws SQLException {

        String query = "Update inspectionplan_operation_tab SET date=?, norm=?, description=?, storage_Rack=?, location=? WHERE idinspectionplan_operation=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setDate(1, inspectionPlanOperationDTO.getDate());
        preparedStmt.setString(2, inspectionPlanOperationDTO.getNorm());
        preparedStmt.setString(3, inspectionPlanOperationDTO.getDescription());
        preparedStmt.setString(4, inspectionPlanOperationDTO.getStorageRack());
        preparedStmt.setString(5, inspectionPlanOperationDTO.getLocation());
        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }

    /**
     * Deletes a inspector from Database It is enough if the inspector object
     * contains an id
     *
     * @param inspectionPlanOperationDTO inspector who should be deleted
     * @throws SQLException
     */
    public void delete(InspectionPlanOperationDTO inspectionPlanOperationDTO) throws SQLException {

        String query = "DELETE FROM inspectionplan_operation_tab WHERE idinspectionplan_operation=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, inspectionPlanOperationDTO.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }
    
   
    
    
    
    
    
    
    
    
    /**
     * Maps a resutlSet to a InspectionPlanOperationDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the
     * inspectionPlanOperation who should be mapped
     * @return mapped InspectionPlanOperationDTO2 object
     * @throws SQLException
     */
    private InspectionPlanOperationDTO mapInspectionPlanOperation(ResultSet rs) throws SQLException {
        InspectionPlanOperationDTO inspectionPlanOperation;
        
        //@ Stefan hier mal langsam: Die Kundendaten etc. stelle ich später bereit
        CustomerDTO customerDTO = new CustomerDTO();
        InspectorDTO inspector = new InspectorDTO();
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO = new InspectionPlanTemplateDTO();
        InspectionPlanOperationStatusDTO inspectionPlanOperationStatusDTO = new InspectionPlanOperationStatusDTO();
        
        
        inspectionPlanOperation = new InspectionPlanOperationDTO();
        inspectionPlanOperation.setId(rs.getInt("idinspectionplan_operation"));
        inspectionPlanOperation.setDate(rs.getDate("date"));
        inspectionPlanOperation.setNorm(rs.getString("norm"));
        inspectionPlanOperation.setDescription(rs.getString("description"));
        inspectionPlanOperation.setStorageRack(rs.getString("storage_Rack"));
        inspectionPlanOperation.setLocation(rs.getString("location"));
        
        customerDTO.setId(rs.getInt("customer_id"));
        inspectionPlanOperation.setCustomer(customerDTO);
        
        inspector.setId(rs.getInt("inspector_id"));
        inspectionPlanOperation.setInspector(inspector);
        
        inspectionPlanTemplateDTO.setId(rs.getInt("inspectionplan_template_id"));
        inspectionPlanOperation.setInspectionplanTemplate(inspectionPlanTemplateDTO);
        
        inspectionPlanOperationStatusDTO.setId(rs.getInt("inspectionplan_operation_status_id"));
        inspectionPlanOperation.setInspectionPlanOperationStatus(inspectionPlanOperationStatusDTO);
        
        return inspectionPlanOperation;
    }

}
