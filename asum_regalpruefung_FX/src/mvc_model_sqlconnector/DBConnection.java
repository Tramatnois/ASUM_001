/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model_sqlconnector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author danie
 */
public class DBConnection extends DBConnector {
    
    private static DBConnection instance;
    private String driver;
    private String host;
    
    private static boolean isConnected;
    
    public boolean IsConnected() {
//        isConnected = this.connect(host,database,username,password);
        return isConnected;
    }
    
    public void isConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }
    
    public String getHost() {
        return host;
    }
    
    public String getDatabase() {
        return database;
    }
    private String database;
    private String username;
    private String password;
    
    private DBConnection() {
        //super("localhost", "asum_gmbh", "root", "");
        super();
        this.get_Properties();
        isConnected = this.connect(host, database, username, password);
        
    }
    
    public static DBConnection getInstance() {
        if (DBConnection.instance == null) {
            instance = new DBConnection();
        } else {
            instance.isConnected(instance.connect(instance.host, instance.database, instance.username, instance.password));
        }
        return instance;
    }
    
    private void get_Properties() {
        Properties prop = new Properties();
        InputStream input = null;
        
        try {
            input = new FileInputStream("config.properties");
            // load a properties file
            prop.load(input);
            // get the property value
            driver = prop.getProperty("jdbc.driver");
            host = prop.getProperty("jdbc.host");
            database = prop.getProperty("jdbc.database");
            username = prop.getProperty("jdbc.username");
            password = prop.getProperty("jdbc.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
