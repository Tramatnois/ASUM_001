/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mvc_model.InspectionPlanTemplateDAO;
import mvc_model.InspectionPlanTemplateDTO;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class InspPlanTemplateManagement_Controller extends AnchorPane {

    @FXML
    private AnchorPane holderAnchor;

    @FXML
    private JFXTreeTableView<InspectionPlanTemplateDTO> tbl_view_inspplan_overview;

    public JFXTreeTableView<InspectionPlanTemplateDTO> getTbl_view_inspplan_overview() {
        return tbl_view_inspplan_overview;
    }

    @FXML
    private JFXTextArea ta_logging;

    @FXML
    private JFXButton btn_newInspPlan;

    @FXML
    private JFXButton btn_delInspPlan;

    @FXML
    private JFXButton btn_saveInspPlan;

    @FXML
    private JFXButton btn_editInspPlan;

    @FXML
    private JFXButton btn_assignCharacteristics;

    @FXML
    private JFXButton btn_backInspPlan;

    private ObservableList<InspectionPlanTemplateDTO> data;
    private Application_Controller application_controller;
    private ObservableList<InspectionPlanTemplateDTO> newInspectionPlans;

    private static InspPlanTemplateManagement_Controller instance;

    public synchronized static InspPlanTemplateManagement_Controller getInstance() {
        if (instance == null) {
            instance = new InspPlanTemplateManagement_Controller();
        }
        return instance;
    }

    private InspPlanTemplateManagement_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/InspPlanTemplateManagement.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(InspPlanTemplateManagement_Controller.class.getName()).log(Level.SEVERE, "Unable to load InspPlanTemplateManagement.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

        try {
            //prepare column: InspectionID
            JFXTreeTableColumn<InspectionPlanTemplateDTO, Integer> inspection_id = new JFXTreeTableColumn<>("ID");
            inspection_id.setPrefWidth(50);
            inspection_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanTemplateDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());
//            inspection_id.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
            //prepare column: Norm
            JFXTreeTableColumn<InspectionPlanTemplateDTO, String> norm = new JFXTreeTableColumn<>("Norm");
            norm.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanTemplateDTO, String> param) -> param.getValue().getValue().getNormProperty());
            norm.setPrefWidth(350);
            norm.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
//prepare column: Inspection
            JFXTreeTableColumn<InspectionPlanTemplateDTO, String> inspection = new JFXTreeTableColumn<>("Prüfplan");
            inspection.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanTemplateDTO, String> param) -> param.getValue().getValue().getDescriptionProperty());
            inspection.setPrefWidth(350);
            inspection.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

            data = FXCollections.observableArrayList();
            newInspectionPlans = FXCollections.observableArrayList();

            for (InspectionPlanTemplateDTO inspplan_template : new InspectionPlanTemplateDAO().selectAllInspectionPlanTemplates()) {
                data.add(inspplan_template);
            }

            TreeItem<InspectionPlanTemplateDTO> root;
            root = new RecursiveTreeItem<InspectionPlanTemplateDTO>(data, RecursiveTreeObject::getChildren);
            this.tbl_view_inspplan_overview.getColumns().setAll(inspection_id, norm, inspection);
            this.tbl_view_inspplan_overview.setRoot(root);
            this.tbl_view_inspplan_overview.setShowRoot(false);
            // set event handler for key pressed event --> Enter or Tab
            tbl_view_inspplan_overview.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.TAB | event.getCode() == KeyCode.ENTER) {
                        int newRowIndex = tbl_view_inspplan_overview.getCurrentItemsCount() - 1;
                        TreeTableColumn<InspectionPlanTemplateDTO, ?> nextCol = tbl_view_inspplan_overview.getColumns().get(2);
//                        tbl_view_inspplan_overview.getSelectionModel().select(newRowIndex, nextCol);
                        tbl_view_inspplan_overview.getFocusModel().focus(newRowIndex, nextCol);
                        tbl_view_inspplan_overview.edit(newRowIndex, nextCol);
                    }
                }

            });
//            // content changed event
//            this.tbl_view_inspplan_overview.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem<InspectionPlanTemplateDTO>>() {
//                @Override
//                public void onChanged(ListChangeListener.Change<? extends TreeItem<InspectionPlanTemplateDTO>> c) {
//                    System.out.println("Data changed: ");
//                    
//                }
//
//            });
            this.tbl_view_inspplan_overview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<InspectionPlanTemplateDTO>>() {
                @Override
                public void changed(ObservableValue<? extends TreeItem<InspectionPlanTemplateDTO>> observable, TreeItem<InspectionPlanTemplateDTO> oldValue, TreeItem<InspectionPlanTemplateDTO> newValue) {
//                    System.out.println("->" + newValue.getValue().getDescription());
//                    System.out.println("->" + newValue.getValue().getActiveBoolean());
//                    if (newValue.getValue().getActiveBoolean()) {
                    if (newValue != null) {
                        newInspectionPlans.add(newValue.getValue());
                    }

//                    }
                }

            }
            );

