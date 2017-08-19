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
import mvc_model.CustomerDAO;
import mvc_model.InspectionPlanOperationStatusDAO;

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
//            query = "SELECT ipo.idinspectionplan_operation, ipo.date, ipo.norm, ipo.description, ipo.storage_Rack, ipo.location, ipo.customer_id, ipo.inspector_id, ipo.inspectionplan_template_id,"
//                + " ipos.idinspectionplan_operation_status, ipos.description AS iposDescription "
//                + "FROM inspectionplan_operation_tab ipo JOIN inspectionplan_operation_status_tab ipos ON ipo.inspectionplan_operation_status_id  = ipos.idinspectionplan_operation_status "
//                ;

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

    //DBConnection connection = new DBConnection();
    /**
     * Returns a single inspector ordered by ID
     *
     * @param id = ID of the InspectionPlanOperationDTO
     * @return returns a single InspectionPlanOperationDTO which is ordered by
     * ID
     * @throws SQLException
     */
    public InspectionPlanOperationDTO selectSingleById(int id) throws SQLException {
        InspectionPlanOperationDTO inspectionPlanOperationDTO;
        String query;
        PreparedStatement preparedStmt;
        ResultSet rs;

        //query = "SELECT * FROM inspectionplan_operation_tab where idinspectionplan_operation=?";
        query = "SELECT ipo.idinspectionplan_operation, ipo.date, ipo.norm, ipo.description, ipo.storage_Rack, ipo.location, ipo.customer_id, ipo.inspector_id, ipo.inspectionplan_template_id, ipo.inspectionplan_operation_status_id, "
                + " ipos.idinspectionplan_operation_status, ipos.description AS iposDescription "
                + "FROM inspectionplan_operation_tab ipo JOIN inspectionplan_operation_status_tab ipos ON ipo.inspectionplan_operation_status_id  = ipos.idinspectionplan_operation_status "
                + "where idinspectionplan_operation=?";

        preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, id);

        rs = preparedStmt.executeQuery();
        if (rs.next()) {
            inspectionPlanOperationDTO = this.mapInspectionPlanOperation(rs);
        } else {
            return null;
        }
        rs.close();
        preparedStmt.close();

        return inspectionPlanOperationDTO;
    }

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

    /**
     * Adds a inspector to the Database. Id of the inspector object is ignored
     *
     * @param inspectionPlanOperationDTO
     * @throws SQLException
     * @return id of inserted Inspector or -1 = Error
     */
    public int insert(InspectionPlanOperationDTO inspectionPlanOperationDTO) throws SQLException {
        ResultSet rs;
        int generatedKey = -1;
        String query = " insert into inspectionplan_operation_tab (date, norm, description, storage_Rack, location, customer_id, inspector_id, inspectionplan_template_id , inspectionplan_operation_status_id)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setDate(1, inspectionPlanOperationDTO.getDate());
        preparedStmt.setString(2, inspectionPlanOperationDTO.getNorm());
        preparedStmt.setString(3, inspectionPlanOperationDTO.getDescription());
        preparedStmt.setString(4, inspectionPlanOperationDTO.getStorageRack());
        preparedStmt.setString(5, inspectionPlanOperationDTO.getLocation());
        preparedStmt.setInt(6, inspectionPlanOperationDTO.getCustomer().getId());
        preparedStmt.setInt(7, inspectionPlanOperationDTO.getInspector().getId());
        preparedStmt.setInt(8, inspectionPlanOperationDTO.getInspectionplanTemplate().getId());
        preparedStmt.setInt(9, inspectionPlanOperationDTO.getInspectionPlanOperationStatus().getId());

        // execute the preparedstatement
        preparedStmt.execute();
        rs = preparedStmt.getGeneratedKeys();
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }

    /**
     * Updates a inspector in the database
     *
     * @param inspector inspector who is updated. All fields of the object are
     * updated.
     * @throws SQLException
     */
    public void update(InspectionPlanOperationDTO inspectionPlanOperationDTO) throws SQLException {

        String query = "Update inspectionplan_operation_tab SET date=?, norm=?, description=?, storage_Rack=?, location=? WHERE idinspectionplan_operation=?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setDate(1, inspectionPlanOperationDTO.getDate());
        preparedStmt.setString(2, inspectionPlanOperationDTO.getNorm());
        preparedStmt.setString(3, inspectionPlanOperationDTO.getDescription());
        preparedStmt.setString(4, inspectionPlanOperationDTO.getStorageRack());
        preparedStmt.setString(5, inspectionPlanOperationDTO.getLocation());
        preparedStmt.setInt(6, inspectionPlanOperationDTO.getId());
        // execute the preparedstatement
        preparedStmt.executeUpdate();
        preparedStmt.close();

    }

    /**
     * Deletes a inspector from Database It is enough if the inspector object
     * contains an id
     *
     * @param inspectionPlanOperationDTO inspector who should be deleted
     * @throws SQLException
     */
    public void delete(InspectionPlanOperationDTO inspectionPlanOperationDTO) throws SQLException {

        String query = "DELETE FROM inspectionplan_operation_tab WHERE idinspectionplan_operation=?";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = connection.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, inspectionPlanOperationDTO.getId());
        // execute the preparedstatement
        preparedStmt.execute();

    }

    /**
     * Maps a resutlSet to a InspectionPlanOperationDTO Object
     *
     *
     * @param rs ResultSet. The Pointer must be set on the
     * inspectionPlanOperation who should be mapped
     * @return mapped InspectionPlanOperationDTO2 object
     * @throws SQLException
     */
    private InspectionPlanOperationDTO mapInspectionPlanOperation(ResultSet rs) throws SQLException {
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

        //@ Daniel: Bitte erstmal nicht ändern. Müssen telefoinieren. 
        //@ Das mit den Select Join funktioniert nicht so ganz wie du dir das vorgestellt hast.
//        customerDTO.setId(rs.getInt("customer_id"));
//        inspectionPlanOperation.setCustomer(customerDTO);
//@ Daniel: Vielleicht im DTO eine get Methode für das DAO Objekt? Es macht kein Sinn hier ein DAO Objekt zu initialisieren
//@Stefan: Warum nicht? Das CCustomer Objekt wird nur intialisiert, um die Customer ID zu tragen.
        CustomerDAO customerDAO = new CustomerDAO();
        customerDTO = customerDAO.selectSingleCustomer(rs.getInt("customer_id"));
        inspectionPlanOperation.setCustomer(customerDTO);

        inspector.setId(rs.getInt("inspector_id"));
        inspectionPlanOperation.setInspector(inspector);

        inspectionPlanTemplateDTO.setId(rs.getInt("inspectionplan_template_id"));
        inspectionPlanOperation.setInspectionplanTemplate(inspectionPlanTemplateDTO);

        //@ Daniel: Bitte erstmal nicht ändern. Müssen telefoinieren.
//        inspectionPlanOperationStatusDTO.setId(rs.getInt("inspectionplan_operation_status_id"));
//@ Daniel: Vielleicht im DTO eine get Methode für das DAO Objekt? Es macht kein Sinn hier ein DAO Objekt zu initialisieren
//@Stefan: Warum nicht? Das CCustomer Objekt wird nur intialisiert, um die Customer ID zu tragen.
         
        InspectionPlanOperationStatusDAO inspplanop_StatusDAO = new InspectionPlanOperationStatusDAO();
        inspectionPlanOperationStatusDTO = inspplanop_StatusDAO.selectSingleInspectionPlanOperationStatus(rs.getInt("inspectionplan_operation_status_id"));
        
//        inspectionPlanOperationStatusDTO.setDescription(rs.getString("description"));

        inspectionPlanOperation.setInspectionPlanOperationStatus(inspectionPlanOperationStatusDTO);

        return inspectionPlanOperation;
    }

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

}
