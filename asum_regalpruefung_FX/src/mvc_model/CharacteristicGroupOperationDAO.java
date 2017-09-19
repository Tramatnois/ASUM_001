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
 * @author danie
 */
public class CharacteristicGroupOperationDAO extends AbstractDAO{
    
    
     public ArrayList<CharacteristicGroupOperationDTO> selectAll() throws SQLException {
        ArrayList<CharacteristicGroupOperationDTO> characteristicGroupOperationList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM characteristic_group_operation_tab ";


        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            characteristicGroupOperationList.add(this.mapCharacteristicGroupOperation(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return characteristicGroupOperationList;
    }
   
     
     
     /**
      * Returns a list of CharacteristicGroupOperation Items ordered by the inspectionPlanOperation id.
      * The given inspectionPlanOperation is mapped to the items.
      * 
      * @param id
      * @return ArrayList of CharacteristicGroup selected by Operation Plan ID
      * @throws SQLException 
      */
     public ArrayList<CharacteristicGroupOperationDTO> selectAllByInspectionPlanOperation(int inspectionPlanOperationId) throws SQLException {
        ArrayList<CharacteristicGroupOperationDTO> characteristicGroupOperationList = new ArrayList<>();
        CharacteristicGroupOperationDTO characteristicGroupOperationDTO;
        String query;
        ResultSet rs;
        query = "SELECT * FROM characteristic_group_operation_tab "
                + "WHERE inspectionplan_operation_id=?";

        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, inspectionPlanOperationId);

        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            characteristicGroupOperationDTO = this.mapCharacteristicGroupOperation(rs);
            characteristicGroupOperationList.add(characteristicGroupOperationDTO);
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return characteristicGroupOperationList;
    }
    /**
     * Returns a single characteristicGroup ordered by ID
     *
     * @param id = ID of the characteristicGroupDTO
     * @return returns a single characteristicGroupDTO which is ordered by
     * ID
     * @throws SQLException
     */
    public CharacteristicGroupOperationDTO selectSingleById(int id) throws SQLException {
        CharacteristicGroupOperationDTO characteristicGroupOperationDTO;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        //query = "SELECT * FROM inspectionplan_operation_tab where idinspectionplan_operation=?";
        query = "SELECT * FROM characteristic_group_operation_tab "
                + "WHERE idcharacteristic_group_operation=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            characteristicGroupOperationDTO = this.mapCharacteristicGroupOperation(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return characteristicGroupOperationDTO;
    }
    /**
     * Adds a characteristicGroupOperation to the Database.
     *
     * @param characteristicGroupOperation
     * @throws SQLException
     * @return id of inserted characteristicGroupOperation or -1 = Error
     */
    public int insert(CharacteristicGroupOperationDTO characteristicGroupOperationDTO) throws SQLException {
        ResultSet rs;
        int generatedKey = -1;
        String query = " insert into characteristic_group_operation_tab (idcharacteristic_group_operation, groupnumber, description, inspectionplan_operation_id)"
                + " values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setInt(1, characteristicGroupOperationDTO.getId());
        preparedStmt.setInt(2, characteristicGroupOperationDTO.getGroupnumber());
        preparedStmt.setString(3, characteristicGroupOperationDTO.getDescription());
        preparedStmt.setInt(4, characteristicGroupOperationDTO.getInspectionPlanDTO().getId());

        // execute the preparedstatement
        preparedStmt.execute();
        rs = preparedStmt.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }

    /**
     * Updates a CharacteristicGroupOperationDTO in the database
     *
     * @param CharacteristicGroupOperationDTO who is updated. All fields of the object are
     * updated.
     * @throws SQLException
     */
    public void update(CharacteristicGroupOperationDTO characteristicGroupOperationDTO) throws SQLException {

        String query = "Update characteristic_group_operation_tab SET groupnumber=?, description=?, description=?  WHERE idcharacteristic_group_operation=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, characteristicGroupOperationDTO.getGroupnumber());
        preparedStmt.setString(2, characteristicGroupOperationDTO.getDescription());
        preparedStmt.setInt(3, characteristicGroupOperationDTO.getInspectionPlanDTO().getId());
        
        preparedStmt.setInt(4, characteristicGroupOperationDTO.getId());

        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }

    /**
     * Deletes a CharacteristicGroupOperationDTO from Database It is enough if the CharacteristicGroupOperationDTO object
     * contains an id
     *
     * @param CharacteristicGroupOperationDTO CharacteristicGroupOperationDTO who should be deleted
     * @throws SQLException
     */
    public void delete(CharacteristicGroupOperationDTO characteristicGroupOperationDTO) throws SQLException {

        String query = "DELETE FROM characteristic_group_operation_tab WHERE idcharacteristic_group_operation=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, characteristicGroupOperationDTO.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }

    /**
     * Maps a resutlSet to a InspectionPlanOperationDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the
     * CharacteristicGroupOperationDTO who should be mapped
     * @return mapped CharacteristicGroupOperationDTO object
     * @throws SQLException
     */
    private CharacteristicGroupOperationDTO mapCharacteristicGroupOperation(ResultSet rs) throws SQLException {
        CharacteristicGroupOperationDTO  characteristicGroupOperationDTO= new CharacteristicGroupOperationDTO();
        InspectionPlanOperationDTO inspectionPlanOperationDTO = new InspectionPlanOperationDTO();
        
        characteristicGroupOperationDTO.setId(rs.getInt("idcharacteristic_group_operation"));
        characteristicGroupOperationDTO.setDescription("description");
        characteristicGroupOperationDTO.setGroupnumber(rs.getInt("groupnumber"));
        inspectionPlanOperationDTO.setId(rs.getInt("inspectionplan_operation_id"));
        characteristicGroupOperationDTO.setInspectionPlanDTO(inspectionPlanOperationDTO);
       

        return characteristicGroupOperationDTO;
    }

}


