/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.sqlconnector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.model.CustomerDTO;

/**
 *
 * @author danie
 */
public class DBConnection extends DBConnector{
    private static DBConnection instance;
    private String driver;
    private String host;
    private String database;
    private String username;
    private String password;

    private DBConnection() {
        //super("localhost", "asum_gmbh", "root", "");
        super();
        this.get_Properties();
        this.connect(host,database,username,password);
        
    }
    public static DBConnection getInstance() {
        if(DBConnection.instance == null)
            instance = new DBConnection();
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
