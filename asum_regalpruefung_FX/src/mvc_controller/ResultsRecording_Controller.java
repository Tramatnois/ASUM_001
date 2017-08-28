/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mvc_model.InspectionOperationCommentDAO;
import mvc_model.InspectionResultDAO;
import mvc_model.InspectionResultDTO;
import mvc_model.InspectionOperationCommentDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class ResultsRecording_Controller extends AnchorPane {

    @FXML
    private AnchorPane holderAnchor;

    @FXML
    private JFXTreeTableView<InspectionResultDTO> tbl_view_resultsRecording;

    @FXML
    private Label lblDescription;

    @FXML
    private JFXDatePicker tf_datepicker;
    @FXML
    private JFXRadioButton rb_inspResult_Yes;
    @FXML
    private JFXRadioButton rb_inspResult_Init;
    @FXML
    private JFXRadioButton rb_inspResult_No;
    @FXML
    private JFXTextArea tfa_results_comments;

    @FXML
    private Label lblZipCode;

    @FXML
    private ToggleGroup inspectionResult;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblNorm;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblStorageRack;

    @FXML
    private JFXCheckBox cb_activeUser;

    @FXML
    private Label lblFee;

    @FXML
    private Label lblPaid;

    @FXML
    private Label lblBalance;

    @FXML
    private AnchorPane fabPane;

    @FXML
    private Label fabEdit;

    @FXML
    private JFXTextField tf_customerID;

    @FXML
    private JFXTextField tf_customer;

    @FXML
    private JFXButton btn_load_customer;

    @FXML
    private JFXTextField tf_inspectionID;

    @FXML
    private JFXTextField tf_inspectionDescr;

    @FXML
    private JFXButton btn_load_inspection;

    private ObservableList<InspectionResultDTO> data;
    private Application_Controller application_controller;

    private static ResultsRecording_Controller instance;

    public synchronized static ResultsRecording_Controller getInstance() {
        if (instance == null) {
            instance = new ResultsRecording_Controller();
        }
        return instance;
    }

    private ResultsRecording_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/ResultsRecording.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ResultsRecording_Controller.class.getName()).log(Level.SEVERE, "Unable to load ResultsRecording.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

// init radio buttons. I dont know, why it is not possible to style them with CSS        
        try {
            //prepare column: PositionID
            JFXTreeTableColumn<InspectionResultDTO, Integer> positionID = new JFXTreeTableColumn<>("POS.");
            positionID.setPrefWidth(50);
            positionID.getStyleClass().add("col_positionID");
            positionID.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionResultDTO, Integer> param) -> param.getValue().getValue().getCharacteristicOperation().getPositionProperty().asObject());

//prepare column: CharacterristicOperation
            JFXTreeTableColumn<InspectionResultDTO, String> characteristic = new JFXTreeTableColumn<>("Prüfmerkmal");
            characteristic.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionResultDTO, String> param) -> param.getValue().getValue().getCharacteristicOperation().getDescriptionProperty());
            characteristic.setPrefWidth(600);

////prepare column: KundenID
//            JFXTreeTableColumn<InspectionPlanOperationDTO, Integer> customer_id = new JFXTreeTableColumn<>("KundenID");
//            customer_id.getStyleClass().add("col_customer_id");
//            customer_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());
//            customer_id.setPrefWidth(100);
////prepare column: Kundenname
//            JFXTreeTableColumn<InspectionPlanOperationDTO, String> customer = new JFXTreeTableColumn<>("Kunde");
//            customer.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getCustomer().getNameProperty());
//            customer.setPrefWidth(250);
////prepare column: Status
//            JFXTreeTableColumn<InspectionPlanOperationDTO, String> status = new JFXTreeTableColumn<>("Status");
//            status.getStyleClass().add("col_status");
//            status.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getInspectionPlanOperationStatus().getDescriptionProperty());
//            status.setPrefWidth(100);
//            
            data = FXCollections.observableArrayList();
//            
            for (InspectionResultDTO inspResult : new InspectionResultDAO().selectAllInspResults()) {
                data.add(inspResult);
            }
//            
            final TreeItem<InspectionResultDTO> root;
            root = new RecursiveTreeItem<InspectionResultDTO>(data, RecursiveTreeObject::getChildren);

            tbl_view_resultsRecording.getColumns().setAll(positionID, characteristic);
            tbl_view_resultsRecording.setRoot(root);
            tbl_view_resultsRecording.setShowRoot(false);
            tbl_view_resultsRecording.getStyleClass().add("inspResultsTable");
