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

/**
 *
 * @author LT Dan
 */
public class InspectionResultDAO extends AbstractDAO{
    
/**
     * returns a List of all inspection results in the database
     *
     * @return Array List with all inspection results
     */
    public ArrayList<InspectionResultDTO> selectAllInspResults() throws SQLException {
        ArrayList<InspectionResultDTO> inspResultList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM inspection_result_tab";
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery("SELECT * FROM inspection_result_tab");
        while (rs.next()) {
            inspResultList.add(this.mapInspResult(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return inspResultList;
    }    
/**
     * Maps a resutlSet to a CustomerDTO2 Object
     * 
     * 
     * @param rs ResultSet. The Pointer must be set on the customer who should be mapped
     * @return mapped CustomerDTO2 object
     * @throws SQLException 
     */
    private InspectionResultDTO mapInspResult(ResultSet rs) throws SQLException {
        InspectionPlanOperationDTO inspOperation = new InspectionPlanOperationDTO();
        CharacteristicOperationDTO charOperation = new CharacteristicOperationDTO();
        InspectionOperationCommentDTO inspOpComment = new InspectionOperationCommentDTO();
        
        InspectionResultDTO inspResult;
        inspResult = new InspectionResultDTO();
        inspResult.setId(rs.getInt("idinspection_result"));
        inspResult.setResult(rs.getString("result"));
        inspOperation.setId(rs.getInt("inspection_plan_operation_id"));
        inspResult.setInspectionOperation(inspOperation);
        charOperation.setId(rs.getInt("characteristic_operation_id"));
        inspResult.setCharacteristic(charOperation);
        return inspResult;
    }    
    
}
