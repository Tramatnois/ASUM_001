/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_main;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import mvc_controller.FXML_Application_DocumentController;
import mvc_controller.FXML_SplashScreen_DocumentController;
import org.controlsfx.control.NotificationPane;

/**
 *
 * @author tramatnois
 */
public class runApplication extends Application {

//    Screen screen = Screen.getPrimary();
    private Stage splashScreenStage;
    private Stage mainStage;
    private NotificationPane notificationPane;
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.splashScreenStage = primaryStage;

// manage stage layout:
//        primaryStage.setTitle("Application");
// init SplashScreen
        FXML_SplashScreen_DocumentController root_SplashScreen = FXML_SplashScreen_DocumentController.getInstance();
        Scene scene_SplashScreen = new Scene(root_SplashScreen);
        splashScreenStage.initStyle(StageStyle.UNDECORATED);
// init notificationPane        
        notificationPane = new NotificationPane(root_SplashScreen);
        notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
// set css
        notificationPane.getStylesheets().addAll("styles/fxml_splashscreen_document.css");
// get icon for notificationPane        
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.WARNING);
        icon.setSize("2em");
        icon.setStyleClass("WarningIcon");
        notificationPane.setGraphic(icon);

//        notificationPane.setShowFromTop(false);
        root_SplashScreen.setNotificationPane(notificationPane);

        splashScreenStage.setScene(new Scene(notificationPane));

        root_SplashScreen.setSplashScreenStage(splashScreenStage);

        splashScreenStage.show();

        mainStage = new Stage();
        
        FXML_Application_DocumentController rootPrimaryStage = FXML_Application_DocumentController.getInstance();
        Scene scenePrimaryStage = new Scene(rootPrimaryStage);
        this.mainStage.setScene(scenePrimaryStage);
        
//// maximize screen
//        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
//        primaryStage.setX(bounds.getMinX());
//        primaryStage.setY(bounds.getMinY());
//        primaryStage.setWidth(bounds.getWidth());
//        primaryStage.setHeight(bounds.getHeight());
//// set full screen for primary stage
//        primaryStage.setFullScreen(true);
// set delay event and close after delay the scene
// and open the main scence application
        splashScreenStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mainStage.show();
            }
        });
//        primaryStage.show();
//        primaryStage.centerOnScreen();
//        splashScreenStage.toFront();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