//
//// add filter for customer name
//            tf_filter.textProperty().addListener(new ChangeListener<String>() {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    tbl_view_inspplan_operation.setPredicate(new Predicate<TreeItem<InspectionPlanOperationDTO>>() {
//                        @Override
//                        public boolean test(TreeItem<InspectionPlanOperationDTO> inspPlanOp) {
//                            Boolean flag = false;
////                            Boolean flag = customer.getValue().getName().contains(newValue) | customer.getValue().getId().toString().contains(newValue);
//                            if (rb_inspPlanOpID.isSelected()) {
//                                flag = inspPlanOp.getValue().getId().toString().contains(newValue);
//                            }
//                            if (rb_inspPlanOpDescr.isSelected()) {
//                                flag = inspPlanOp.getValue().getDescription().contains(newValue);
//                            }
//                            if (rb_customerID.isSelected()) {
//                                flag = inspPlanOp.getValue().getCustomer().getId().toString().contains(newValue);
//                            }
//                            if (rb_customerName.isSelected()) {
//                                flag = inspPlanOp.getValue().getCustomer().getName().contains(newValue);
//                            }
//                            if (rb_inspPlanOpStatus.isSelected()) {
//                                flag = inspPlanOp.getValue().getInspectionPlanOperationStatus().getDescription().contains(newValue);
//                            }
//                            return flag;
//                        }
//                    });
//                }
//            });
        } catch (SQLException ex) {
            Logger.getLogger(ResultsRecording_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setApplication_controller(Application_Controller controller) {
        this.application_controller = controller;
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    void btn_load_customer_handler(ActionEvent event) {

    }

    @FXML
    void btn_load_inspection_handler(ActionEvent event) {

    }

    @FXML
    void tbl_view_resultsRecording_handler(MouseEvent event) {
        try {
            TreeItem<InspectionResultDTO> inspResult = tbl_view_resultsRecording.getSelectionModel().getSelectedItem();
            tf_datepicker.setValue(LOCAL_DATE(inspResult.getValue().getInspectionOperation().getDate().toString()));

            switch (inspResult.getValue().getResult()) {
                case "0":
                    rb_inspResult_No.setSelected(true);
                    break;
                case "1":
                    rb_inspResult_Yes.setSelected(true);
                    break;
                default:
                    rb_inspResult_Init.setSelected(true);
                    break;
            }

            InspectionOperationCommentDTO inspComment = new InspectionOperationCommentDTO();
            InspectionOperationCommentDAO inspCommentDAO = new InspectionOperationCommentDAO();

            switch (inspResult.getValue().getResult()) {
                case "0":
                    inspComment = inspCommentDAO.selectSingleById(1);
                    tfa_results_comments.setText(inspComment.getComment());
                    break;
                case "1":
//                    inspComment = inspCommentDAO.selectSingleById(2);
                    tfa_results_comments.clear();
                    break;
            }

            

//        lblDescription.setText(inspResult.getValue().getDescription());
//        tf_datepicker.setValue(LOCAL_DATE(inspResult.getValue().getDate().toString()));
//        lblNorm.setText(inspResult.getValue().getNorm());
//        lblLocation.setText(inspResult.getValue().getLocation());
//        lblStorageRack.setText(inspResult.getValue().getStorageRack());
//        if (event.getClickCount() == 2) {
//            System.out.println("doubleclick");
//            this.fxml_application_controller.setInspectionPlanOperation(inspPlanOp.getValue().getDescription());
////            Stage stage = (Stage) ap_inspplan_operation.getScene().getWindow();
////            stage.close();
//            this.fxml_application_controller.drawerContentView.close();
//
//        }
//        cb_activeUser.setSelected(customer.getValue().getActiveBoolean());
        } catch (SQLException ex) {
            Logger.getLogger(ResultsRecording_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btn_searchInspPlanOp_handler(ActionEvent event) {
//        FilterableTreeItem<CustomerDTO> root = tbl_view_customer.getRoot();
//
//        root.predicateProperty().bind(Bindings.createObjectBinding(() -> {
//            if (tf_filter.getText() == null || tf_filter.getText().isEmpty()) {
//                return null;
//            }
//            return TreeItemPredicate.create(customer -> customer.toString().contains(tf_filter.getText()));
//        }, tf_filter.textProperty()));
//
//        JFXTreeTableView<CustomerDTO> treeView;
//        treeView = new JFXTreeTableView<>(root);
//        tbl_view_customer.setRoot(root);
    }
}
