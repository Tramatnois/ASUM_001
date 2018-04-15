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
public class InspectionPlanTemplateDAO extends AbstractDAO {

    /**
     * returns a List of all Customer in the database
     *
     * @return Array List with all customer
     */
    public ArrayList<InspectionPlanTemplateDTO> selectAllInspectionPlanTemplates() throws SQLException {
        ArrayList<InspectionPlanTemplateDTO> inspectionPlanTemplateList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM inspectionplan_template_tab where active = 1";
//            query = "SELECT ipo.idinspectionplan_operation, ipo.date, ipo.norm, ipo.description, ipo.storage_Rack, ipo.location, ipo.customer_id, ipo.inspector_id, ipo.inspectionplan_template_id,"
//                + " ipos.idinspectionplan_operation_status, ipos.description AS iposDescription "
//                + "FROM inspectionplan_operation_tab ipo JOIN inspectionplan_operation_status_tab ipos ON ipo.inspectionplan_operation_status_id  = ipos.idinspectionplan_operation_status "
//                ;

        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            inspectionPlanTemplateList.add(this.mapInspectionPlanTemplate(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return inspectionPlanTemplateList;
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
    public InspectionPlanTemplateDTO selectSingleById(int id) throws SQLException {
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO;
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
            inspectionPlanTemplateDTO = this.mapInspectionPlanTemplate(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return inspectionPlanTemplateDTO;
    }

    /**
     * Adds a inspector to the Database. Id of the inspector object is ignored
     *
     * @param inspectionPlanTemplateDTO
     * @throws SQLException
     * @return id of inserted Inspector or -1 = Error
     */
    public int insert(InspectionPlanTemplateDTO inspectionPlanTemplateDTO) throws SQLException {
        ResultSet rs;
        int generatedKey = -1;
        String query = " insert into inspectionplan_template_tab ( norm, description, active)"
                + " values (?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, inspectionPlanTemplateDTO.getNorm());
        preparedStmt.setString(2, inspectionPlanTemplateDTO.getDescription());
        preparedStmt.setInt(3, inspectionPlanTemplateDTO.getActive());
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
    public void update(InspectionPlanTemplateDTO inspectionPlanTemplateDTO) throws SQLException {

        String query = "Update inspectionplan_template_tab SET norm=?, description=?, active=? WHERE idinspectionplan_template=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setString(1, inspectionPlanTemplateDTO.getNorm());
        preparedStmt.setString(2, inspectionPlanTemplateDTO.getDescription());
        preparedStmt.setInt(3, inspectionPlanTemplateDTO.getActive());
        preparedStmt.setInt(4, inspectionPlanTemplateDTO.getId());
        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }

    /**
     * Deletes a inspector from Database It is enough if the inspector object
     * contains an id
     *
     * @param inspectionPlanTemplateDTO inspector who should be deleted
     * @throws SQLException
     */
    public void delete(InspectionPlanTemplateDTO inspectionPlanTemplateDTO) throws SQLException {

        String query = "DELETE FROM inspectionplan_template_tab WHERE idinspectionplan_template=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, inspectionPlanTemplateDTO.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }

    /**
     * Maps a resutlSet to a InspectionPlanOperationDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the
     * inspectionPlanTemplate who should be mapped
     * @return mapped InspectionPlanOperationDTO2 object
     * @throws SQLException
     */
    private InspectionPlanTemplateDTO mapInspectionPlanTemplate(ResultSet rs) throws SQLException {
        InspectionPlanTemplateDTO inspectionPlanTemplate;
        inspectionPlanTemplate = new InspectionPlanTemplateDTO();

        inspectionPlanTemplate.setId(rs.getInt("idinspectionplan_template"));
        inspectionPlanTemplate.setNorm(rs.getString("norm"));
        inspectionPlanTemplate.setDescription(rs.getString("description"));
        inspectionPlanTemplate.setActive(rs.getInt("active"));

        return inspectionPlanTemplate;
    }

}