//            data.addListener(new ListChangeListener<InspectionPlanTemplateDTO>() {
//                @Override
//                public void onChanged(ListChangeListener.Change<? extends InspectionPlanTemplateDTO> c) {
//                    System.out.println("Data changed");
//                }
//
//            });
        } catch (SQLException ex) {
            Logger.getLogger(InspPlanTemplateManagement_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editItem(InspectionPlanTemplateDTO item) {

        // scroll to the new item
        int newRowIndex = this.tbl_view_inspplan_overview.getCurrentItemsCount() - 1;
        this.tbl_view_inspplan_overview.scrollTo(newRowIndex);
        // Put the second column in editing mode
        TreeTableColumn<InspectionPlanTemplateDTO, ?> firstCol = tbl_view_inspplan_overview.getColumns().get(1);
        // 
        this.tbl_view_inspplan_overview.getFocusModel().focus(newRowIndex, firstCol);
        tbl_view_inspplan_overview.edit(newRowIndex, firstCol);
        // set active
        item.setActive(1);

    }

    private void logging(String message) {
//        this.ta_logging.setText(message);
        ta_logging.appendText(message + "\n");
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
    void btn_assignCharacteristics_Handler(ActionEvent event) {

    }

    @FXML
    void btn_backInspPlan_Handler(ActionEvent event) {
        this.application_controller.setNode("INSPPLANOP");
    }

    @FXML
    void btn_delInspPLan_Handler(ActionEvent event) {
        InspectionPlanTemplateDAO model = new InspectionPlanTemplateDAO();
        // Get the selection model
        TreeTableViewSelectionModel<InspectionPlanTemplateDTO> sm = tbl_view_inspplan_overview.getSelectionModel();
        // Get the selected Entry
        int rowIndex = sm.getSelectedIndex();
        TreeItem<InspectionPlanTemplateDTO> selectedItem = sm.getModelItem(rowIndex);
        //
        InspectionPlanTemplateDTO currentInspectionPlan = selectedItem.getValue();
//        data.remove(currentInspectionPlan);
        try {
            data.remove(currentInspectionPlan);
            currentInspectionPlan.setActive(0);
            model.update(currentInspectionPlan);
//            selectedItem.getParent().getChildren().remove(selectedItem);
            this.logging("Prüfplan:" + selectedItem.getValue().getDescription() + " gelöscht");
        } catch (SQLException ex) {
            Logger.getLogger(InspPlanTemplateManagement_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btn_edit_InspPLan_Handler(ActionEvent event) {
        // set table editable
        this.tbl_view_inspplan_overview.setEditable(true);

    }

    @FXML
    void btn_newInspPlan_Handler(ActionEvent event) {
        InspectionPlanTemplateDTO new_inspplan_template = new InspectionPlanTemplateDTO();
        // add new Inspection Plan Template to Table
        data.add(new_inspplan_template);
        // store new inspection plan for database insert
        newInspectionPlans.add(new_inspplan_template);
        // set table editable
        this.tbl_view_inspplan_overview.setEditable(true);
        // prepare a new tree item with a new Inspection Plan Template object
        TreeItem<InspectionPlanTemplateDTO> root = new RecursiveTreeItem<InspectionPlanTemplateDTO>(data, RecursiveTreeObject::getChildren);
        TreeItem<InspectionPlanTemplateDTO> item = new TreeItem<>(new_inspplan_template);

        // add root item
        this.tbl_view_inspplan_overview.setRoot(root);
        // edit new item
        this.editItem(new_inspplan_template);

    }

    @FXML
    void btn_saveInsplan_Handler(ActionEvent event) {
        InspectionPlanTemplateDAO model = new InspectionPlanTemplateDAO();
        InspectionPlanTemplateDTO newInspectionPlan;
//        newInspectionPlan = tbl_view_inspplan_overview.get

        for (InspectionPlanTemplateDTO insertInspectionPlan : newInspectionPlans) {
            try {
                System.out.println("is active: " + insertInspectionPlan.getActiveBoolean());
                if (insertInspectionPlan.getActiveBoolean()) {
                    if (insertInspectionPlan.getId() == 0) {
                        insertInspectionPlan.setId(model.insert(insertInspectionPlan));
                        this.logging("Prüfplan: " + insertInspectionPlan.getDescription() + " angelegt");
                    } else {
                        model.update(insertInspectionPlan);
                        this.logging("Prüfplan: " + insertInspectionPlan.getDescription() + " gespeichertt");
                    }
                }
                // set table non editable
                this.tbl_view_inspplan_overview.setEditable(false);
//                this.newInspectionPlans.clear();
            } catch (SQLException ex) {
                Logger.getLogger(InspPlanTemplateManagement_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void tbl_view_inspplan_operation_handler(MouseEvent event) {

    }
}
