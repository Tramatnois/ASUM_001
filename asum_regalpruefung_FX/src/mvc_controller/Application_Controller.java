/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mvc_model.CustomerDAO;
import mvc_model.CustomerDTO;
import mvc_model_sqlconnector.DBConnection;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class Application_Controller extends StackPane {

    @FXML
    private AnchorPane rootPane;

    /*Sub Functions Customer*/
    @FXML
    private GridPane paneSubFun_Customer;
    @FXML
    private JFXButton btn_edit_customer;
    @FXML
    private JFXButton btn_add_customer;
    @FXML
    private JFXButton btn_del_customer;

    /*Sub Functions InspectionPlan*/
    @FXML
    private GridPane paneSubFun_InspPlanOp;
    @FXML
    private GridPane paneSubFun_InspPlanCharGroup;
    @FXML
    private JFXButton btn_edit_inspPlanOp;
    @FXML
    private JFXButton btn_new_inspPlanOp;
    @FXML
    private JFXButton btn_del_inspPlanOp;
    @FXML
    private JFXButton btn_show_inspPlanOpResults;
    @FXML
    private JFXButton btn_show_inspPlanOpAttachments;
    @FXML
    private JFXButton btn_manage_inspPlanOp_Template;
    @FXML
    private JFXButton btn_manage_characteristic_Template;
    @FXML
    private JFXButton btn_manage_CharacterGroup_Template;

    @FXML
    private AnchorPane contentPane;

    public AnchorPane getContentPane() {
        return contentPane;
    }
    @FXML
    private JFXDrawer drawerSlideMenue;

    public JFXDrawer getDrawerSlideMenue() {
        return drawerSlideMenue;
    }
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private HBox hoxImages;

    @FXML
    private ImageView imgInfo;

    @FXML
    private ImageView imgHome;

    @FXML
    private ImageView imgExit;

    @FXML
    private TextField tf_connected;

    private static Application_Controller instance;
    private static Stage inspPlanOpStage;
    private static Stage customerStage;

    protected DBConnection connection;

    private Application_Controller home;
    private SidePanel_Controller sideMenu;
    private Customer_Controller customer_Controller;
    private AddCustomer_Controller addCustomer_Controller;
    private ChangeCustomer_Controller changeCustomer_Controller;
    private CustomerSelect_Controller customerSelect_Controller;
    private InspPlanOp_Controller inspectionPlanOperations_Controller;
    private InspPlanOpSelect_Controller inspPlanOpSelect_Controller;
    private InspPlanTemplateManagement_Controller inspPlanTemplateManagement_Controller;
    private ResultsRecording_Controller resultsRecording_Controller;

    private HamburgerBackArrowBasicTransition hamburger_transition;

    /**
     * Statische Methode 'getInstance()Ä liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     *
     * @return
     */
    public synchronized static Application_Controller getInstance() {
        if (instance == null) {
            instance = new Application_Controller();
        }
        return instance;
    }

    /**
     * Konstruktor ist privat, Klasse darf nicht von außen instanziiert werden.
     */
    private Application_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/Application.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
// Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            home = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Application_Controller.class.getName()).log(Level.SEVERE, "Unable to load Application.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

// init buttons
        btn_add_customer.setTooltip(new Tooltip("Neuen Kunden anlegen"));
        btn_edit_customer.setTooltip(new Tooltip("Kunden bearbeiten"));
        btn_del_customer.setTooltip(new Tooltip("Kunden deaktivieren"));

        this.sideMenu = SidePanel_Controller.getInstance();
        this.sideMenu.setApplication_controller(this);
        drawerSlideMenue.setSidePane(sideMenu);

//        for (Node node : sideMenu.getChildren()){
//            if (node.getAccessibleText() != null) {
//                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (eventHandler) -> {
//                    switch (node.getAccessibleText()) {
//                        case "showCustomer":
//                            contentPane.getChildren().clear();
//                            contentPane.getChildren().add((Node) customer_Controller);
//                            break;
//                    }
//                });
//            }
//        }
        this.customer_Controller = Customer_Controller.getInstance();
        this.customer_Controller.setApplication_controller(this);
        this.addCustomer_Controller = AddCustomer_Controller.getInstance();
        this.addCustomer_Controller.setApplication_controller(this);
        this.changeCustomer_Controller = ChangeCustomer_Controller.getInstance();
        this.changeCustomer_Controller.setApplication_controller(this);
        this.customerSelect_Controller = CustomerSelect_Controller.getInstance();
        this.customerSelect_Controller.setApplication_controller(this);
        this.inspPlanTemplateManagement_Controller = InspPlanTemplateManagement_Controller.getInstance();
        this.inspPlanTemplateManagement_Controller.setApplication_controller(this);
        this.inspectionPlanOperations_Controller = InspPlanOp_Controller.getInstance();
        this.inspectionPlanOperations_Controller.setApplication_controller(this);
        this.inspPlanOpSelect_Controller = InspPlanOpSelect_Controller.getInstance();
        this.inspPlanOpSelect_Controller.setApplication_controller(this);
        this.resultsRecording_Controller = ResultsRecording_Controller.getInstance();
        this.resultsRecording_Controller.setApplication_controller(this);

        hamburger_transition = new HamburgerBackArrowBasicTransition(hamburger);
        hamburger_transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            hamburger_transition.setRate(hamburger_transition.getRate() * -1);
            hamburger_transition.play();

            if (drawerSlideMenue.isShown()) {
                drawerSlideMenue.close();
            } else {
                drawerSlideMenue.open();
            }
        });
        connection = DBConnection.getInstance();
        DropShadow dropShadow = new DropShadow();
        tf_connected.setText(connection.getHost() + "." + connection.getDatabase());
