package mvc_controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FXML_SidePanel_DocumentController extends AnchorPane {

    @FXML
    private JFXButton btn_show_Customer;
    @FXML
    private JFXButton btn_show_Inspections;
    @FXML
    private JFXButton btn2;
    @FXML
    private JFXButton btn3;
    @FXML
    private JFXButton btn_exit;
    @FXML
    private JFXButton btn4;
    @FXML
    private JFXButton btn5;

    private FXML_Application_DocumentController fxml_application_controller;
    private static FXML_SidePanel_DocumentController instance;

    /**
     * Statische Methode 'getInstance()Ä liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     */
    public synchronized static FXML_SidePanel_DocumentController getInstance() {
        if (instance == null) {
            instance = new FXML_SidePanel_DocumentController();
        }
        return instance;
    }

    private FXML_SidePanel_DocumentController() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_SidePanel_Document.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, "Unable to load FXML_SidePanel_Document.fxml", ex);
        }
        initialize();
    }

    private void initialize() {
//        

    }

    public void setReference(FXML_Application_DocumentController controller) {
        this.fxml_application_controller = controller;
    }

    @FXML
    void btn_show_Customer_handler(ActionEvent event) {
        this.fxml_application_controller.getContentPane().getChildren().clear();
        this.fxml_application_controller.getContentPane().getChildren().add(fxml_application_controller.getCustomer());
        this.fxml_application_controller.getDrawerSlideMenue().close();
    }

    @FXML
    void btn_show_Inspections_handler(ActionEvent event) {
        this.fxml_application_controller.getContentPane().getChildren().clear();
        this.fxml_application_controller.getContentPane().getChildren().add(fxml_application_controller.getInspectionPlanOperations());
        this.fxml_application_controller.getDrawerSlideMenue().close();
    }

    @FXML
    private void changeColor(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
//        System.out.println(btn.getText());
//        switch(btn.getText())
//        {
//            case "text1":FXMLDocumentController.rootP.setStyle("-fx-background-color:#00FF00");
//                break;
//            case "text2":FXMLDocumentController.rootP.setStyle("-fx-background-color:#0000FF");
//                break;
//            case "text3":FXMLDocumentController.rootP.setStyle("-fx-background-color:#FF0000");
//                break;
//        }
    }

    @FXML
    void btn_exit_handler(ActionEvent event)  {
        System.exit(0);
    }

}
