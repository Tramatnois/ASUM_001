/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_model;

import mvc_model_sqlconnector.DBConnection;

/**
 *
 * @author LT Dan
 */
public abstract class AbstractDAO {

    protected DBConnection connection;

    public AbstractDAO() {
        connection = DBConnection.getInstance();
    }
}
