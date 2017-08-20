/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mvc_model.InspectionPlanOperationDAO;
import mvc_model.InspectionPlanOperationDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class FXML_InspPlanOp_DocumentController extends AnchorPane {

    @FXML
    private JFXTreeTableView<InspectionPlanOperationDTO> tbl_view_inspplan_operation;

    @FXML
    private JFXTextField tf_view_inspplan_operation_location;

    private ObservableList<InspectionPlanOperationDTO> data;
    @FXML
    private JFXDatePicker tf_datepicker;

    @FXML
    private AnchorPane ap_inspplan_operation;

    private FXML_Application_DocumentController fxml_application_controller;
    private static FXML_InspPlanOp_DocumentController instance;

    /**
     * Statische Methode 'getInstance() liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     */
    public synchronized static FXML_InspPlanOp_DocumentController getInstance() {
        if (instance == null) {
            instance = new FXML_InspPlanOp_DocumentController();
        }
        return instance;
    }

    /**
     * Konstruktor ist privat, Klasse darf nicht von außen instanziiert werden.
     */
    private FXML_InspPlanOp_DocumentController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/FXML_InspPlanOp_Document.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(FXML_Application_DocumentController.class.getName()).log(Level.SEVERE, "Unable to load FXML_Customer_Document.fxml", ex);
        }
        initialize();
    }

    private void initialize() {
        try {
//prepare column: InspectionID
            JFXTreeTableColumn<InspectionPlanOperationDTO, Integer> inspection_id = new JFXTreeTableColumn<>("ID");
            inspection_id.setPrefWidth(50);
            //inspection_id.setPrefWidth(Control.USE_COMPUTED_SIZE);
            inspection_id.getStyleClass().add("col_inspection_id");
            inspection_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());

//prepare column: Inspection
            JFXTreeTableColumn<InspectionPlanOperationDTO, String> inspection = new JFXTreeTableColumn<>("Prüfplan");
            inspection.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getDescriptionProperty());

//prepare column: KundenID
            JFXTreeTableColumn<InspectionPlanOperationDTO, Integer> customer_id = new JFXTreeTableColumn<>("KundenID");
            customer_id.getStyleClass().add("col_customer_id");
            customer_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());

//prepare column: Kundenname
            JFXTreeTableColumn<InspectionPlanOperationDTO, String> customer = new JFXTreeTableColumn<>("Kunde");
            customer.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getCustomer().getNameProperty());

//prepare column: Status
            JFXTreeTableColumn<InspectionPlanOperationDTO, String> status = new JFXTreeTableColumn<>("Status");
            status.getStyleClass().add("col_status");
            status.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getInspectionPlanOperationStatus().getDescriptionProperty());

            data = FXCollections.observableArrayList();

//            for (InspectionPlanOperationDTO inspplan_op : new InspectionPlanOperationDAO().selectAllFullLoadWithoutTemplate()) {
            for (InspectionPlanOperationDTO inspplan_op : new InspectionPlanOperationDAO().selectAllInspectionPlanOperations()) {
                data.add(inspplan_op);
            }

            final TreeItem<InspectionPlanOperationDTO> root;
            root = new RecursiveTreeItem<InspectionPlanOperationDTO>(data, RecursiveTreeObject::getChildren);

            tbl_view_inspplan_operation.getColumns().setAll(inspection_id, inspection, customer_id, customer, status);
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
//            Stage stage = (Stage) ap_inspplan_operation.getScene().getWindow();
//            stage.close();
            this.fxml_application_controller.drawerCustomerView.close();

        }

    }

}
