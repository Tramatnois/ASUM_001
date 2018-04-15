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
public class CharacteristicOperationDAO extends AbstractDAO {


    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<CharacteristicOperationDTO> selectAll() throws SQLException {
        ArrayList<CharacteristicOperationDTO> characteristicOperationList = new ArrayList<>();
        String query;
        ResultSet rs;
        query = "SELECT charop.idcharacteristic_operation AS charop_idcharacteristic_operation, charop.description AS charop_description, charop.position AS charop_position, charop.inspectionplan_operation_id AS charop_inspectionplan_operation_id, charop.characteristic_group_operation_id AS charop_characteristic_group_operation_id, "
                + "ctype.idcharacteristic_type AS ctype_idcharacteristic_type, ctype.description AS ctype_description " 
                + "FROM characteristic_operation_tab AS charop "
                + "JOIN characteristic_type_tab AS ctype ON charop.characteristic_type_id = ctype.idcharacteristic_type ";

        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            characteristicOperationList.add(this.mapCharacteristicGroupOperation(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return characteristicOperationList;
    }

    /**
     * Returns a list of CharacteristicOperation Items ordered by the
     * inspectionPlanOperation id. The given inspectionPlanOperation is mapped
     * to the items.
     *
     * @param id
     * @return ArrayList of CharacteristicGroup selected by Operation Plan ID
     * @throws SQLException
     */
    public ArrayList<CharacteristicOperationDTO> selectAllByInspectionPlanOperation(InspectionPlanOperationDTO inspectionPlanOperationDTO) throws SQLException {
        ArrayList<CharacteristicOperationDTO> characteristicOperationList = new ArrayList<>();
        CharacteristicOperationDTO characteristicOperationDTO;
        String query;
        ResultSet rs;
        query = "SELECT * FROM characteristic_operation_tab "
                + "WHERE inspectionplan_operation_id=?";

        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, inspectionPlanOperationDTO.getId());

        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            characteristicOperationDTO = this.mapCharacteristicGroupOperation(rs);
            characteristicOperationDTO.setInspectionPlan(inspectionPlanOperationDTO);
            characteristicOperationList.add(characteristicOperationDTO);
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return characteristicOperationList;
    }

    /**
     * Returns a single characteristicGroup ordered by ID
     *
     * @param id = ID of the characteristicGroupDTO
     * @return returns a single characteristicGroupDTO which is ordered by ID
     * @throws SQLException
     */
    public CharacteristicOperationDTO selectSingleById(int id) throws SQLException {
        CharacteristicOperationDTO characteristicOperationDTO;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        //query = "SELECT * FROM inspectionplan_operation_tab where idinspectionplan_operation=?";
        query = "SELECT * FROM characteristic_operation_tab "
                + "WHERE idcharacteristic_group_operation=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            characteristicOperationDTO = this.mapCharacteristicGroupOperation(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return characteristicOperationDTO;
    }

    /**
     * Adds a characteristicOperation to the Database.
     *
     * @param characteristicOperation
     * @throws SQLException
     * @return id of inserted characteristicOperation or -1 = Error
     */
    public int insert(CharacteristicOperationDTO characteristicOperationDTO) throws SQLException {
        ResultSet rs;
        int generatedKey = -1;
        String query = " insert into characteristic_operation_tab (idcharacteristic_group_operation, groupnumber, description, inspectionplan_operation_id)"
                + " values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setInt(1, characteristicOperationDTO.getId());
        preparedStmt.setInt(2, characteristicOperationDTO.getCharacteristicgroup().getGroupnumber());
        preparedStmt.setString(3, characteristicOperationDTO.getDescription());
        preparedStmt.setInt(4, characteristicOperationDTO.getInspectionPlan().getId());

        // execute the preparedstatement
        preparedStmt.execute();
        rs = preparedStmt.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }

    /**
     * Updates a CharacteristicOperationDTO in the database
     *
     * @param CharacteristicOperationDTO who is updated. All fields of the
     * object are updated.
     * @throws SQLException
     */
    public void update(CharacteristicOperationDTO characteristicOperationDTO) throws SQLException {

        String query = "Update characteristic_operation_tab SET groupnumber=?, description=?, description=?  WHERE idcharacteristic_group_operation=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, characteristicOperationDTO.getCharacteristicgroup().getGroupnumber());
        preparedStmt.setString(2, characteristicOperationDTO.getDescription());
        preparedStmt.setInt(3, characteristicOperationDTO.getInspectionPlan().getId());

        preparedStmt.setInt(4, characteristicOperationDTO.getId());

        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }

    /**
     * Deletes a CharacteristicOperationDTO from Database It is enough if the
     * CharacteristicOperationDTO object contains an id
     *
     * @param CharacteristicOperationDTO CharacteristicOperationDTO who should
     * be deleted
     * @throws SQLException
     */
    public void delete(CharacteristicOperationDTO characteristicOperationDTO) throws SQLException {

        String query = "DELETE FROM characteristic_operation_tab WHERE idcharacteristic_group_operation=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, characteristicOperationDTO.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }

    /**
     * Maps a resutlSet to a InspectionPlanOperationDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the
     * CharacteristicOperationDTO who should be mapped
     * @return mapped CharacteristicOperationDTO object
     * @throws SQLException
     */
    private CharacteristicOperationDTO mapCharacteristicGroupOperation(ResultSet rs) throws SQLException {
        CharacteristicOperationDTO characteristicOperationDTO = new CharacteristicOperationDTO();
        InspectionPlanOperationDTO inspectionPlanOperationDTO = new InspectionPlanOperationDTO();
        CharacteristicTypeDTO characteristicTypeDTO = new CharacteristicTypeDTO();
        CharacteristicGroupOperationDTO characteristicGroupOperationDTO = new CharacteristicGroupOperationDTO();
        
        characteristicOperationDTO.setId(rs.getInt("charop_idcharacteristic_operation"));
        characteristicOperationDTO.setDescription(rs.getString("charop_description"));
        characteristicOperationDTO.setPosition(rs.getInt("charop_position"));
        //inspection Plan
        inspectionPlanOperationDTO.setId(rs.getInt("charop_inspectionplan_operation_id"));
        characteristicOperationDTO.setInspectionPlan(inspectionPlanOperationDTO);
        //Characteristic Type
        characteristicTypeDTO.setId(rs.getInt("ctype_idcharacteristic_type"));
        characteristicTypeDTO.setDescription("ctype_description");
        characteristicOperationDTO.setCharacteristicType(characteristicTypeDTO);
        //CHaracteristic Group
        characteristicGroupOperationDTO.setId(rs.getInt("charop_characteristic_group_operation_id"));
        
        
        
        
        
        return characteristicOperationDTO;
    }

/**
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

}**/
/**
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
    }**/
}
