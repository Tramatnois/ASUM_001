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
public class CharacteristicGroupTemplateDAO extends AbstractDAO{
        
    /**
     * returns a List of all Customer in the database
     *
     * @return Array List with all customer
     */
    public ArrayList<CharacteristicGroupTemplateDTO> selectAllCharGroupsTemplates() throws SQLException {
        ArrayList<CharacteristicGroupTemplateDTO> charGroupTemplateList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM characteristic_group_template_tab where active = 1";
//            query = "SELECT ipo.idinspectionplan_operation, ipo.date, ipo.norm, ipo.description, ipo.storage_Rack, ipo.location, ipo.customer_id, ipo.inspector_id, ipo.inspectionplan_template_id,"
//                + " ipos.idinspectionplan_operation_status, ipos.description AS iposDescription "
//                + "FROM inspectionplan_operation_tab ipo JOIN inspectionplan_operation_status_tab ipos ON ipo.inspectionplan_operation_status_id  = ipos.idinspectionplan_operation_status "
//                ;

        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            charGroupTemplateList.add(this.mapCharGroupTemplate(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return charGroupTemplateList;
    }
    
    //DBConnection connection = new DBConnection();
    /**
     * Returns a single inspector ordered by ID
     *
     * @param id = ID of the InspectionPlanOperationDTO
     * @return returns a single InspectionPlanOperationDTO which is ordered by
     * ID
     * @throws SQLException
     */
    public CharacteristicGroupTemplateDTO selectSingleById(int id) throws SQLException {
        CharacteristicGroupTemplateDTO charGroupTemplateDTO;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        //query = "SELECT * FROM inspectionplan_operation_tab where idinspectionplan_operation=?";
        query = "SELECT ipo.idinspectionplan_operation, ipo.date, ipo.norm, ipo.description, ipo.storage_Rack, ipo.location, ipo.customer_id, ipo.inspector_id, ipo.inspectionplan_template_id, ipo.inspectionplan_operation_status_id, "
                + " ipos.idinspectionplan_operation_status, ipos.description AS iposDescription "
                + "FROM inspectionplan_operation_tab ipo JOIN inspectionplan_operation_status_tab ipos ON ipo.inspectionplan_operation_status_id  = ipos.idinspectionplan_operation_status "
                + "where idinspectionplan_operation=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            charGroupTemplateDTO = this.mapCharGroupTemplate(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return charGroupTemplateDTO;
    }

    /**
     * Adds a inspector to the Database. Id of the inspector object is ignored
     *
     * @param charGroupTemplateDTO
     * @throws SQLException
     * @return id of inserted Inspector or -1 = Error
     */
    public int insert(CharacteristicGroupTemplateDTO charGroupTemplateDTO) throws SQLException {
        ResultSet rs;
        int generatedKey = -1;
        String query = " insert into characteristic_group_template_tab ( groupnumber, description, inspectionplan_template_id, active)"
                + " values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setInt(1, charGroupTemplateDTO.getGroupNumber());
        preparedStmt.setString(2, charGroupTemplateDTO.getDescription());
        preparedStmt.setInt(3, charGroupTemplateDTO.getInspectionPlanDTO().getId());
        preparedStmt.setInt(4, charGroupTemplateDTO.getActive());
        // execute the preparedstatement
        preparedStmt.execute();
        rs = preparedStmt.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }
    
    /**
     * Updates a inspector in the database
     *
     * @param inspector inspector who is updated. All fields of the object are
     * updated.
     * @throws SQLException
     */
    public void update(CharacteristicGroupTemplateDTO charGroupTemplateDTO) throws SQLException {

        String query = "Update characteristic_group_template_tab SET groupnumber=?, description=?, inspectionplan_template_id=?, active=? WHERE idcharacteristic_group_template=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, charGroupTemplateDTO.getGroupNumber());
        preparedStmt.setString(2, charGroupTemplateDTO.getDescription());
        preparedStmt.setInt(3, charGroupTemplateDTO.getInspectionPlanDTO().getId());
        preparedStmt.setInt(4, charGroupTemplateDTO.getActive());
        preparedStmt.setInt(5, charGroupTemplateDTO.getId());
        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }
    
    /**
     * Deletes a inspector from Database It is enough if the inspector object
     * contains an id
     *
     * @param charGroupTemplateDTO inspector who should be deleted
     * @throws SQLException
     */
    public void delete(CharacteristicGroupTemplateDTO charGroupTemplateDTO) throws SQLException {

        String query = "DELETE FROM characteristic_group_template_tab WHERE idcharacteristic_group_template=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, charGroupTemplateDTO.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }
    
    private CharacteristicGroupTemplateDTO mapCharGroupTemplate(ResultSet rs) throws SQLException {
        CharacteristicGroupTemplateDTO charGroupTemplate;
        charGroupTemplate = new CharacteristicGroupTemplateDTO();

        charGroupTemplate.setId(rs.getInt("idcharacteristic_group_template"));
        charGroupTemplate.setGroupNumber(rs.getInt("groupnumber"));
        charGroupTemplate.setDescription(rs.getString("description"));
        charGroupTemplate.setInspectionPlanDTO(new InspectionPlanTemplateDAO().selectSingleById(rs.getInt("inspectionplan_template_id")));
        charGroupTemplate.setActive(rs.getInt("active"));

        return charGroupTemplate;
    }

    
    
    
    
}
