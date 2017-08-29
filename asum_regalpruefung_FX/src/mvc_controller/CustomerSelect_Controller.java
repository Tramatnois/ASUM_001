package mvc_controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import mvc_model.CustomerDTO;

public class CustomerSelect_Controller extends AnchorPane {

    @FXML
    private JFXTreeTableView<CustomerDTO> tbl_view_customer_select;

    @FXML
    private JFXRadioButton rb_customerID;

    @FXML
    private ToggleGroup filter;

    @FXML
    private JFXRadioButton rb_customerName;

    @FXML
    private JFXTextField tf_searchCustomer;

    private Application_Controller application_controller;
    private static CustomerSelect_Controller instance;

    /**
     * Statische Methode 'getInstance()Ä liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     */
    public synchronized static CustomerSelect_Controller getInstance() {
        if (instance == null) {
            instance = new CustomerSelect_Controller();
        }
        return instance;
    }

    private CustomerSelect_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/CustomerSelect.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(CustomerSelect_Controller.class.getName()).log(Level.SEVERE, "Unable to load CustomerSelect.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

        rb_customerID.setSelectedColor(Color.DARKGOLDENROD);
        rb_customerID.setUnSelectedColor(Color.WHITESMOKE);
        rb_customerName.setSelectedColor(Color.BLUEVIOLET);
        rb_customerName.setUnSelectedColor(Color.WHITE);
// get customer table
//prepare column: CustomerID
        JFXTreeTableColumn<CustomerDTO, Integer> customer_id = new JFXTreeTableColumn<>("ID");
        customer_id.setPrefWidth(50);
//            customer_id.getStyleClass().add("Col_customer_id");
        customer_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());
//prepare column: CustomerName
        JFXTreeTableColumn<CustomerDTO, String> customer_name = new JFXTreeTableColumn<>("Kunde");
        customer_name.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getNameProperty());
        customer_name.getStyleClass().add("Col_customer_name");
//prepare column: CustomerZipCode
        JFXTreeTableColumn<CustomerDTO, String> customer_zipCode = new JFXTreeTableColumn<>("Postleitzahl");
        customer_zipCode.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getZipCodeProperty());
//            customer_zipCode.getStyleClass().add("Col_customer_zipCode");
//prepare column: CustomerCity
        JFXTreeTableColumn<CustomerDTO, String> customer_city = new JFXTreeTableColumn<>("Ort");
        customer_city.setCellValueFactory((TreeTableColumn.CellDataFeatures<CustomerDTO, String> param) -> param.getValue().getValue().getCityProperty());
//            customer_city.getStyleClass().add("Col_customer_city");

//        this.tbl_view_customer_select = this.application_controller.getCustomer().getTbl_view_customer();
        tbl_view_customer_select.getColumns().setAll(
                customer_id, customer_name, customer_zipCode, customer_city
        );

        tbl_view_customer_select.setFocusTraversable(true);
        tbl_view_customer_select.setShowRoot(false);
        tbl_view_customer_select.getStyleClass().add("CustomerSelectTable");
        
// setDoubleClickEvent Handler
        tbl_view_customer_select.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TreeItem<CustomerDTO> customer = tbl_view_customer_select.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 2) {
                    application_controller.getResultsRecording_Controller().setCustomer(customer.getValue().getId());
                    application_controller.getResultsRecording_Controller().setCustomer(customer.getValue().getName());
                    application_controller.getResultsRecording_Controller().getPopup().hide();
                }
            }

        });

// add filter for customer name
        tf_searchCustomer.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tbl_view_customer_select.setPredicate(new Predicate<TreeItem<CustomerDTO>>() {
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

    }

    public void setApplication_controller(Application_Controller controller) {
        this.application_controller = controller;
    }

    public JFXTreeTableView<CustomerDTO> getTbl_view_customer_select() {
        prepareCustomerSelectTable();
        return tbl_view_customer_select;
    }

    public void prepareCustomerSelectTable() {
        tbl_view_customer_select.setRoot(this.application_controller.getCustomer().getTbl_view_customer().getRoot());
    }

    @FXML
    void tbl_view_customer_select_handler(MouseEvent event) {

    }

}
