/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controller.LoginController;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author Admin
 * @param <T>
 */
public abstract class DAO<T> {

    protected Connection connection;

    public DAO() {
        try {
           String user = "linh";
            String pass = "123456";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=HappyPrograming9;encrypt=true;trustServerCertificate=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public abstract ArrayList<T> listAll();

    public abstract void insert(T entity);

    public abstract void update(T entity);

    public abstract void delete(T entity);
    
}
