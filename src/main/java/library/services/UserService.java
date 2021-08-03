/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import library.helpers.DBHelper;
import library.models.LoggedInUser;

/**
 *
 * @author dinhloc
 */
@WebService
public class UserService {
    @WebMethod
    public int insertUser(@WebParam(name="UserName")String usernameString,@WebParam(name="Password") String passwordString, @WebParam(name="Email") String emailString, @WebParam(name="UserRole") String userRoleString, @WebParam(name="CreatedDate") String createdDateString){
        
        int result = 0;
        
        try {
            DBHelper dBHelper = new DBHelper();
        
            Connection conn = dBHelper.createDBConnection();
            PreparedStatement stmt=conn.prepareStatement("insert into users values(null,?,?,?,?,?)");
            
            stmt.setString(1, usernameString);
            stmt.setString(2, passwordString);
            stmt.setString(3, emailString);
            stmt.setString(4, userRoleString);
            stmt.setString(5, createdDateString);
            
            result = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
        
    }
    
    @WebMethod
    public LoggedInUser validateUser(String username, String password) {
        
        try {
           
            DBHelper dBHelper = new DBHelper();
        
            Connection conn = dBHelper.createDBConnection();
            
            ResultSet rs = null;
            String queryString = "Select * from users where Username = '" + username + "' and Password = '"+password+"';";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                LoggedInUser user = new LoggedInUser();
                user.setUserId(rs.getInt("ID"));
                user.setUserRole(rs.getString("UserRole"));
                return user;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    @WebMethod
    public int reserveBook(int bookId, int userId) {
        int result = 0;
        
        try {
            DBHelper dBHelper = new DBHelper();
        
            Connection conn = dBHelper.createDBConnection();
            PreparedStatement stmt=conn.prepareStatement("INSERT INTO bsq17ugjkx3188bq.booksrequests VALUES (?, ?, null)");
            
            stmt.setInt(1, bookId);
            stmt.setInt(2, userId);
            
            result = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;   
    }
    
    @WebMethod
    public int returnBook(int bookId) {
        int result = 0;
        
        try {
            DBHelper dBHelper = new DBHelper();
        
            Connection conn = dBHelper.createDBConnection();
            PreparedStatement stmt=conn.prepareStatement("DELETE FROM bsq17ugjkx3188bq.booksrequests WHERE BookID = ?");
            
            stmt.setInt(1, bookId);
            
            result = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;   
    }
}
