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
import javax.jws.WebParam;
import javax.jws.WebService;
import library.helpers.DBHelper;
import library.models.Author;
import library.models.Publisher;

/**
 *
 * @author dinhloc
 */
@WebService
public class AuthorService {
    public List<Author> getAuthors() {

        List<Author> authors = new ArrayList<>();
        

        try {
            Connection conn = DBHelper.createDBConnection();
            ResultSet rs = null;
            String queryString = "Select * from authors";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                Author a = new Author(rs.getInt("AuthorID"), rs.getString("AuthorName"));
                authors.add(a);
            }
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authors;
    }
    
    public List<Author> addAuthor(@WebParam(name = "AuthorName") String authorNameString) {

        try {

            Connection conn = DBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("insert into authors values(null,?)");

            stmt.setString(1, authorNameString);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getAuthors();
    }
    
    public List<Author> updateAuthor(@WebParam(name = "AuthorID") int authorID,@WebParam(name = "AuthorName") String authorNameString) {

        try {

            Connection conn = DBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("update authors set AuthorName = ? where AuthorId = ?;");

            stmt.setString(1, authorNameString);
            stmt.setInt(2, authorID);

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getAuthors();
    }
    
    public List<Author> deleteAuthor(@WebParam(name = "ID") int ID) {

        try {

            Connection conn = DBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("delete from authors where AuthorID =?");

            stmt.setInt(1, ID);

            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getAuthors();
    }
}
