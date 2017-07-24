/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_main;

import javafx.application.Application;
import javafx.stage.Stage;
import mvc_view.SplashScreenDrawer;

/**
 *
 * @author tramatnois
 */
public abstract class runApplication extends Application {

    SplashScreenDrawer splashScreenStage;
   
//    @Override

    public runApplication() {
        this.splashScreenStage = new SplashScreenDrawer();
    }
//    public void start(Stage splashScreenStage) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
