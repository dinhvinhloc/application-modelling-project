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
import library.models.Book;
import library.models.BookFullInfo;

/**
 *
 * @author dinhloc
 */
@WebService
public class BookService {

    public List<Book> getBooks() {

        List<Book> books = new ArrayList<>();

        try {
            DBHelper dBHelper = new DBHelper();

            Connection conn = dBHelper.createDBConnection();
            ResultSet rs = null;
            String queryString = "Select * from books";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                Book b = new Book(rs.getInt("id"), rs.getString("ISBN"), rs.getInt("PublisherID"), rs.getString("Title"), rs.getInt("AuthorID"), rs.getInt("Pages"), rs.getString("PublishedDate"));

                books.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    public List<BookFullInfo> getBooksFullInfo() {

        List<BookFullInfo> books = new ArrayList<>();

        try {
            DBHelper dBHelper = new DBHelper();

            Connection conn = dBHelper.createDBConnection();
            ResultSet rs = null;
            String queryString = "select * from books b join publishers p on b.PublisherID = p.PublisherID join authors a on b.AuthorID = a.AuthorID;";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                BookFullInfo b = new BookFullInfo(rs.getInt("id"), rs.getString("ISBN"), rs.getString("PublisherName"), rs.getString("Title"), rs.getString("AuthorName"), rs.getInt("Pages"), rs.getString("PublishedDate"));

                books.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookFullInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

    public List<BookFullInfo> addBook(@WebParam(name = "ISBN") String isbnString, @WebParam(name = "Title") String titleString, @WebParam(name = "PublishedDate") String publishedDateString, @WebParam(name = "PublisherID") int publisherID, @WebParam(name = "AuthorID") int authorID, @WebParam(name = "Pages") int pages) {

        try {

            DBHelper dBHelper = new DBHelper();
            Connection conn = dBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("insert into books values(null,?,?,?,?,?,?)");

            stmt.setString(1, isbnString);
            stmt.setString(2, titleString);
            stmt.setString(3, publishedDateString);
            stmt.setInt(4, publisherID);
            stmt.setInt(5, authorID);
            stmt.setInt(6, pages);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookFullInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getBooksFullInfo();
    }

    public List<BookFullInfo> updateBook(@WebParam(name = "ID") int ID, @WebParam(name = "ISBN") String isbnString, @WebParam(name = "Title") String titleString, @WebParam(name = "PublishedDate") String publishedDateString, @WebParam(name = "PublisherID") int publisherID, @WebParam(name = "AuthorID") int authorID, @WebParam(name = "Pages") int pages) {

        try {

            DBHelper dBHelper = new DBHelper();
            Connection conn = dBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("update books set ISBN = ?, Title = ?, PublishedDate = ?, PublisherID = ?, AuthorID=?, Pages = ? where ID = ?");

            stmt.setString(1, isbnString);
            stmt.setString(2, titleString);
            stmt.setString(3, publishedDateString);
            stmt.setInt(4, publisherID);
            stmt.setInt(5, authorID);
            stmt.setInt(6, pages);
            stmt.setInt(7, ID);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookFullInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getBooksFullInfo();
    }
    
    
    public List<BookFullInfo> deleteBook(@WebParam(name = "ID") int ID) {

        try {

            DBHelper dBHelper = new DBHelper();
            Connection conn = dBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("delete from books where ID =?");

            stmt.setInt(1, ID);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookFullInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getBooksFullInfo();
    }
   
}
