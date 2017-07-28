/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import mvc_model.CustomerDTO;
import mvc_model.InspectionPlanOperationDAO;
import mvc_model.InspectionPlanOperationDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_InspPlanOp_DocumentController implements Initializable {

    @FXML
    private JFXTreeTableView<InspectionPlanOperationDTO> tbl_view_inspplan_operation;

    @FXML
    private JFXTextField tf_view_inspplan_operation_location;

    private ObservableList<InspectionPlanOperationDTO> data;
    @FXML
    private JFXDatePicker tf_datepicker;

    private FXML_Application_DocumentController fxml_application_controller;
    @FXML
    private AnchorPane ap_inspplan_operation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            JFXTreeTableColumn<InspectionPlanOperationDTO, Integer> inspection_id = new JFXTreeTableColumn<>("ID");
            inspection_id.setPrefWidth(50);
//            inspection_id.setPrefWidth(Control.USE_COMPUTED_SIZE);
            inspection_id.getStyleClass().add("col_inspection_id");
            inspection_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());

            JFXTreeTableColumn<InspectionPlanOperationDTO, String> inspection = new JFXTreeTableColumn<>("Prüfplan");
            inspection.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getDescriptionProperty());

            //prepare column: KundenID
            JFXTreeTableColumn<InspectionPlanOperationDTO, Integer> customer_id = new JFXTreeTableColumn<>("KundenID");
            customer_id.getStyleClass().add("col_customer_id");
            customer_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());

            //prepare column: Kundenname
            JFXTreeTableColumn<InspectionPlanOperationDTO, String> customer = new JFXTreeTableColumn<>("Kundenname");
            customer.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getNameProperty());

            data = FXCollections.observableArrayList();

            for (InspectionPlanOperationDTO inspplan_op : new InspectionPlanOperationDAO().selectAllInspectionPlanOperations()) {
                data.add(inspplan_op);
            }

            final TreeItem<InspectionPlanOperationDTO> root;
            root = new RecursiveTreeItem<InspectionPlanOperationDTO>(data, RecursiveTreeObject::getChildren);

            tbl_view_inspplan_operation.getColumns().setAll(inspection_id, inspection, customer_id, customer);
            tbl_view_inspplan_operation.setRoot(root);
            tbl_view_inspplan_operation.setShowRoot(false);

        } catch (SQLException ex) {
            Logger.getLogger(FXML_InspPlanOp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setReference(FXML_Application_DocumentController controller) {
        this.fxml_application_controller = controller;
    }
    
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    private void tbl_view_inspplan_operation_handler(MouseEvent event) {
        TreeItem<InspectionPlanOperationDTO> inspplan_operation = tbl_view_inspplan_operation.getSelectionModel().getSelectedItem();
        Date date = inspplan_operation.getValue().getDate();

        tf_datepicker.setValue(LOCAL_DATE(date.toString()));
        String location = tbl_view_inspplan_operation.getSelectionModel().getSelectedItem().getValue().getLocation();
        tf_view_inspplan_operation_location.setText(location);

        if (event.getClickCount() == 2) {
            System.out.println("doubleclick");
            this.fxml_application_controller.setInspectionPlanOperation(inspplan_operation.getValue().getDescription());
            Stage stage = (Stage) ap_inspplan_operation.getScene().getWindow();
            stage.close();

        }

    }

}
