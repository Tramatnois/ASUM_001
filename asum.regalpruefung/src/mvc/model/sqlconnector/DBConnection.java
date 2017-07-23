/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.sqlconnector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author danie
 */
public class DBConnection extends DBConnector{
    
    private String driver;
    private String host;
    private String database;
    private String username;
    private String password;

    public DBConnection() {
        //super("localhost", "asum_gmbh", "root", "");
        super();
        this.get_Properties();
        this.connect(host,database,username,password);
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
    public static void main(String[] args) {
        DBConnection conn = new DBConnection();
    }
        
}
