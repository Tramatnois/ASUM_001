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
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc_model.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_Application_DocumentController extends AnchorPane {

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
    public JFXDrawer drawerCustomerView;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDatePicker tf_datepicker;
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

    private static FXML_Application_DocumentController instance;
    private static Stage inspPlanOpStage;
    private static Stage customerStage;

    /**
     * Statische Methode 'getInstance()Ä liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     */
    public synchronized static FXML_Application_DocumentController getInstance() {
        if (instance == null) {
            instance = new FXML_Application_DocumentController();
        }
        return instance;
    }

    /**
     * Konstruktor ist privat, Klasse darf nicht von außen instanziiert werden.
     */
    private FXML_Application_DocumentController() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_Application_Document.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, "Unable to load FXML_Application_Document.fxml", ex);
        }
        initialize();
    }

    private void initialize() {
        try {
            HBox box = FXMLLoader.load(getClass().getResource("/mvc_view_application/SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public Stage getCustomerStage() {
        return customerStage;
    }

    public void setCustomerStage(Stage customerStage) {
        FXML_Application_DocumentController.customerStage = customerStage;
    }

    public Stage getInspPlanOpStage() {
        return inspPlanOpStage;
    }

    public void setInspPlanOpStage(Stage inspPlanOpStage) {
        FXML_Application_DocumentController.inspPlanOpStage = inspPlanOpStage;
    }

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//
//        try {
//            VBox box = FXMLLoader.load(getClass().getResource("/mvc_view_application/SidePanelContent.fxml"));
//            drawer.setSidePane(box);
//        } catch (IOException ex) {
//            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
//        transition.setRate(-1);
//        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
//            transition.setRate(transition.getRate() * -1);
//            transition.play();
//
//            if (drawer.isShown()) {
//                drawer.close();
//            } else {
//                drawer.open();
//            }
//        });
////        tf_date.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
//        tf_datepicker.setValue(LocalDate.now());
//    }
    @FXML
    private void btn_load_customer_handler(ActionEvent event) {
//        try {
//            Parent root;
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_Customer_Document.fxml"));
//            root = (Parent) loader.load();
//        loadFXML("/mvc_view_application/FXML_Customer_Document.fxml");
//// get the FXML_Customer_DocumentController and set the current FXML_Application_DocumentController           
//            FXML_Customer_DocumentController controller = (FXML_Customer_DocumentController) loader.getController();
//            controller.setReference(this);
//// get Singleton Customer Stage
//            customerStage = new Stage();
//
//            Scene scene = new Scene(root);
//            customerStage.setScene(scene);
//            customerStage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        FXML_Customer_DocumentController customerPane = FXML_Customer_DocumentController.getInstance();
        customerPane.setReference(this);

        //this.getChildren().add(customerPane);
//        if (customerStage == null) {
//            customerStage = new Stage();
//            Scene scene = new Scene(customerPane);
//            customerStage.setScene(scene);
//            customerStage.show();
//        } else {
//            if (!customerStage.isFocused()) {
//                customerStage.requestFocus();
//            }
//        }
        drawerCustomerView.setSidePane(customerPane);
        if (drawerCustomerView.isShown()) {
            drawerCustomerView.close();
        } else {
            drawerCustomerView.open();
        }

    }

    @FXML
    private void btn_show_all_inspplan_operations_handler(ActionEvent event) {
        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_InspPlanOp_Document.fxml"));
            root = (Parent) loader.load();

// get the FXML_InspPlanOp_DocumentController and set the current FXML_Application_DocumentController           
            FXML_InspPlanOp_DocumentController controller = (FXML_InspPlanOp_DocumentController) loader.getController();
            controller.setReference(this);
// get Singleton Customer Stage
            FXML_Application_DocumentController.getInstance().setInspPlanOpStage(inspPlanOpStage);
            Scene scene = new Scene(root);
            inspPlanOpStage.setScene(scene);
            inspPlanOpStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
