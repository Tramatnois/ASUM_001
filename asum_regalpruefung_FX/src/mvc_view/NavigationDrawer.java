/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_view;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class NavigationDrawer extends Application {

    public static Boolean isSplashLoaded = false;
    Screen screen = Screen.getPrimary();

    @Override
    public void start(Stage splashScreenStage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/mvc_view_splashScreen/FXML_SplashScreen_Document.fxml"));
        Scene scene = new Scene(root);

        splashScreenStage.setScene(scene);
        //splashScreenStage.setTitle("Wie könnte das Programm heißen?");
        splashScreenStage.initStyle(StageStyle.UNDECORATED);
// set delay event and close after delay the scene
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> splashScreenStage.close());
        delay.play();
// maximize screen
//        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
//        splashScreenStage.setX(bounds.getMinX());
//        splashScreenStage.setY(bounds.getMinY());
//        splashScreenStage.setWidth(bounds.getWidth());
//        splashScreenStage.setHeight(bounds.getHeight());
// display GUI
        splashScreenStage.show();
        splashScreenStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
