package mvc_controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class SidePanelContentController implements Initializable {

    @FXML
    private JFXButton btn1;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
}
