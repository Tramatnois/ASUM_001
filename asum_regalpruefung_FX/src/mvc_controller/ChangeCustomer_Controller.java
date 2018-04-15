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
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import mvc_model.CustomerDAO;
import mvc_model.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class ChangeCustomer_Controller extends AnchorPane {

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private Label tf_title;
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
    private JFXButton btn_saveCustomer;
    @FXML
    private JFXButton btn_Exit;

    private Application_Controller application_controller;
    private static ChangeCustomer_Controller instance;
    private CustomerDTO customer;

    public synchronized static ChangeCustomer_Controller getInstance() {
        if (instance == null) {
            instance = new ChangeCustomer_Controller();
        }
        return instance;
    }

    private ChangeCustomer_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/CustomerChange.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ChangeCustomer_Controller.class.getName()).log(Level.SEVERE, "Unable to load CustomerChange.fxml", ex);
        }
        initialize();
    }

    private void initialize() {
        
    }

    public void clear() {
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
    public void prefill() {
        if (this.application_controller != null) {

            CustomerDAO customer_model = new CustomerDAO();
            TreeTableView.TreeTableViewSelectionModel<CustomerDTO> selectionModel;
            selectionModel = this.application_controller.getCustomer().getTbl_view_customer().getSelectionModel();

            // get the selected row index
            int rowIndex = selectionModel.getSelectedIndex();
            // get current customer item
            TreeItem<CustomerDTO> selectedItem = selectionModel.getModelItem(rowIndex);
            TreeItem<CustomerDTO> parent = selectedItem.getParent();

            this.customer = selectedItem.getValue();
            // set text fields
            instance.tf_customer.setText(this.customer.getName());
            instance.tf_street.setText(this.customer.getStreet());
            instance.tf_zipcode.setText(this.customer.getZipcode());
            instance.tf_city.setText(this.customer.getCity());
            instance.tf_contactPerson.setText(this.customer.getContactperson());
            instance.tf_phone.setText(this.customer.getPhone());
            instance.tf_fax.setText(this.customer.getFax());
            instance.tf_email.setText(this.customer.getEmail());
        }
    }

    public void setApplication_controller(Application_Controller controller) {
        this.application_controller = controller;
    }

    public void saveCustomer() {
        CustomerDAO customer_model = new CustomerDAO();
        this.customer.setName(this.tf_customer.getText());
        this.customer.setStreet(this.tf_street.getText());
        this.customer.setZipcode(this.tf_zipcode.getText());
        this.customer.setCity(this.tf_city.getText());
        this.customer.setContactperson(this.tf_contactPerson.getText());
        this.customer.setPhone(this.tf_phone.getText());
        this.customer.setFax(this.tf_fax.getText());
        this.customer.setEmail(this.tf_email.getText());
        //customer.setActive(1);
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Kunde wurde erfolgreich gespeichert.");
            alert.showAndWait();
            customer_model.updateCustomer(customer);
            this.application_controller.getCustomer().refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeCustomer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btn_Exit_Handler(ActionEvent event) {
        System.out.println("Button Exit pressed");
        this.application_controller.setNode("CUSTOMER");
        this.clear();
    }

    @FXML
    void btn_saveCustomer_Handler(ActionEvent event) {
        this.saveCustomer();
    }

}
