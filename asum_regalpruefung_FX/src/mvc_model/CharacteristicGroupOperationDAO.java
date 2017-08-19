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
        query = "SELECT * FROM characteristic_group_operation_tab";


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
     /*
    public ArrayList<InspectionPlanOperationDTO> selectAllFullLoadWithoutTemplate() throws SQLException {
        ArrayList<InspectionPlanOperationDTO> inspectionPlanOperationList = new ArrayList<>();
        String query;
        ResultSet rs;
        //query = "SELECT * FROM inspectionplan_operation_tab";
        query = "SELECT ipo.idinspectionplan_operation, ipo.date, ipo.norm, ipo.description, ipo.storage_Rack, ipo.location, ipo.inspectionplan_template_id, "
                + "ipos.idinspectionplan_operation_status as inspectionplan_operation_status_id, ipos.description AS description, "
                + "cus.idcustomer as customer_id, cus.name AS cusname, cus.street AS cusstreet, cus.zipcode AS cuszipcode, cus.city AS cuscity, cus.contactperson AS cuscontactperson, cus.phone AS cusphone, cus.fax AS cusfax, cus.email AS cusemail, "
                + "ins.idinspector as inspector_id, ins.name AS insname, ins.street AS insstreet, ins.zipcode AS inszipcode, ins.city AS inscity "
                + "FROM inspectionplan_operation_tab as ipo "
                + "JOIN inspectionplan_operation_status_tab as ipos ON ipo.inspectionplan_operation_status_id  = ipos.idinspectionplan_operation_status "
                + "JOIN customer_tab as cus ON ipo.customer_id=cus.idcustomer "
                + "JOIN inspector_tab as ins ON ipo.inspector_id=ins.idinspector ";

        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        rs = preparedStmt.executeQuery();
        while (rs.next()) {
            inspectionPlanOperationList.add(this.mapInspectionPlanOperation(rs));
        }
        // execute the preparedstatement

        rs.close();
        preparedStmt.close();
        return inspectionPlanOperationList;
    }
*/
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
                + "where idcharacteristic_group_operation=?";

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
/*
    public InspectionPlanOperationDTO selectSingleFullLoadByIdWithoutTemplate(int id) throws SQLException {
        InspectionPlanOperationDTO inspectionPlanOperationDTO;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        //query = "SELECT * FROM inspectionplan_operation_tab where idinspectionplan_operation=?";
        query = "SELECT ipo.idinspectionplan_operation, ipo.date, ipo.norm, ipo.description, ipo.storage_Rack, ipo.location, ipo.inspectionplan_template_id, "
                + "ipos.idinspectionplan_operation_status, ipos.description AS iposDescription, "
                + "cus.idcustomer, cus.name AS cusname, cus.street AS cusstreet, cus.zipcode AS cuszipcode, cus.city AS cuscity, cus.contactperson AS cuscontactperson, cus.phone AS cusphone, cus.fax AS cusfax, cus.email AS cusemail, "
                + "ins.idinspector, ins.name AS insname, ins.street AS insstreet, ins.zipcode AS inszipcode, ins.city AS inscity "
                + "FROM inspectionplan_operation_tab ipo "
                + "JOIN inspectionplan_operation_status_tab ipos ON ipo.inspectionplan_operation_status_id  = ipos.idinspectionplan_operation_status "
                + "JOIN customer_tab cus ON ipo.customer_id=cus.idcustomer "
                + "JOIN inspector_tab ins ON ipo.inspector_id=ins.idinspector "
                + "WHERE idinspectionplan_operation=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            inspectionPlanOperationDTO = this.mapFullInspectionPlanOperationWithoutTemplate(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return inspectionPlanOperationDTO;
    }
*/
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
/*
    private InspectionPlanOperationDTO mapFullInspectionPlanOperationWithoutTemplate(ResultSet rs) throws SQLException {
        InspectionPlanOperationDTO inspectionPlanOperation;

        //@ Stefan hier mal langsam: Die Kundendaten etc. stelle ich später bereit
        CustomerDTO customerDTO = new CustomerDTO();
        InspectorDTO inspector = new InspectorDTO();
        InspectionPlanTemplateDTO inspectionPlanTemplateDTO = new InspectionPlanTemplateDTO();
        InspectionPlanOperationStatusDTO inspectionPlanOperationStatusDTO = new InspectionPlanOperationStatusDTO();

        inspectionPlanOperation = new InspectionPlanOperationDTO();
        inspectionPlanOperation.setId(rs.getInt("idinspectionplan_operation"));
        inspectionPlanOperation.setDate(rs.getDate("date"));
        inspectionPlanOperation.setNorm(rs.getString("norm"));
        inspectionPlanOperation.setDescription(rs.getString("description"));
        inspectionPlanOperation.setStorageRack(rs.getString("storage_Rack"));
        inspectionPlanOperation.setLocation(rs.getString("location"));
        //Map Customer
        customerDTO.setId(rs.getInt("idcustomer"));
        customerDTO.setName(rs.getString("cusname"));
        customerDTO.setStreet(rs.getString("cusstreet"));
        customerDTO.setZipcode(rs.getString("cuszipcode"));
        customerDTO.setCity(rs.getString("cuscity"));
        customerDTO.setContactperson(rs.getString("cuscontactperson"));
        customerDTO.setContactperson(rs.getString("cuscontactperson"));
        customerDTO.setPhone(rs.getString("cusphone"));
        customerDTO.setFax(rs.getString("cusfax"));
        customerDTO.setEmail(rs.getString("cusemail"));
        inspectionPlanOperation.setCustomer(customerDTO);

        //map Inspector
        inspector.setId(rs.getInt("idinspector"));
        inspector.setName(rs.getString("insname"));
        inspector.setStreet(rs.getString("insstreet"));
        inspector.setZipcode(rs.getString("inszipcode"));
        inspector.setCity(rs.getString("inscity"));
        inspectionPlanOperation.setInspector(inspector);

        //map inspectionplan
        inspectionPlanTemplateDTO.setId(rs.getInt("inspectionplan_template_id"));
        inspectionPlanOperation.setInspectionplanTemplate(inspectionPlanTemplateDTO);
        //map operation status
        inspectionPlanOperationStatusDTO.setId(rs.getInt("idinspectionplan_operation_status"));
        inspectionPlanOperationStatusDTO.setDescription(rs.getString("iposDescription"));

        inspectionPlanOperation.setInspectionPlanOperationStatus(inspectionPlanOperationStatusDTO);

        return inspectionPlanOperation;
    }
*/
}