//            dropShadow.setColor(Color.GREEN);
        tf_connected.setEffect(dropShadow);
        tf_connected.setStyle("-fx-background-color: #009688;-fx-text-fill: white;");

    }

    //Set selected node to a content holder
    public void setNode(String nodeName) {
        Node node = null;
// init content pane        
        contentPane.getChildren().clear();
// hide paneSubFunctions
        paneSubFun_Customer.setVisible(false);
        paneSubFun_InspPlanOp.setVisible(false);
// set given node for contentPane        
        switch (nodeName) {
            case "CUSTOMER":
                node = customer_Controller;
                paneSubFun_Customer.setVisible(true);
                break;
            case "ADD_CUSTOMER":
                node = addCustomer_Controller;
                paneSubFun_Customer.setVisible(true);
                break;
            case "CHANGE_CUSTOMER":
                node = changeCustomer_Controller;
                changeCustomer_Controller.prefill();
                paneSubFun_Customer.setVisible(true);
                break;
            case "INSPPLAN_MANAGEMENT":
                node = inspPlanTemplateManagement_Controller;
                paneSubFun_InspPlanCharGroup.setVisible(true);
                break;
            case "INSPPLANOP":
                node = inspectionPlanOperations_Controller;
                paneSubFun_InspPlanOp.setVisible(true);
                break;
            case "RESULTS_RECORDING":
                node = resultsRecording_Controller;
//                paneSubFun_InspPlanOp.setVisible(true);
                break;

        }
        contentPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();

// hide sidemenu
        drawerSlideMenue.close();
// switch hamburger status
        hamburger_transition.setRate(hamburger_transition.getRate() * -1);
        hamburger_transition.play();
    }

    public HamburgerBackArrowBasicTransition getHamburger_transition() {
        return hamburger_transition;
    }

    public Customer_Controller getCustomer() {
        return customer_Controller;
    }

    public InspPlanOp_Controller getInspectionPlanOperations() {
        return inspectionPlanOperations_Controller;
    }

    public ResultsRecording_Controller getResultsRecording_Controller() {
        return resultsRecording_Controller;
    }

    public void setResultsRecording_Controller(ResultsRecording_Controller resultsRecording_Controller) {
        this.resultsRecording_Controller = resultsRecording_Controller;
    }

    public Stage getCustomerStage() {
        return customerStage;
    }

    public void setCustomerStage(Stage customerStage) {
        Application_Controller.customerStage = customerStage;
    }

    public Stage getInspPlanOpStage() {
        return inspPlanOpStage;
    }

    public void setInspPlanOpStage(Stage inspPlanOpStage) {
        Application_Controller.inspPlanOpStage = inspPlanOpStage;
    }

    public InspPlanOpSelect_Controller getInspPlanOpSelect_Controller() {
        return inspPlanOpSelect_Controller;
    }

    public CustomerSelect_Controller getCustomerSelect_Controller() {
        return customerSelect_Controller;
    }

    /*Sub Functions Customer*/
    @FXML
    void btn_edit_customer_handler(ActionEvent event) {
//        if (getCustomer().getTbl_view_customer().isEditable()) {
//            getCustomer().getTbl_view_customer().setEditable(false);
//        } else {
//            getCustomer().getTbl_view_customer().setEditable(true);
//        }
        System.out.println("change/display customer");
        setNode("CHANGE_CUSTOMER");
    }

    @FXML
    void btn_add_customer_handler(ActionEvent event) {
        System.out.println("add customer");
        setNode("ADD_CUSTOMER");

    }

    @FXML
    void btn_del_customer_handler(ActionEvent event) {
        CustomerDTO customer;
        CustomerDAO customer_model = new CustomerDAO();
        TreeTableViewSelectionModel<CustomerDTO> selectionModel = getCustomer().getTbl_view_customer().getSelectionModel();

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

    /*Sub Functions InspectionPlanOperation*/
    @FXML
    void btn_new_inspPlanOp_handler(ActionEvent event) {

    }
    
    @FXML
    void btn_edit_inspPlanOp_handler(ActionEvent event) {

    }
    
    @FXML
    void btn_del_inspPlanOp_handler(ActionEvent event) {

    }
    /*Sub Functions InspectionPlanTemplate*/
    @FXML
    void btn_manage_inspPlanOp_Template_handler(ActionEvent event) {
        System.out.println("manage InspectionPlan Template");
        setNode("INSPPLAN_MANAGEMENT");
    }

    @FXML
    void btn_manage_characteristic_Template_handler(ActionEvent event) {

    }
    
    @FXML
    void btn_manage_CharacterGroup_Template_handler(ActionEvent event) {

    }

    @FXML
    void btn_show_inspPlanOpResults_handler(ActionEvent event) {

    }

    @FXML
    void btn_show_inspPlanOpAttachments_handler(ActionEvent event) {

    }

    @FXML
    private void btn_exit_handler(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void btn_goHome_handler(MouseEvent event) {

    }

    @FXML
    private void btn_showInfo_handler(MouseEvent event) {

    }

}
