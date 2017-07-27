/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import mvc_model.InspectionPlanOperationDAO;
import mvc_model.InspectionPlanOperationDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_InspPlanOp_DocumentController implements Initializable {

    @FXML
    private TreeTableView<InspectionPlanOperationDTO> tbl_view_inspplan_operation;
    @FXML
    private TreeTableColumn<InspectionPlanOperationDTO, Integer> tbl_view_inspplan_operation_cust_id;
    @FXML
    private TreeTableColumn<InspectionPlanOperationDTO, String> tbl_view_inspplan_operation_cust_name;
    @FXML
    private TreeTableColumn<InspectionPlanOperationDTO, Integer> tbl_view_inspplan_operation_id;
    @FXML
    private TreeTableColumn<InspectionPlanOperationDTO, String> tbl_view_inspplan_operation_descr;
    @FXML
    private JFXTextField tf_view_inspplan_operation_date;
    @FXML
    private JFXTextField tf_view_inspplan_operation_location;

    private ObservableList<InspectionPlanOperationDTO> data;

    private FXML_Application_DocumentController fxml_application_controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
//            data = FXCollections.observableArrayList();
            TreeItem<InspectionPlanOperationDTO> root = new TreeItem<>(new InspectionPlanOperationDTO());
            for (InspectionPlanOperationDTO inspplan_operation : new InspectionPlanOperationDAO().selectAllInspectionPlanOperations()) {
//                data.add(inspplan_operation);
                root.getChildren().addAll(new TreeItem<>(inspplan_operation));
            }

            tbl_view_inspplan_operation_cust_id.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
            tbl_view_inspplan_operation_cust_name.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
            tbl_view_inspplan_operation_id.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
            tbl_view_inspplan_operation_descr.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));

            tbl_view_inspplan_operation.setRoot(root);

        } catch (SQLException ex) {
            Logger.getLogger(FXML_InspPlanOp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
