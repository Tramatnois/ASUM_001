/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import mvc_model.CustomerDAO;
import mvc_model.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_Customer_DocumentController extends AnchorPane {

    @FXML
    private JFXTreeTableView<CustomerDTO> tbl_view_customer;
    @FXML
    private JFXTextField tf_filter_name;
    @FXML
    private JFXTextField tf_filter_contactPerson;    
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
            customer_id.getStyleClass().add("Col_customer_id");
            customer_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());
//prepare column: CustomerName
            JFXTreeTableColumn<CustomerDTO, String> customer_name = new JFXTreeTableColumn<>("Kundenname");
            customer_name.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getNameProperty());
            customer_name.getStyleClass().add("Col_customer_name");
//prepare column: CustomerStreet
            JFXTreeTableColumn<CustomerDTO, String> customer_street = new JFXTreeTableColumn<>("Straße");
            customer_street.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getStreetProperty());
            customer_street.getStyleClass().add("Col_customer_street");
//prepare column: CustomerZipCode
            JFXTreeTableColumn<CustomerDTO, String> customer_zipCode = new JFXTreeTableColumn<>("Postleitzahl");
            customer_zipCode.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getZipCodeProperty());
            customer_zipCode.getStyleClass().add("Col_customer_zipCode");
//prepare column: CustomerCity
            JFXTreeTableColumn<CustomerDTO, String> customer_city = new JFXTreeTableColumn<>("Ort");
            customer_city.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getCityProperty());
            customer_city.getStyleClass().add("Col_customer_city");
//prepare column: CustomerContactPerson
            JFXTreeTableColumn<CustomerDTO, String> customer_contactPerson = new JFXTreeTableColumn<>("Kontaktperson");
            customer_contactPerson.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getContactpersonProperty());
            customer_contactPerson.getStyleClass().add("Col_customer_contactPerson");
//prepare column: CustomerPhone
            JFXTreeTableColumn<CustomerDTO, String> customer_phone = new JFXTreeTableColumn<>("Telefon");
            customer_phone.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getPhoneProperty());
            customer_phone.getStyleClass().add("Col_customer_phone");
            data = FXCollections.observableArrayList();
//prepare column: CustomerFax
            JFXTreeTableColumn<CustomerDTO, String> customer_fax = new JFXTreeTableColumn<>("Fax");
            customer_fax.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getFaxProperty());
            customer_fax.getStyleClass().add("Col_customer_fax");
//prepare column: CustomerEmail
            JFXTreeTableColumn<CustomerDTO, String> customer_email = new JFXTreeTableColumn<>("E-Mail");
            customer_email.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getEmailProperty());
            customer_email.getStyleClass().add("Col_customer_contactPerson");

            for (CustomerDTO customer : new CustomerDAO().selectAllCustomer()) {
                data.add(customer);
            }

            final TreeItem<CustomerDTO> root;
            root = new RecursiveTreeItem<CustomerDTO>(data, RecursiveTreeObject::getChildren);

            tbl_view_customer.getColumns().setAll(
                                                    customer_id, customer_name, customer_street, customer_zipCode, customer_city, customer_contactPerson,
                                                    customer_phone, customer_fax, customer_email
                                                  );
            tbl_view_customer.setRoot(root);
            tbl_view_customer.setShowRoot(false);
                                    
// add filter for customer name
            tf_filter_name.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   tbl_view_customer.setPredicate(new Predicate<TreeItem<CustomerDTO>>() {
                       @Override
                       public boolean test(TreeItem<CustomerDTO> customer) {
                           Boolean flag = customer.getValue().getName().contains(newValue);
                           return flag;
                       }
                   });
                }
            } );
// add filter for customer contact person
            tf_filter_contactPerson.textProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   tbl_view_customer.setPredicate(new Predicate<TreeItem<CustomerDTO>>() {
                       @Override
                       public boolean test(TreeItem<CustomerDTO> customer) {
                           Boolean flag = customer.getValue().getContactperson().contains(newValue);
                           return flag;
                       }
                   });
                }
            } );            
        } catch (SQLException ex) {
            Logger.getLogger(FXML_Customer_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
