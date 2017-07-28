/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mvc_model_sqlconnector.DBConnection;

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

    /**
     * Maps a resutlSet to a InspectionPlanOperationDTO2 Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the
     * inspectionPlanOperation who should be mapped
     * @return mapped InspectionPlanOperationDTO2 object
     * @throws SQLException
     */
    private InspectionPlanOperationDTO mapInspectionPlanOperation(ResultSet rs) throws SQLException {
        InspectionPlanOperationDTO inspectionPlanOperation;
        CustomerDTO customerDTO = new CustomerDTO();
        CustomerDAO customerDAO = new CustomerDAO();

        InspectorDTO inspector = new InspectorDTO();
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO = new InspectionPlanTemplateDTO();

        inspectionPlanOperation = new InspectionPlanOperationDTO();
        inspectionPlanOperation.setId(rs.getInt("idinspectionplan_operation"));
        inspectionPlanOperation.setDate(rs.getDate("date"));
        inspectionPlanOperation.setNorm("norm");
        inspectionPlanOperation.setDescription(rs.getString("description"));
        inspectionPlanOperation.setStoracgeRack(rs.getString("storage_Rack"));
        inspectionPlanOperation.setLocation(rs.getString("location"));
//        customerDTO.setId(rs.getInt("customer_id"));
//        customerDAO.selectSingleCustomer(customerDTO.getId());
        customerDTO = customerDAO.selectSingleCustomer(rs.getInt("customer_id"));
        inspectionPlanOperation.setCustomer(customerDTO);
        inspector.setId(rs.getInt("inspector_id"));
        inspectionPlanOperation.setInspector(inspector);
        inspectionPlanTemplateDTO.setId(rs.getInt("inspectionplan_template_id"));
        inspectionPlanOperation.setInspectionplan(inspectionPlanTemplateDTO);
        return inspectionPlanOperation;
    }

}
