package mvc_controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import mvc_model.CustomerDTO;
import mvc_model.InspectionPlanOperationDTO;

public class InspPlanOpSelect_Controller extends AnchorPane {

    @FXML
    private JFXTreeTableView<InspectionPlanOperationDTO> tbl_view_inspPlanOp_select;

    @FXML
    private JFXRadioButton rb_inspPlanOpID;

    @FXML
    private ToggleGroup filter1;

    @FXML
    private JFXRadioButton rb_inspPlanOp;

    @FXML
    private ToggleGroup filter11;

    @FXML
    private JFXRadioButton rb_customerID;

    @FXML
    private ToggleGroup filter;

    @FXML
    private JFXRadioButton rb_customer;

    @FXML
    private JFXTextField tf_searchInspPlanOp;
    private Application_Controller application_controller;
    private static InspPlanOpSelect_Controller instance;

    /**
     * Statische Methode 'getInstance()Ä liefert die einzige Instanz der Klasse
     * zurück. Ist synchronisiert und somit thread-sicher.
     */
    public synchronized static InspPlanOpSelect_Controller getInstance() {
        if (instance == null) {
            instance = new InspPlanOpSelect_Controller();
        }
        return instance;
    }

    private InspPlanOpSelect_Controller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_application/InspPlanOpSelect.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(InspPlanOpSelect_Controller.class.getName()).log(Level.SEVERE, "Unable to load InspPlanOpSelect.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

        rb_inspPlanOpID.setSelectedColor(Color.CHARTREUSE);
        rb_inspPlanOpID.setUnSelectedColor(Color.WHITESMOKE);
        rb_inspPlanOp.setSelectedColor(Color.CHARTREUSE);
        rb_inspPlanOp.setUnSelectedColor(Color.WHITESMOKE);
        rb_customerID.setSelectedColor(Color.CHARTREUSE);
        rb_customerID.setUnSelectedColor(Color.WHITESMOKE);
        rb_customer.setSelectedColor(Color.CORAL);
        rb_customer.setUnSelectedColor(Color.WHITESMOKE);
        
        //prepare column: InspectionID
            JFXTreeTableColumn<InspectionPlanOperationDTO, Integer> inspection_id = new JFXTreeTableColumn<>("ID");
            inspection_id.setPrefWidth(25);
//            inspection_id.setPrefWidth(Control.USE_COMPUTED_SIZE);
            inspection_id.getStyleClass().add("col_inspection_id");
            inspection_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());

//prepare column: Inspection
            JFXTreeTableColumn<InspectionPlanOperationDTO, String> inspection = new JFXTreeTableColumn<>("Prüfplan");
            inspection.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getDescriptionProperty());
            inspection.setPrefWidth(300);

//prepare column: KundenID
            JFXTreeTableColumn<InspectionPlanOperationDTO, Integer> customer_id = new JFXTreeTableColumn<>("KundeID");
            customer_id.getStyleClass().add("col_customer_id");
            customer_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, Integer> param) -> param.getValue().getValue().getIdProperty().asObject());
            customer_id.setPrefWidth(75);
//prepare column: Kundenname
            JFXTreeTableColumn<InspectionPlanOperationDTO, String> customer = new JFXTreeTableColumn<>("Kunde");
            customer.setCellValueFactory((TreeTableColumn.CellDataFeatures<InspectionPlanOperationDTO, String> param) -> param.getValue().getValue().getCustomer().getNameProperty());
            customer.setPrefWidth(100);
        
            tbl_view_inspPlanOp_select.getColumns().setAll(inspection_id, inspection, customer_id, customer);
        

        tbl_view_inspPlanOp_select.setFocusTraversable(true);
        tbl_view_inspPlanOp_select.setShowRoot(false);
        tbl_view_inspPlanOp_select.getStyleClass().add("InspPlanOpSelectTable");
        
// setDoubleClickEvent Handler
        tbl_view_inspPlanOp_select.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TreeItem<InspectionPlanOperationDTO> inspPlanOp = tbl_view_inspPlanOp_select.getSelectionModel().getSelectedItem();
                if (event.getClickCount() == 2) {
                    application_controller.getResultsRecording_Controller().setInspection(inspPlanOp.getValue().getId());
                    application_controller.getResultsRecording_Controller().setInspection(inspPlanOp.getValue().getDescription());
                    application_controller.getResultsRecording_Controller().getPopup().hide();
                }
            }

        });
        
        tf_searchInspPlanOp.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    tbl_view_inspPlanOp_select.setPredicate(new Predicate<TreeItem<InspectionPlanOperationDTO>>() {
                        @Override
                        public boolean test(TreeItem<InspectionPlanOperationDTO> inspPlanOp) {
                            Boolean flag = false;
//                            Boolean flag = customer.getValue().getName().contains(newValue) | customer.getValue().getId().toString().contains(newValue);
                            if (rb_inspPlanOpID.isSelected()) {
                                flag = inspPlanOp.getValue().getId().toString().contains(newValue);
                            }
                            if (rb_inspPlanOp.isSelected()) {
                                flag = inspPlanOp.getValue().getDescription().contains(newValue);
                            }
                            if (rb_customerID.isSelected()) {
                                flag = inspPlanOp.getValue().getCustomer().getId().toString().contains(newValue);
                            }
                            if (rb_customer.isSelected()) {
                                flag = inspPlanOp.getValue().getCustomer().getName().contains(newValue);
                            }
                            return flag;
                        }
                    });
                }
            });



    }

    public void setApplication_controller(Application_Controller controller) {
        this.application_controller = controller;
    }

    public JFXTreeTableView<InspectionPlanOperationDTO> getTbl_view_inspPlanOp_select() {
        prepareInspPlanOpSelectTable();
        return tbl_view_inspPlanOp_select;
    }

    public void prepareInspPlanOpSelectTable() {
        tbl_view_inspPlanOp_select.setRoot(this.application_controller.getInspectionPlanOperations().getTbl_view_inspplan_operation().getRoot());
    }

    @FXML
    void tbl_view_inspPlanOp_select_handler(MouseEvent event) {

    }

}
