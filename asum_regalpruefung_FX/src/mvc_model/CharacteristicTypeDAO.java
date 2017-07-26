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
class CharacteristicTypeDAO extends AbstractDAO{
     
    /**
     * Returns a single characteristicType ordered by ID
     *
     * @param idcharacteristicType = ID of the characteristicType
     * @return returns a single characteristicType which is ordered by ID
     * @throws SQLException
     */
    public CharacteristicTypeDTO selectSingleCharacteristicType(int id) throws SQLException {
        CharacteristicTypeDTO characteristicType;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM characteristic_type_tab where idcharacteristic_type=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            characteristicType = this.mapCharacteristicType(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return characteristicType;
    }

    /**
     * returns a List of all CharacteristicTemplate in the database
     *
     * @return Array List with all characteristicType
     */
    
    public ArrayList<CharacteristicTypeDTO> selectAllCharacteristicTypes() throws SQLException {
        ArrayList<CharacteristicTypeDTO> characteristicTypeList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM characteristic_type_tab";
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            characteristicTypeList.add(this.mapCharacteristicType(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return characteristicTypeList;
    }

    /**
     * Adds a characteristicType to the Database. Id of the characteristicType object is ignored
     *
     * @param characteristicType
     * @throws SQLException
     */
    /*
    public void insertCharacteristicTemplate(CharacteristicTypeDTO characteristicType) throws SQLException {

        String query = " insert into characteristic_template_tab (description, inspectionplan_template_id, characteristic_type_id, characteristic_group_template_id, position)"
                + " values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setString(1, characteristicType.getDescription());
        preparedStmt.setInt(2, characteristicType.getInspectionPlan().getId());
        preparedStmt.setInt(3, characteristicType.getCharacteristicType().getId());
        preparedStmt.setInt(4, characteristicType.getCharacteristicgroup().getId());
        preparedStmt.setInt(5, characteristicType.getPosition());
        // execute the preparedstatement
        preparedStmt.execute();
        preparedStmt.close();
    }*/

    /**
     * Updates a characteristicType in the database
     *
     * @param characteristicType characteristicType who is updated. All fields of the object are
     * updated.
     * @throws SQLException
     */
    /*
    public void updateCharacteristicTemplate(CharacteristicTypeDTO characteristicType) throws SQLException {

        String query = "Update characteristicType_tab SET description=? inspectionplan_template_id=? characteristic_type_id=? characteristic_group_template_id=? position=? WHERE idcharacteristic_template=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setString(1, characteristicType.getDescription());
        preparedStmt.setInt(2, characteristicType.getInspectionPlan().getId());
        preparedStmt.setInt(3, characteristicType.getCharacteristicType().getId());
        preparedStmt.setInt(4, characteristicType.getCharacteristicgroup().getId());
        preparedStmt.setInt(5, characteristicType.getPosition());
        preparedStmt.setInt(6, characteristicType.getId());
        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }*/

    /**
     * Deletes a characteristicType from Database It is enough if the characteristicType object
     * contains an id
     *
     * @param characteristicType characteristicType who should be deleted
     * @throws SQLException
     *//*
    public void deleteCharacteristicTemplate(CharacteristicTypeDTO characteristicType) throws SQLException {

        String query = "DELETE FROM characteristic_template_tab WHERE idcharacteristic_template=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, characteristicType.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }*/

    /**
     * Maps a resutlSet to a CharacteristicTypeDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the characteristicType who should
     * be mapped
     * @return mapped CharacteristicTypeDTO object
     * @throws SQLException
     */
    private CharacteristicTypeDTO mapCharacteristicType(ResultSet rs) throws SQLException {
        CharacteristicTypeDTO characteristicType;        
        characteristicType = new CharacteristicTypeDTO();
        characteristicType.setId(rs.getInt("idcharacteristic_type"));
        characteristicType.setDescription(rs.getString("description"));
     
        return characteristicType;
    }
}
