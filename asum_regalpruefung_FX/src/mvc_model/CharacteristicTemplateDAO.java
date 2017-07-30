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
public class CharacteristicTemplateDAO extends AbstractDAO{
        /**
     * Returns a single characteristicTemplate ordered by ID
     *
     * @param idcharacteristicTemplate = ID of the characteristicTemplate
     * @return returns a single characteristicTemplate which is ordered by ID
     * @throws SQLException
     */
    public CharacteristicTemplateDTO selectSingleCharacteristicTemplate(int id) throws SQLException {
        CharacteristicTemplateDTO characteristicTemplate;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        query = "SELECT * FROM characteristic_template_tab where idcharacteristic_template=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            characteristicTemplate = this.mapCharacteristicTemplate(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return characteristicTemplate;
    }

    /**
     * returns a List of all CharacteristicTemplate in the database
     *
     * @return Array List with all characteristicTemplate
     */
    
    public ArrayList<CharacteristicTemplateDTO> selectAllCharacteristicTemplate() throws SQLException {
        ArrayList<CharacteristicTemplateDTO> characteristicTemplateList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT * FROM characteristic_template_tab";
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            characteristicTemplateList.add(this.mapCharacteristicTemplate(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return characteristicTemplateList;
    }

    /**
     * Adds a characteristicTemplate to the Database. Id of the characteristicTemplate object is ignored
     *
     * @param characteristicTemplate
     * @throws SQLException
     * @return GeneratedKey of Insert statement
     */
    public int insertCharacteristicTemplate(CharacteristicTemplateDTO characteristicTemplate) throws SQLException {
        ResultSet rs;
        int generatedKey =-1;
        String query = " insert into characteristic_template_tab (description, inspectionplan_template_id, characteristic_type_id, characteristic_group_template_id, position)"
                + " values (?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, characteristicTemplate.getDescription());
        preparedStmt.setInt(2, characteristicTemplate.getInspectionPlan().getId());
        preparedStmt.setInt(3, characteristicTemplate.getCharacteristicType().getId());
        preparedStmt.setInt(4, characteristicTemplate.getCharacteristicgroup().getId());
        preparedStmt.setInt(5, characteristicTemplate.getPosition());
        // execute the preparedstatement
        preparedStmt.execute();
            rs = preparedStmt.getGeneratedKeys();
        if (rs.next())
            generatedKey = rs.getInt(1);
        preparedStmt.close();
        return generatedKey;
    }

    /**
     * Updates a characteristicTemplate in the database
     *
     * @param characteristicTemplate characteristicTemplate who is updated. All fields of the object are
     * updated.
     * @throws SQLException
     */
    
    public void updateCharacteristicTemplate(CharacteristicTemplateDTO characteristicTemplate) throws SQLException {

        String query = "Update characteristic_template_tab SET description=?, characteristic_group_template_id=?, characteristic_type_id=? , position=? WHERE idcharacteristic_template=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setString(1, characteristicTemplate.getDescription());
        preparedStmt.setInt(2, characteristicTemplate.getCharacteristicType().getId());
        preparedStmt.setInt(3, characteristicTemplate.getCharacteristicgroup().getId());
        preparedStmt.setInt(4, characteristicTemplate.getPosition());
        preparedStmt.setInt(5, characteristicTemplate.getId());
        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }

    /**
     * Deletes a characteristicTemplate from Database It is enough if the characteristicTemplate object
     * contains an id
     *
     * @param characteristicTemplate characteristicTemplate who should be deleted
     * @throws SQLException
     */
    public void deleteCharacteristicTemplate(CharacteristicTemplateDTO characteristicTemplate) throws SQLException {

        String query = "DELETE FROM characteristic_template_tab WHERE idcharacteristic_template=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, characteristicTemplate.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }

    /**
     * Maps a resutlSet to a CharacteristicTemplateDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the characteristicTemplate who should
     * be mapped
     * @return mapped CharacteristicTemplateDTO object including the full characteristic Type object
     * @throws SQLException
     */
    private CharacteristicTemplateDTO mapCharacteristicTemplate(ResultSet rs) throws SQLException {
        CharacteristicTemplateDTO characteristicTemplate;
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO = new InspectionPlanTemplateDTO();
 
        CharacteristicGroupTemplateDTO characteristicGroupTemplateDTO = new CharacteristicGroupTemplateDTO();
        
        characteristicTemplate = new CharacteristicTemplateDTO();
        characteristicTemplate.setId(rs.getInt("idcharacteristic_template"));
        characteristicTemplate.setDescription(rs.getString("description"));
        characteristicTemplate.setPosition(rs.getInt("position"));
        inspectionPlanTemplateDTO.setId(rs.getInt("inspectionplan_template_id"));
        characteristicTemplate.setInspectionPlan(inspectionPlanTemplateDTO);
        characteristicTemplate.setCharacteristicType(new CharacteristicTypeDAO().selectSingleCharacteristicType(rs.getInt("characteristic_type_id")));
        characteristicGroupTemplateDTO.setId(rs.getInt("characteristic_group_template_id"));
        characteristicTemplate.setCharacteristicgroup(characteristicGroupTemplateDTO);
     
        return characteristicTemplate;
    }
}
