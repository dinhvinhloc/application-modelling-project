/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinhloc
 */
public class DBHelper {

    public DBHelper() {
    }

    public Connection createDBConnection() throws SQLException {
        Connection conn = null;
        try {
            String dbURL, dbUsername, dbPassword;
            Class.forName("com.mysql.jdbc.Driver");
            dbURL = "jdbc:mysql://npb8qi6wzuof08g2:sayvzotjtly1yjg5@ohunm00fjsjs1uzy.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/bsq17ugjkx3188bq?useSSL=false";
            dbUsername = "npb8qi6wzuof08g2";
            dbPassword = "sayvzotjtly1yjg5";
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
