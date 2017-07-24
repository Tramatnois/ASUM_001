/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_main;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import mvc_view.SplashScreenDrawer;

/**
 *
 * @author tramatnois
 */
public class runApplication extends Application {

//    final Float[] values = new Float[]{-1.0f, 0f, 0.6f, 1.0f};
//    final Label[] labels = new Label[values.length];
//    final ProgressBar[] pbs = new ProgressBar[values.length];
//    final ProgressIndicator[] pins = new ProgressIndicator[values.length];
//    final HBox hbs[] = new HBox[values.length];

    Screen screen = Screen.getPrimary();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Task<SplashScreenDrawer> splashScreenTask = createSplashScreenTask();
        Stage splashScreenStage = new Stage();
        
        buildSplashScreenUI(splashScreenStage, splashScreenTask);
        

        // manage stage layout:
        primaryStage.yProperty().addListener((obs, oldY, newY) -> splashScreenStage.setY(newY.doubleValue() - 100));
        primaryStage.setTitle("Application");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/mvc_view_application/FXML_Application_Document.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //splashScreenStage.setTitle("Wie könnte das Programm heißen?");

// maximize screen
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
// set delay event and close after delay the scene
// and open the main scence application
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> splashScreenStage.close());
        delay.setOnFinished(event -> primaryStage.show());

        delay.play();
// display GUI

        splashScreenStage.centerOnScreen();
        splashScreenStage.show();

//        primaryStage.show();
//        primaryStage.centerOnScreen();
//        splashScreenStage.toFront();
        primaryStage.setOnCloseRequest(event -> splashScreenStage.close());

    }

    public static void main(String[] args) {
        launch(args);
    }

    private Task<SplashScreenDrawer> createSplashScreenTask() {
        return new Task<SplashScreenDrawer>() {
            @Override
            public SplashScreenDrawer call() throws Exception {

                return new SplashScreenDrawer();
            }
        };
    }

    private void buildSplashScreenUI(Stage splashScreenStage, Task<SplashScreenDrawer> splashScreenTask) throws IOException {
        Parent root_splash;
        root_splash = FXMLLoader.load(getClass().getResource("/mvc_view_splashScreen/FXML_SplashScreen_Document.fxml"));
        Scene splashScreenScene = new Scene(root_splash);
        splashScreenStage.setScene(splashScreenScene);
        splashScreenStage.initStyle(StageStyle.UNDECORATED);
    }

}
