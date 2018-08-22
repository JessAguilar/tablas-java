/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2;

/**
 *
 * @author Administrador
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    
    private static conexion instance;
    private Connection connection;
    
    private conexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/tabla", "root", "");
            this.connection.setAutoCommit(false);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static conexion getInstance() throws SQLException {
        if (instance == null) {
            instance = new conexion();
        } else if (instance.getConnection().isClosed()) {
            instance = new conexion();
        }

        return instance;
    }

}

