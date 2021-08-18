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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import library.helpers.DBHelper;
import library.helpers.Email;
import library.models.Book;
import library.models.BookRequest;
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
            Connection conn = DBHelper.createDBConnection();
            PreparedStatement stmt=conn.prepareStatement("insert into users values(null,?,?,?,?,?)");
            
            stmt.setString(1, usernameString);
            stmt.setString(2, passwordString);
            stmt.setString(3, emailString);
            stmt.setString(4, userRoleString);
            stmt.setString(5, createdDateString);
            
            result = stmt.executeUpdate();
            conn.close();
            
            String from = "humberlibmanagement@gmail.com";
            String subject = "Account Registration successful!";
            
            String message = "Dear "+ usernameString
                             + "\n\nYour account has been registered successfully"
                             + "\n\n Regards"
                             + "\nHumber College Library";
            
            
            Email.sendEmail(from, emailString, subject, message);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
        
    }
    
    @WebMethod
    public LoggedInUser validateUser(String username, String password) {
        
        try {
           
            Connection conn = DBHelper.createDBConnection();
            
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
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    @WebMethod
    public int reserveBook(int bookId, int userId) {
        int result = 0;
        
        try {
            Connection conn = DBHelper.createDBConnection();
            PreparedStatement stmt=conn.prepareStatement("INSERT INTO bsq17ugjkx3188bq.booksrequests VALUES (?, ?, null)");
            
            stmt.setInt(1, bookId);
            stmt.setInt(2, userId);
            
            result = stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;   
    }
    
    @WebMethod
    public int returnBook(int bookId) {
        int result = 0;
        
        try {
            Connection conn = DBHelper.createDBConnection();
            PreparedStatement stmt=conn.prepareStatement("DELETE FROM bsq17ugjkx3188bq.booksrequests WHERE BookID = ?");
            
            stmt.setInt(1, bookId);
            
            result = stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;   
    }
    
    @WebMethod
    public List<BookRequest> getRequestedBooks() {

        List<BookRequest> bookRequests = new ArrayList<>();

        try {
            Connection conn = DBHelper.createDBConnection();
            ResultSet rs = null;
            String queryString = "Select * from booksrequests";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                BookRequest br = new BookRequest(rs.getInt("BookID"), rs.getInt("UserID"), rs.getString("RequestDate"));

                bookRequests.add(br);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookRequests;
    }
}
