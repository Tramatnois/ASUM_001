/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mvc_model_sqlconnector.DBConnection;
import org.controlsfx.control.NotificationPane;

/**
 * FXML Controller class
 *
 * @author tramatnois
 */
public class SplashScreen_Controller extends AnchorPane {

    @FXML
    private AnchorPane splashScreenPane;

    @FXML
    private Label lbl_Logo;

    @FXML
    private Label lbl_application;

    @FXML
    private Label lbl_welcome;

    @FXML
    private Pane spinnerPane;

    @FXML
    private Label lbl_wait;

    @FXML
    private JFXSpinner spinner;

    @FXML
    private JFXButton btn_application_start;

    @FXML
    private JFXButton btn_application_retry;

    private static SplashScreen_Controller instance;
    private Application_Controller application_DocumentController;
    private Stage splashScreenStage;

    private ParallelTransition paraTransition;

    protected DBConnection connection;

    private NotificationPane notificationPane;

    public void setNotificationPane(NotificationPane notificationPane) {
        this.notificationPane = notificationPane;
    }

    public synchronized static SplashScreen_Controller getInstance() {
        if (instance == null) {
            instance = new SplashScreen_Controller();
        }
        return instance;
    }

    private SplashScreen_Controller() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mvc_view_splashScreen/FXML_SplashScreen_Document.fxml"));
// Tell the loader that this object is the BorderPane we've designed in FXML.
        loader.setRoot(this);
// Tell the loader that this is the object whose attributes and methods are referenced in FXML.
        loader.setController(this);
        // Load the FXML document. When this succeeds, the @FXML attributes will be ready to use.
        try {
            loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(FXML_StorageRackInsp_DocumentController.class.getName()).log(Level.SEVERE, "Unable to load FXML_Customer_Document.fxml", ex);
        }
        initialize();
    }

    private void initialize() {

        lbl_Logo.setVisible(false);
        lbl_application.setVisible(false);
        lbl_welcome.setVisible(false);

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), lbl_Logo);
        translateTransition1.setByY(700);
        translateTransition1.play();

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5), lbl_application);
        translateTransition2.setByY(700);
        translateTransition2.play();

        TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(0.5), lbl_welcome);
        translateTransition3.setByX(-700);
        translateTransition3.play();

        translateTransition1.setOnFinished(event -> {
            lbl_Logo.setVisible(true);
            TranslateTransition translateTransitionEvent1 = new TranslateTransition(Duration.seconds(1), lbl_Logo);
            translateTransitionEvent1.setByY(-700);
            translateTransitionEvent1.play();

            translateTransitionEvent1.setOnFinished(event1 -> {

                lbl_application.setVisible(true);
                TranslateTransition translateTransitionEvent2 = new TranslateTransition(Duration.seconds(1), lbl_application);
                translateTransitionEvent2.setByY(-700);
                translateTransitionEvent2.play();

                translateTransitionEvent2.setOnFinished(event2 -> {

                    lbl_welcome.setVisible(true);
                    TranslateTransition translateTransitionEvent3 = new TranslateTransition(Duration.seconds(1), lbl_welcome);
                    translateTransitionEvent3.setByX(700);
                    translateTransitionEvent3.play();

                    translateTransitionEvent3.setOnFinished(event4 -> {

                        spinnerPane.setVisible(true);
                        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), spinnerPane);
                        fadeIn.setFromValue(0);
                        fadeIn.setToValue(1);
//                        ParallelTransition paraTransition = new ParallelTransition(fadeIn);
                        paraTransition = new ParallelTransition(fadeIn);
                        paraTransition.play();

                        fadeIn.setOnFinished(event5 -> {

                            PauseTransition pause = new PauseTransition(Duration.seconds(1));
                            pause.play();

                            // new thread check db connection
                            // check the database connection
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //Update your GUI here
                                    connection = DBConnection.getInstance();
                                    if (!connection.IsConnected()) {

//                                        notificationPane.getActions().addAll(new Action("Retry", ae -> {
//                                            
//                                        }));
                                        notificationPane.show("Fehler: Keine Verbindung zur Datenbank");
                                        spinnerPane.setVisible(false);
                                        btn_application_start.setVisible(false);
                                        btn_application_retry.setVisible(true);
                                        btn_application_retry.setText("");
                                        btn_application_retry.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.REFRESH));
//                                        btn_application_retry.setStyle("-fx-pref-width: 50; -fx-pref-height: 50;");
                                        btn_application_retry.setAlignment(Pos.CENTER);
                                    }
                                }
                            });

                            pause.setOnFinished(event6 -> {
                                if (connection.IsConnected()) {
                                    notificationPane.hide();
                                    btn_application_start.setVisible(true);
                                }
                                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), spinnerPane);
                                fadeOut.setFromValue(1);
                                fadeOut.setToValue(0);
                                FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(1), btn_application_start);
                                fadeIn2.setFromValue(0);
                                fadeIn2.setToValue(1);
                                ParallelTransition paraTransition2 = new ParallelTransition(fadeOut, fadeIn2);
                                paraTransition2.play();
                                fadeOut.setOnFinished(event7 -> {

                                });
                            });

                        });
//                        this.splashScreenStage.close();
                    });

                });

            });

        });

    }

    @FXML
    void btn_start_handler(ActionEvent event) {
//        Notifications notificationsBuilder = Notifications.create()
//                .title("Fehler")
//                .text("Datenbank wurde nicht gefunden")
//                .graphic(null)
//                .position(Pos.TOP_LEFT);
//        notificationsBuilder.showError();
//        Scene scene = splashScreenStage.getScene();
//        Parent pane = scene.getRoot();
//        NotificationPane notificationPane = new NotificationPane(splashScreenStage.getScene().getRoot());
//        notificationPane.setText("TEST");

//        VBox root = new VBox(10);
//        root.setPadding(new Insets(50, 0, 0, 10));
//        notificationPane.setContent(root);
////        scene = new Scene(notificationPane, scene.getWidth(), scene.getHeight());
////        splashScreenStage.setScene(new Scene(notificationPane, splashScreenStage.getScene().getWidth(), splashScreenStage.getScene().getHeight() ));
//        
//        if (notificationPane.isShowing()) {
//            notificationPane.hide();
//        } else {
//            notificationPane.show();
//        }
        this.splashScreenStage.hide();
    }

    @FXML
    void btn_retry_handler(ActionEvent event) {

        if (notificationPane.isShowing()) {
            notificationPane.hide();
            spinnerPane.setVisible(true);
            btn_application_retry.setVisible(false);
            paraTransition.play();
        } else {
            spinnerPane.setVisible(true);
            btn_application_retry.setVisible(false);
            paraTransition.play();
        }
        if (!connection.IsConnected()) {
            notificationPane.show();
//            spinnerPane.setVisible(false);
        } else {
            spinnerPane.setVisible(true);
            btn_application_retry.setVisible(false);
        }
    }

    public void setReference(Application_Controller controller) {
        this.application_DocumentController = controller;
    }

    public void setSplashScreenStage(Stage splashScreenStage) {
        this.splashScreenStage = splashScreenStage;
    }
}
