/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    private JFXButton btn_view_inspPlanOp_Template;
    @FXML
    private JFXButton btn_change_inspPlanOp_Template;
    @FXML
    private JFXButton btn_add_inspPlanOp_Template;
    @FXML
    private JFXButton btn_del_inspPlanOp_Template;
    @FXML
    private JFXButton btn_view_characteristic_Template;
    @FXML
    private JFXButton btn_change_characteristic_Template;
    @FXML
    private JFXButton btn_add_characteristic_Template;
    @FXML
    private JFXButton btn_del_characteristic_Template;

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
    private Customer_Controller customer;
    private InspPlanOp_Controller inspectionPlanOperations;

    private HamburgerBackArrowBasicTransition hamburger_transition;

    public HamburgerBackArrowBasicTransition getHamburger_transition() {
        return hamburger_transition;
    }

    public Customer_Controller getCustomer() {
        return customer;
    }

    public InspPlanOp_Controller getInspectionPlanOperations() {
        return inspectionPlanOperations;
    }

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_Application_Document.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
// Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            home = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Application_Controller.class.getName()).log(Level.SEVERE, "Unable to load FXML_Application_Document.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

// init buttons
        btn_add_customer.setTooltip(new Tooltip("Neuen Kunden anlegen"));
        btn_edit_customer.setTooltip(new Tooltip("Kunden bearbeiten"));
        btn_del_customer.setTooltip(new Tooltip("Kunden deaktivieren"));

        this.sideMenu = SidePanel_Controller.getInstance(); //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        sideMenu.setOnMouseClicked(e -> System.out.println("MouseClick: sideMenu"));
        drawerSlideMenue.setSidePane(sideMenu);

//        for (Node node : sideMenu.getChildren()){
//            if (node.getAccessibleText() != null) {
//                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (eventHandler) -> {
//                    switch (node.getAccessibleText()) {
//                        case "showCustomer":
//                            contentPane.getChildren().clear();
//                            contentPane.getChildren().add((Node) customer);
//                            break;
//                    }
//                });
//            }
//        }
        this.customer = Customer_Controller.getInstance();
//        customer.setOnMouseClicked(e -> System.out.println("MouseClick: customer"));
//        customer.setPickOnBounds(false);
        this.inspectionPlanOperations = InspPlanOp_Controller.getInstance();

        sideMenu.setReference(this);

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
                node = customer;
                paneSubFun_Customer.setVisible(true);
                break;
            case "INSPPLANOP":
                node = inspectionPlanOperations;
                paneSubFun_InspPlanOp.setVisible(true);
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

    /*Sub Functions Customer*/
    @FXML
    void btn_edit_customer_handler(ActionEvent event) {

    }

    @FXML
    void btn_add_customer_handler(ActionEvent event) {

    }

    @FXML
    void btn_del_customer_handler(ActionEvent event) {

    }

    /*Sub Functions InspectionPlanOperation*/
    @FXML
    void btn_edit_inspPlanOp_handler(ActionEvent event) {

    }

    @FXML
    void btn_view_inspPlanOp_Template_handler(ActionEvent event) {

    }

    @FXML
    void btn_change_inspPlanOp_Template_handler(ActionEvent event) {

    }

    @FXML
    void btn_add_inspPlanOp_Template_handler(ActionEvent event) {

    }

    @FXML
    void btn_del_inspPlanOp_Template_handler(ActionEvent event) {

    }

    @FXML
    void btn_view_characteristic_Template_handler(ActionEvent event) {

    }

    @FXML
    void btn_change_characteristic_Template_handler(ActionEvent event) {

    }

    @FXML
    void btn_add_characteristic_Template_handler(ActionEvent event) {

    }

    @FXML
    void btn_del_characteristic_Template_handler(ActionEvent event) {

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
