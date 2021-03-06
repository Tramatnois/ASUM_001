/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import mvc_model.CustomerDAO;
import mvc_model.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class Customer_Controller extends AnchorPane {

    @FXML
    private AnchorPane holderAnchor;

    @FXML
    private JFXTreeTableView<CustomerDTO> tbl_view_customer;

    public JFXTreeTableView<CustomerDTO> getTbl_view_customer() {
        return tbl_view_customer;
    }

    @FXML
    private JFXTextField tf_filter;
    @FXML
    private ToggleGroup filter;
    @FXML
    private JFXRadioButton rb_customerID;
    @FXML
    private JFXRadioButton rb_customerName;
    @FXML
    private JFXButton btn_searchCustomer;

    @FXML
    private Label lblName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblFax;

    @FXML
    private Label lblStreet;

    @FXML
    private Label lblZipCode;

    @FXML
    private Label lblCity;

    @FXML
    private JFXCheckBox cb_activeUser;

    @FXML
    private AnchorPane fabPane;

    @FXML
    private Label fabEdit;

    private ObservableList<CustomerDTO> data;
    private Application_Controller application_controller;
    private static Customer_Controller instance;
    
    final ContextMenu contextMenu = new ContextMenu();

    public synchronized static Customer_Controller getInstance() {
        if (instance == null) {
            instance = new Customer_Controller();
        }
        return instance;
    }

    private Customer_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/Customer.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, "Unable to load Customer.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

// init radio buttons. I dont know, why it is not possible to style them with CSS        
        rb_customerID.setSelectedColor(Color.DARKGOLDENROD);
        rb_customerID.setUnSelectedColor(Color.WHITESMOKE);
        rb_customerName.setSelectedColor(Color.BLUEVIOLET);
        rb_customerName.setUnSelectedColor(Color.WHITE);

        try {
            //prepare column: CustomerID
            JFXTreeTableColumn<CustomerDTO, Integer> customer_id = new JFXTreeTableColumn<>("ID");
            customer_id.setPrefWidth(50);
//            customer_id.getStyleClass().add("Col_customer_id");
            customer_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());
//prepare column: CustomerName
            JFXTreeTableColumn<CustomerDTO, String> customer_name = new JFXTreeTableColumn<>("Kunde");
            customer_name.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getNameProperty());
            customer_name.getStyleClass().add("Col_customer_name");
            customer_name.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//prepare column: CustomerStreet
            JFXTreeTableColumn<CustomerDTO, String> customer_street = new JFXTreeTableColumn<>("Straße");
            customer_street.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getStreetProperty());
//            customer_street.getStyleClass().add("Col_customer_street");
            customer_street.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//prepare column: CustomerZipCode
            JFXTreeTableColumn<CustomerDTO, String> customer_zipCode = new JFXTreeTableColumn<>("Postleitzahl");
            customer_zipCode.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getZipCodeProperty());
//            customer_zipCode.getStyleClass().add("Col_customer_zipCode");
            customer_zipCode.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//prepare column: CustomerCity
            JFXTreeTableColumn<CustomerDTO, String> customer_city = new JFXTreeTableColumn<>("Ort");
            customer_city.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getCityProperty());
//            customer_city.getStyleClass().add("Col_customer_city");
            customer_city.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//prepare column: CustomerContactPerson
            JFXTreeTableColumn<CustomerDTO, String> customer_contactPerson = new JFXTreeTableColumn<>("Kontaktperson");
            customer_contactPerson.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getContactpersonProperty());
//            customer_contactPerson.getStyleClass().add("Col_customer_contactPerson");
            customer_contactPerson.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//prepare column: CustomerPhone
            JFXTreeTableColumn<CustomerDTO, String> customer_phone = new JFXTreeTableColumn<>("Telefon");
            customer_phone.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getPhoneProperty());
//            customer_phone.getStyleClass().add("Col_customer_phone");  
            customer_phone.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//prepare column: CustomerFax
            JFXTreeTableColumn<CustomerDTO, String> customer_fax = new JFXTreeTableColumn<>("Fax");
            customer_fax.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getFaxProperty());
//            customer_fax.getStyleClass().add("Col_customer_fax");
            customer_fax.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//prepare column: CustomerEmail
            JFXTreeTableColumn<CustomerDTO, String> customer_email = new JFXTreeTableColumn<>("E-Mail");
            customer_email.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getEmailProperty());
