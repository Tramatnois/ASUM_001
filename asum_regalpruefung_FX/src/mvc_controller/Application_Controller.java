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
public class Application_Controller extends StackPane {

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

    private static Application_Controller instance;
    private static Stage inspPlanOpStage;
    private static Stage customerStage;

    protected DBConnection connection;

    private Application_Controller home;
    private SidePanel_Controller sideMenu;
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
        Application_Controller.customerStage = customerStage;
    }

    public Stage getInspPlanOpStage() {
        return inspPlanOpStage;
    }

    public void setInspPlanOpStage(Stage inspPlanOpStage) {
        Application_Controller.inspPlanOpStage = inspPlanOpStage;
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
