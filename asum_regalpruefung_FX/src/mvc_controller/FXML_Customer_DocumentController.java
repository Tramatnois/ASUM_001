/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mvc_model.CustomerDAO;
import mvc_model.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_Customer_DocumentController extends AnchorPane {

//    @FXML
//    private TableView<CustomerDTO> tbl_view_customer;
//    @FXML
//    private TableColumn<CustomerDTO, Integer> tbl_view_customer_id;
//    @FXML
//    private TableColumn<CustomerDTO, String> tbl_view_customer_name;
//    @FXML
//    private TableColumn<CustomerDTO, String> tbl_view_customer_street;
//    @FXML
//    private TableColumn<CustomerDTO, String> tbl_view_customer_zipcode;
//    @FXML
//    private TableColumn<CustomerDTO, String> tbl_view_customer_city;
//    @FXML
//    private TableColumn<CustomerDTO, String> tbl_view_customer_contactperson;
//    @FXML
//    private TableColumn<CustomerDTO, String> tbl_view_customer_phone;
//    @FXML
//    private TableColumn<CustomerDTO, String> tbl_view_customer_fax;
//    @FXML
//    private TableColumn<CustomerDTO, String> tbl_view_customer_email;
    @FXML
    private JFXTreeTableView<CustomerDTO> tbl_view_customer;
    @FXML
    private Button btn_view_customer_OK;
    @FXML
    private AnchorPane subPane;

    private ObservableList<CustomerDTO> data;
    private FXML_Application_DocumentController fxml_application_controller;
    private static FXML_Customer_DocumentController instance;

    /**
     * Statische Methode 'getInstance()Ä liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     */
    public synchronized static FXML_Customer_DocumentController getInstance() {
        if (instance == null) {
            instance = new FXML_Customer_DocumentController();
        }
        return instance;
    }

    /**
     * Konstruktor ist privat, Klasse darf nicht von außen instanziiert werden.
     */
    private FXML_Customer_DocumentController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_Customer_Document.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, "Unable to load FXML_Customer_Document.fxml", ex);
        }
        initialize();
    }

    private void initialize() {
        try {
//prepare column: CustomerID
            JFXTreeTableColumn<CustomerDTO, Integer> customer_id = new JFXTreeTableColumn<>("ID");
            customer_id.setPrefWidth(50);
            //inspection_id.setPrefWidth(Control.USE_COMPUTED_SIZE);
            customer_id.getStyleClass().add("col_customer_id");
            customer_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());

//prepare column: CustomerName
            JFXTreeTableColumn<CustomerDTO, String> customer_name = new JFXTreeTableColumn<>("Kundenname");
            customer_name.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getNameProperty());
            customer_name.getStyleClass().add("col_customer_id");

            
            
            
            data = FXCollections.observableArrayList();

            for (CustomerDTO customer : new CustomerDAO().selectAllCustomer()) {
                data.add(customer);
            }

//            tbl_view_customer_id.setCellValueFactory(new PropertyValueFactory<>("id"));
//            tbl_view_customer_name.setCellValueFactory(new PropertyValueFactory<>("name"));
//            tbl_view_customer_street.setCellValueFactory(new PropertyValueFactory<>("street"));
//            tbl_view_customer_zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
//            tbl_view_customer_city.setCellValueFactory(new PropertyValueFactory<>("city"));
//            tbl_view_customer_contactperson.setCellValueFactory(new PropertyValueFactory<>("contactperson"));
//            tbl_view_customer_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
//            tbl_view_customer_fax.setCellValueFactory(new PropertyValueFactory<>("fax"));
//            tbl_view_customer_email.setCellValueFactory(new PropertyValueFactory<>("email"));
//
//            tbl_view_customer.setItems(null);
//            tbl_view_customer.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(FXML_Customer_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            // TODO
//            
//
//                data = FXCollections.observableArrayList();
//
//                for (CustomerDTO customer : new CustomerDAO().selectAllCustomer()) {
//                    data.add(customer);
//                }
//
//                tbl_view_customer_id.setCellValueFactory(new PropertyValueFactory<>("id"));
//                tbl_view_customer_name.setCellValueFactory(new PropertyValueFactory<>("name"));
//                tbl_view_customer_street.setCellValueFactory(new PropertyValueFactory<>("street"));
//                tbl_view_customer_zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
//                tbl_view_customer_city.setCellValueFactory(new PropertyValueFactory<>("city"));
//                tbl_view_customer_contactperson.setCellValueFactory(new PropertyValueFactory<>("contactperson"));
//                tbl_view_customer_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
//                tbl_view_customer_fax.setCellValueFactory(new PropertyValueFactory<>("fax"));
//                tbl_view_customer_email.setCellValueFactory(new PropertyValueFactory<>("email"));
//
//                tbl_view_customer.setItems(null);
//                tbl_view_customer.setItems(data);/
//               
//
//        } catch (SQLException ex) {
//            Logger.getLogger(FXML_Customer_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    @FXML
    private void btn_view_customer_OK_handler(ActionEvent event) {
        TreeItem<CustomerDTO> customer = tbl_view_customer.getSelectionModel().getSelectedItem();
//        this.fxml_application_controller.setCustomername(customer.getName());
        this.fxml_application_controller.setCustomername(customer.getValue().getName());
        this.fxml_application_controller.drawerCustomerView.close();
//        Stage stage = (Stage) btn_view_customer_OK.getScene().getWindow();        
//        stage.close();
    }

    public void setReference(FXML_Application_DocumentController controller) {
        this.fxml_application_controller = controller;
    }

}