//            customer_email.getStyleClass().add("Col_customer_contactPerson");

            data = FXCollections.observableArrayList();
            for (CustomerDTO customer : new CustomerDAO().selectAllCustomer()) {
                data.add(customer);
            }

            final TreeItem<CustomerDTO> root;
            root = new RecursiveTreeItem<CustomerDTO>(data, RecursiveTreeObject::getChildren);

            tbl_view_customer.getColumns().setAll(
                    customer_id, customer_name, customer_street, customer_zipCode, customer_city, customer_contactPerson
            //                    customer_phone, customer_fax, customer_email
            );
            tbl_view_customer.setRoot(root);
            tbl_view_customer.setFocusTraversable(true);
            tbl_view_customer.setShowRoot(false);
            tbl_view_customer.getStyleClass().add("CustomerTable");

            // add context menu
            MenuItem edit = new MenuItem("bearbeiten");
            edit.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("edit clicked");
                    application_controller.setNode("CHANGE_CUSTOMER");
                }
                
            });
            
            MenuItem delete = new MenuItem("löschen");
            delete.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("delete clicked");
                    CustomerDTO customer;
        CustomerDAO customer_model = new CustomerDAO();
        TreeTableView.TreeTableViewSelectionModel<CustomerDTO> selectionModel = tbl_view_customer.getSelectionModel();
        
        // get the selected row index
        int rowIndex = selectionModel.getSelectedIndex();
        // get current customer item
        TreeItem<CustomerDTO> selectedItem = selectionModel.getModelItem(rowIndex);
        TreeItem<CustomerDTO> parent = selectedItem.getParent();
        
        customer = selectedItem.getValue();
        
        customer.setActive(0);
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Kunde wurde erfolgreich deaktiviert.");
            alert.showAndWait();
            customer_model.updateCustomer(customer);
            parent.getChildren().remove(selectedItem);
        } catch (SQLException ex) {
            Logger.getLogger(Application_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
                
            });
            contextMenu.getItems().addAll(edit, delete);
            tbl_view_customer.setContextMenu(contextMenu);

// add filter for customer name
            tf_filter.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    tbl_view_customer.setPredicate(new Predicate<TreeItem<CustomerDTO>>() {
                        @Override
                        public boolean test(TreeItem<CustomerDTO> customer) {
                            Boolean flag = false;
//                            Boolean flag = customer.getValue().getName().contains(newValue) | customer.getValue().getId().toString().contains(newValue);
                            if (rb_customerID.isSelected()) {
                                flag = customer.getValue().getId().toString().contains(newValue);
                            }
                            if (rb_customerName.isSelected()) {
                                flag = customer.getValue().getName().contains(newValue);
                            }

                            return flag;
                        }
                    });
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setApplication_controller(Application_Controller controller) {
        this.application_controller = controller;
    }

    public void refreshTable() {
        data.clear();
        try {
            for (CustomerDTO customer : new CustomerDAO().selectAllCustomer()) {
                data.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void tbl_view_customer_handler(MouseEvent event) {
        TreeItem<CustomerDTO> customer = tbl_view_customer.getSelectionModel().getSelectedItem();
        lblName.setText(customer.getValue().getName());
        lblEmail.setText(customer.getValue().getEmail());
        lblPhone.setText(customer.getValue().getPhone());
        lblFax.setText(customer.getValue().getFax());
        lblStreet.setText(customer.getValue().getStreet());
        lblZipCode.setText(customer.getValue().getZipcode());
        lblCity.setText(customer.getValue().getCity());
        cb_activeUser.setSelected(customer.getValue().getActiveBoolean());

    }

    @FXML
    void btn_searchCustomer_handler(ActionEvent event) {
//        FilterableTreeItem<CustomerDTO> root = tbl_view_customer.getRoot();
//
//        root.predicateProperty().bind(Bindings.createObjectBinding(() -> {
//            if (tf_filter.getText() == null || tf_filter.getText().isEmpty()) {
//                return null;
//            }
//            return TreeItemPredicate.create(customer -> customer.toString().contains(tf_filter.getText()));
//        }, tf_filter.textProperty()));
//
//        JFXTreeTableView<CustomerDTO> treeView;
//        treeView = new JFXTreeTableView<>(root);
//        tbl_view_customer.setRoot(root);
    }

    @FXML
    void tbl_view_customer_handler_context(ContextMenuEvent event) {
        System.out.println("show context menu");
        event.consume();

    }
}
