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
 * @author LT Dan
 */
public class InspectionOperationCommentDAO extends AbstractDAO {

    public InspectionOperationCommentDTO selectSingleById(int id) throws SQLException {
        InspectionOperationCommentDTO inspComment;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM inspection_operation_comment_tab where idinspection_comment=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            inspComment = this.mapInspComment(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return inspComment;
    }

    private InspectionOperationCommentDTO mapInspComment(ResultSet rs) throws SQLException {
        InspectionOperationCommentDTO inspComment = new InspectionOperationCommentDTO();
        CharacteristicOperationDTO charOperationDTO = new CharacteristicOperationDTO();

        inspComment.setId(rs.getInt("idinspection_comment"));
        inspComment.setComment(rs.getString("comment"));

        CharacteristicOperationDAO charOperationDAO = new CharacteristicOperationDAO();
        charOperationDTO = charOperationDAO.selectSingleById(rs.getInt("characteristic_operation_id"));
        inspComment.setCharacteristic(charOperationDTO);

        return inspComment;
    }

}
