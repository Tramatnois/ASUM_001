/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mvc_model.CustomerDTO;
import mvc_model_sqlconnector.DBConnection;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_StorageRackInsp_DocumentController extends BorderPane {

    @FXML
    private VBox VBox_Top_left;
    @FXML
    private VBox VBox_Top_Center;
    @FXML
    private VBox VBox_Top_Right;
    @FXML
    private TableView<CustomerDTO> tableView_inspResults;
    @FXML
    private TableColumn<CustomerDTO, Integer> clm_pos;
    @FXML
    private TableColumn<CustomerDTO, String> clm_characteristic;
    @FXML
    private TableColumn<CustomerDTO, Integer> clm_yes;
    @FXML
    private TableColumn<CustomerDTO, Integer> clm_neutral;
    @FXML
    private TableColumn<CustomerDTO, Integer> clm_no;
    @FXML
    private TableColumn<CustomerDTO, Boolean> clm_comments;
    @FXML
    private TextField tf_inspectionType;
    @FXML
    private TextField tf_customer_name;
    @FXML
    private JFXDrawer drawer;
    @FXML
    public JFXDrawer drawerContentView;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDatePicker tf_datepicker;
    @FXML
    private TextField tf_connected;
    @FXML
    private Button btn_show_all_inspplan_operations;
    @FXML
    private Button btn_load_customer;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane subPane;
//    @FXML
//    private AnchorPane customerPane;

    private static FXML_StorageRackInsp_DocumentController instance;
    private static Stage inspPlanOpStage;
    private static Stage customerStage;

    protected DBConnection connection;

    /**
     * Statische Methode 'getInstance()Ä liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     */
    public synchronized static FXML_StorageRackInsp_DocumentController getInstance() {
        if (instance == null) {
            instance = new FXML_StorageRackInsp_DocumentController();
        }
        return instance;
    }

    /**
     * Konstruktor ist privat, Klasse darf nicht von außen instanziiert werden.
     */
    private FXML_StorageRackInsp_DocumentController() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_Application_Document.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
// Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, "Unable to load FXML_Application_Document.fxml", ex);
        }
        initialize();
    }

    private void initialize() {
        try {
            HBox box = FXMLLoader.load(getClass().getResource("/mvc_view_application/SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
//        tf_date.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        tf_datepicker.setValue(LocalDate.now());
// check the database connection
        connection = DBConnection.getInstance();
        DropShadow dropShadow = new DropShadow();
        if (connection.IsConnected()) {
            tf_connected.setText(connection.getHost() + "." + connection.getDatabase());
//            dropShadow.setColor(Color.GREEN);
//            tf_connected.setEffect(dropShadow);
            tf_connected.setStyle("-fx-background-color: #009688;-fx-text-fill: white;");
        } else {
            tf_connected.setText("connection failure");
            dropShadow.setOffsetY(3.0f);
            dropShadow.setColor(Color.RED);
            tf_connected.setEffect(dropShadow);
        }
    }

    public Stage getCustomerStage() {
        return customerStage;
    }

    public void setCustomerStage(Stage customerStage) {
        FXML_StorageRackInsp_DocumentController.customerStage = customerStage;
    }

    public Stage getInspPlanOpStage() {
        return inspPlanOpStage;
    }

    public void setInspPlanOpStage(Stage inspPlanOpStage) {
        FXML_StorageRackInsp_DocumentController.inspPlanOpStage = inspPlanOpStage;
    }

    @FXML
    private void btn_load_customer_handler(ActionEvent event) {

        FXML_Customer_DocumentController customerPane = FXML_Customer_DocumentController.getInstance();
        customerPane.setReference(this);

        drawerContentView.setSidePane(customerPane);
        if (drawerContentView.isShown()) {
            drawerContentView.close();
        } else {
            drawerContentView.open();
        }

    }

    @FXML
    private void btn_show_all_inspplan_operations_handler(ActionEvent event) {

        FXML_InspPlanOp_DocumentController inspPlanOpPane = FXML_InspPlanOp_DocumentController.getInstance();
        inspPlanOpPane.setReference(this);
        drawerContentView.setSidePane(inspPlanOpPane);
        if (drawerContentView.isShown()) {
            drawerContentView.close();
        } else {
            drawerContentView.open();
        }

    }

    public void setCustomername(String name) {
        System.out.println(name);
        tf_customer_name.setText(name);
    }

    public void setInspectionPlanOperation(String name) {
        System.out.println(name);
        tf_inspectionType.setText(name);
    }
}
