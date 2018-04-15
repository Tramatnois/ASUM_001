/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import mvc_model.CustomerDAO;
import mvc_model.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class AddCustomer_Controller extends AnchorPane {

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private JFXTextField tf_customer;
    @FXML
    private JFXTextField tf_street;
    @FXML
    private JFXTextField tf_zipcode;
    @FXML
    private JFXTextField tf_city;
    @FXML
    private JFXTextField tf_contactPerson;
    @FXML
    private JFXTextField tf_phone;
    @FXML
    private JFXTextField tf_fax;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXButton btn_newCustomer;
    @FXML
    private JFXButton btn_saveCustomer;
    @FXML
    private JFXButton btn_Exit;

    private Application_Controller application_controller;
    private static AddCustomer_Controller instance;

    public synchronized static AddCustomer_Controller getInstance() {
        if (instance == null) {
            instance = new AddCustomer_Controller();
        }
        return instance;
    }

    private AddCustomer_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/CustomerAdd.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(AddCustomer_Controller.class.getName()).log(Level.SEVERE, "Unable to load CustomerAdd.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

    }

    public void clear() {
//        instance = new AddCustomer_Controller();
// clear text fields
        instance.tf_customer.clear();
        instance.tf_street.clear();
        instance.tf_zipcode.clear();
        instance.tf_city.clear();
        instance.tf_contactPerson.clear();
        instance.tf_phone.clear();
        instance.tf_fax.clear();
        instance.tf_email.clear();
    }

    public void setApplication_controller(Application_Controller controller) {
        this.application_controller = controller;
    }

    public void saveNewCustomer() {
        CustomerDTO customer = new CustomerDTO();
        CustomerDAO customer_model = new CustomerDAO();
        customer.setName(this.tf_customer.getText());
        customer.setStreet(this.tf_street.getText());
        customer.setZipcode(this.tf_zipcode.getText());
        customer.setCity(this.tf_city.getText());
        customer.setContactperson(this.tf_contactPerson.getText());
        customer.setPhone(this.tf_phone.getText());
        customer.setFax(this.tf_fax.getText());
        customer.setEmail(this.tf_email.getText());
        customer.setActive(1);
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Kunde wurde erfolgreich angelegt.");
            alert.showAndWait();
            customer_model.insertCustomer(customer);
            this.application_controller.getCustomer().refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btn_Exit_Handler(ActionEvent event) {
        System.out.println("Button Exit pressed");
        this.application_controller.setNode("CUSTOMER");
        this.clear();
    }

    @FXML
    void btn_newCustomer_Handler(ActionEvent event) {
        this.clear();

    }

    @FXML
    void btn_saveCustomer_Handler(ActionEvent event) {
        this.saveNewCustomer();
    }

}
