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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc_model.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_Application_DocumentController implements Initializable {
    
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
    private Button btn_load_customer;
    @FXML
    private TextField tf_customer_name;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXDatePicker tf_datepicker;
    
        public static AnchorPane rootP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/mvc_view_application/SidePanelContent.fxml"));
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
    
    @FXML
    private void btn_load_customer_handler(ActionEvent event) {
        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_Customer_Document.fxml"));
//            root = FXMLLoader.load(getClass().getResource("/mvc_view_application/FXML_Customer_Document.fxml"));
            root = (Parent) loader.load();
            FXML_Customer_DocumentController controller = (FXML_Customer_DocumentController) loader.getController();
            controller.setReference(this);
            Stage customerStage = new Stage();
            Scene scene = new Scene(root);
            customerStage.setScene(scene);
            customerStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setCustomername(String name) {
        System.out.println(name);
        tf_customer_name.setText(name);
    }
    
}
