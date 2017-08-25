/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mvc_model_sqlconnector.DBConnection;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_Application_DocumentController extends StackPane {

    @FXML
    private AnchorPane rootPane;
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

    private static FXML_Application_DocumentController instance;
    private static Stage inspPlanOpStage;
    private static Stage customerStage;

    protected DBConnection connection;

    private FXML_Application_DocumentController home;
    private FXML_SidePanel_DocumentController sideMenu;
    private CustomerController customer;

    public CustomerController getCustomer() {
        return customer;
    }
    private FXML_InspPlanOp_DocumentController inspectionPlanOperations;

    public FXML_InspPlanOp_DocumentController getInspectionPlanOperations() {
        return inspectionPlanOperations;
    }

    /**
     * Statische Methode 'getInstance()Ä liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     *
     * @return
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
            home = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, "Unable to load FXML_Application_Document.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

        this.sideMenu = FXML_SidePanel_DocumentController.getInstance(); //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
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

        this.customer = CustomerController.getInstance();
//        customer.setOnMouseClicked(e -> System.out.println("MouseClick: customer"));
//        customer.setPickOnBounds(false);
        this.inspectionPlanOperations = FXML_InspPlanOp_DocumentController.getInstance();
        
        sideMenu.setReference(this);

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

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
        FXML_Application_DocumentController.customerStage = customerStage;
    }

    public Stage getInspPlanOpStage() {
        return inspPlanOpStage;
    }

    public void setInspPlanOpStage(Stage inspPlanOpStage) {
        FXML_Application_DocumentController.inspPlanOpStage = inspPlanOpStage;
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
