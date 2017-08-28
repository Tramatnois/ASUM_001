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
    public InspectionResultDTO selectSingleById(int id) throws SQLException {
        InspectionResultDTO inspResult;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM inspection_result_tab where idinspection_result=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            inspResult = this.mapInspResult(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return inspResult;
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
//        InspectionOperationCommentDTO inspOpComment = new InspectionOperationCommentDTO();
        
        InspectionResultDTO inspResult;
        inspResult = new InspectionResultDTO();
        inspResult.setId(rs.getInt("idinspection_result"));
        inspResult.setResult(rs.getString("result"));
        
//        inspOperation.setId(rs.getInt("inspection_plan_operation_id"));
        InspectionPlanOperationDAO inspPlanOp_DAO = new InspectionPlanOperationDAO();
        inspOperation = inspPlanOp_DAO.selectSingleById(rs.getInt("inspection_plan_operation_id"));
        inspResult.setInspectionOperation(inspOperation);
        
//        charOperation.setId(rs.getInt("characteristic_operation_id"));
        CharacteristicOperationDAO charOperation_DAO = new CharacteristicOperationDAO();
        charOperation = charOperation_DAO.selectSingleById(rs.getInt("characteristic_operation_id"));
        inspResult.setCharacteristicOperation(charOperation);
        
        return inspResult;
    }    
    
}
