/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mvc_model.CustomerDAO;
import mvc_model.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_Customer_DocumentController implements Initializable {

    @FXML
    private TableView<CustomerDTO> tbl_view_customer;
    @FXML
    private TableColumn<CustomerDTO, Integer> tbl_view_customer_id;
    @FXML
    private TableColumn<CustomerDTO, String> tbl_view_customer_name;
    @FXML
    private TableColumn<CustomerDTO, String> tbl_view_customer_street;
    @FXML
    private TableColumn<CustomerDTO, String> tbl_view_customer_zipcode;
    @FXML
    private TableColumn<CustomerDTO, String> tbl_view_customer_city;
    @FXML
    private TableColumn<CustomerDTO, String> tbl_view_customer_contactperson;
    @FXML
    private TableColumn<CustomerDTO, String> tbl_view_customer_phone;
    @FXML
    private TableColumn<CustomerDTO, String> tbl_view_customer_fax;
    @FXML
    private TableColumn<CustomerDTO, String> tbl_view_customer_email;
    @FXML
    private Button btn_view_customer_OK;
    private ObservableList<CustomerDTO> data;

    private FXML_Application_DocumentController fxml_application_controller;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            data = FXCollections.observableArrayList();

            for (CustomerDTO customer : new CustomerDAO().selectAllCustomer()) {
                data.add(customer);
            }

            tbl_view_customer_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tbl_view_customer_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            tbl_view_customer_street.setCellValueFactory(new PropertyValueFactory<>("street"));
            tbl_view_customer_zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
            tbl_view_customer_city.setCellValueFactory(new PropertyValueFactory<>("city"));
            tbl_view_customer_contactperson.setCellValueFactory(new PropertyValueFactory<>("contactperson"));
            tbl_view_customer_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            tbl_view_customer_fax.setCellValueFactory(new PropertyValueFactory<>("fax"));
            tbl_view_customer_email.setCellValueFactory(new PropertyValueFactory<>("email"));

            tbl_view_customer.setItems(null);
            tbl_view_customer.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(FXML_Customer_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btn_view_customer_OK_handler(ActionEvent event) {
        CustomerDTO customer = tbl_view_customer.getSelectionModel().getSelectedItem();
        this.fxml_application_controller.setCustomername(customer.getName());

        Stage stage = (Stage) btn_view_customer_OK.getScene().getWindow();
        stage.close();

    }

    public void setReference(FXML_Application_DocumentController controller) {
        this.fxml_application_controller = controller;
    }
    
}
