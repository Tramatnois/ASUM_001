/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author danie
 */
public class CharacteristicOperationDAO extends AbstractDAO {

    public CharacteristicOperationDTO selectSingleById(int id) throws SQLException {
        CharacteristicOperationDTO charOperation_DTO;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM characteristic_operation_tab where idcharacteristic_operation=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            charOperation_DTO = this.mapCharacteristicOperation(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return charOperation_DTO;
    }

    private CharacteristicOperationDTO mapCharacteristicOperation(ResultSet rs) throws SQLException {
        CharacteristicOperationDTO characteristicOperation = new CharacteristicOperationDTO();

        InspectionPlanOperationDTO inspectionPlanDTO;
        CharacteristicTypeDTO characteristicTypeDTO;
        CharacteristicGroupOperationDTO characteristicgroupDTO;
//        InspectionResultDTO resultDTO;
//        InspectionOperationCommentDTO commentDTO;

        characteristicOperation.setId(rs.getInt("idcharacteristic_operation"));
        characteristicOperation.setDescription(rs.getString("description"));
        characteristicOperation.setPosition(rs.getInt("position"));

        InspectionPlanOperationDAO inspPlanDAO = new InspectionPlanOperationDAO();
        inspectionPlanDTO = inspPlanDAO.selectSingleById(rs.getInt("inspectionplan_operation_id"));
        characteristicOperation.setInspectionPlan(inspectionPlanDTO);

        CharacteristicTypeDAO characteristicTypeDAO = new CharacteristicTypeDAO();
        characteristicTypeDTO = characteristicTypeDAO.selectSingleCharacteristicType(rs.getInt("characteristic_type_id"));
        characteristicOperation.setCharacteristicType(characteristicTypeDTO);

        CharacteristicGroupOperationDAO characteristicgroupDAO = new CharacteristicGroupOperationDAO();
        characteristicgroupDTO = characteristicgroupDAO.selectSingleById(rs.getInt("characteristic_group_operation_id"));
        characteristicOperation.setCharacteristicgroup(characteristicgroupDTO);

//        InspectionResultDAO resultDAO = new InspectionResultDAO();
//        resultDTO = resultDAO.selectSingleById(rs.getInt("characteristic_group_operation_id"));
//        InspectionOperationCommentDAO commentDAO = new InspectionOperationCommentDAO();
//        commentDTO = commentDAO.selectSingleById(rs.getInt("characteristic_type_id"));
        return characteristicOperation;
    }
}
