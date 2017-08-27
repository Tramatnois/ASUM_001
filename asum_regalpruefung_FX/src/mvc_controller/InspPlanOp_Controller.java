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
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.paint.Color;
import mvc_model.InspectionPlanOperationDAO;
import mvc_model.InspectionPlanOperationDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class InspPlanOp_Controller extends AnchorPane {

    @FXML
    private AnchorPane holderAnchor;

    @FXML
    private JFXTreeTableView<InspectionPlanOperationDTO> tbl_view_inspplan_operation;

    @FXML
    private JFXTextField tf_filter;
    @FXML
    private ToggleGroup filter;
    @FXML
    private JFXRadioButton rb_inspPlanOpID;
    @FXML
    private JFXRadioButton rb_inspPlanOpDescr;
    @FXML
    private JFXRadioButton rb_customerID;
    @FXML
    private JFXRadioButton rb_customerName;
    @FXML
    private JFXButton btn_searchInspPlanOp;

    @FXML
    private Label lblDescription;
    @FXML
    private JFXDatePicker tf_datepicker;    
    @FXML
    private Label lblNorm;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblStorageRack;
    
    

    

    @FXML
    private JFXCheckBox cb_activeUser;

    @FXML
    private AnchorPane fabPane;

    @FXML
    private Label fabEdit;

    private ObservableList<InspectionPlanOperationDTO> data;
    private FXML_StorageRackInsp_DocumentController fxml_application_controller;
    private static InspPlanOp_Controller instance;

    public synchronized static InspPlanOp_Controller getInstance() {
        if (instance == null) {
            instance = new InspPlanOp_Controller();
        }
        return instance;
    }

    private InspPlanOp_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/InspPlanOp.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, "Unable to load InspPlanOp.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

// init radio buttons. I dont know, why it is not possible to style them with CSS        
        rb_inspPlanOpID.setSelectedColor(Color.CHARTREUSE);
        rb_inspPlanOpID.setUnSelectedColor(Color.WHITESMOKE);
        rb_inspPlanOpDescr.setSelectedColor(Color.CHARTREUSE);
        rb_inspPlanOpDescr.setUnSelectedColor(Color.WHITESMOKE);
        rb_customerID.setSelectedColor(Color.CHARTREUSE);
        rb_customerID.setUnSelectedColor(Color.WHITESMOKE);
        rb_customerName.setSelectedColor(Color.CORAL);
        rb_customerName.setUnSelectedColor(Color.WHITESMOKE);
//        tf_datepicker.setPrefHeight(12);

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

            for (InspectionPlanOperationDTO inspplan_op : new InspectionPlanOperationDAO().selectAllInspectionPlanOperations()) {
                data.add(inspplan_op);
            }

            final TreeItem<InspectionPlanOperationDTO> root;
            root = new RecursiveTreeItem<InspectionPlanOperationDTO>(data, RecursiveTreeObject::getChildren);

            tbl_view_inspplan_operation.getColumns().setAll(inspection_id, inspection, customer_id, customer, status);
            tbl_view_inspplan_operation.setRoot(root);
            tbl_view_inspplan_operation.setShowRoot(false);

            tbl_view_inspplan_operation.getColumns().setAll(inspection_id, inspection, customer_id, customer, status);
            tbl_view_inspplan_operation.setRoot(root);
            tbl_view_inspplan_operation.setShowRoot(false);
            tbl_view_inspplan_operation.getStyleClass().add("InspPlanOpTTable");

// add filter for customer name
            tf_filter.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    tbl_view_inspplan_operation.setPredicate(new Predicate<TreeItem<InspectionPlanOperationDTO>>() {
                        @Override
                        public boolean test(TreeItem<InspectionPlanOperationDTO> inspPlanOp) {
                            Boolean flag = false;
//                            Boolean flag = customer.getValue().getName().contains(newValue) | customer.getValue().getId().toString().contains(newValue);
                            if (rb_inspPlanOpID.isSelected()) {
                                flag = inspPlanOp.getValue().getId().toString().contains(newValue);
                            }
                            if (rb_inspPlanOpDescr.isSelected()) {
                                flag = inspPlanOp.getValue().getDescription().contains(newValue);
                            }
                            if (rb_customerID.isSelected()) {
                                flag = inspPlanOp.getValue().getCustomer().getId().toString().contains(newValue);
                            }
                            if (rb_customerName.isSelected()) {
                                flag = inspPlanOp.getValue().getCustomer().getName().contains(newValue);
                            }
                            return flag;
                        }
                    });
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(InspPlanOp_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    void tbl_view_inspplan_operation_handler(MouseEvent event) {
        TreeItem<InspectionPlanOperationDTO> inspPlanOp = tbl_view_inspplan_operation.getSelectionModel().getSelectedItem();
        lblDescription.setText(inspPlanOp.getValue().getDescription());
        tf_datepicker.setValue(LOCAL_DATE(inspPlanOp.getValue().getDate().toString()));
        lblNorm.setText(inspPlanOp.getValue().getNorm());
        lblLocation.setText(inspPlanOp.getValue().getLocation());
        lblStorageRack.setText(inspPlanOp.getValue().getStorageRack());

//        cb_activeUser.setSelected(customer.getValue().getActiveBoolean());

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
