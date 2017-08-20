/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model_sqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LtDan
 */
public abstract class DBConnector {

    private Connection connection;
    private ArrayList<ResultSet> resultSetList = new ArrayList<ResultSet>();
    private ArrayList<Statement> statementList = new ArrayList<Statement>();

    public DBConnector() {

    }

    public final boolean connect(String host, String database, String user, String passwd) {
        boolean isConnected = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionCommand = "jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password=" + passwd;
            connection = DriverManager.getConnection(connectionCommand);
            isConnected = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isConnected;
    }

    public ResultSet selectStatement(String table) throws Throwable {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            statementList.add(stmt);
            rs = stmt.executeQuery("SELECT * FROM " + table);
            resultSetList.add(rs);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return rs;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            connection.close();
            for (ResultSet rs : resultSetList) {
                rs.close();
            }
            for (Statement stms : statementList) {
                stms.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.finalize();
        }

    }

}
