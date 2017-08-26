/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_main;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import mvc_controller.Application_Controller;
import mvc_controller.SplashScreen_Controller;
import mvc_model_sqlconnector.DBConnection;
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

    Application_Controller rootPrimaryStage;

    protected DBConnection connection;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.splashScreenStage = primaryStage;

// manage stage layout:
//        primaryStage.setTitle("Application");
// init SplashScreen
        SplashScreen_Controller root_SplashScreen = SplashScreen_Controller.getInstance();
        Scene scene_SplashScreen = new Scene(root_SplashScreen);
        splashScreenStage.initStyle(StageStyle.UNDECORATED);
// init notificationPane        
        notificationPane = new NotificationPane(root_SplashScreen);
        notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
// set css
        notificationPane.getStylesheets().addAll("styles/splashScreen.css");
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

        connection = DBConnection.getInstance();
        if (connection.IsConnected()) {
            rootPrimaryStage = Application_Controller.getInstance();
            Scene scenePrimaryStage = new Scene(rootPrimaryStage);
            this.mainStage.getIcons().add(new Image("images/ASUM_logo-1.png"));
            this.mainStage.setScene(scenePrimaryStage);
        }

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
                if (rootPrimaryStage == null) {
                    rootPrimaryStage = Application_Controller.getInstance();
                    Scene scenePrimaryStage = new Scene(rootPrimaryStage);
                    mainStage.getIcons().add(new Image("images/ASUM_logo-1.png"));
                    mainStage.setScene(scenePrimaryStage);
                }
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
