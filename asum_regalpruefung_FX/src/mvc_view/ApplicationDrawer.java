/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ApplicationDrawer extends Application {

    Screen screen = Screen.getPrimary();

    @Override
    public void start(Stage ApplicationStage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/mvc_view_application/FXML_Application_Document.fxml"));
        Scene scene = new Scene(root);

        ApplicationStage.setScene(scene);
        ApplicationStage.setTitle("Wie könnte das Programm heißen?");
// maximize screen
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        ApplicationStage.setX(bounds.getMinX());
        ApplicationStage.setY(bounds.getMinY());
        ApplicationStage.setWidth(bounds.getWidth());
        ApplicationStage.setHeight(bounds.getHeight());
// display GUI
        ApplicationStage.show();
        ApplicationStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
