/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
    private TextField tf_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tf_date.setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    }

}
