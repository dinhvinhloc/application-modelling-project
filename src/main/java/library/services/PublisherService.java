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
import library.models.Publisher;

/**
 *
 * @author dinhloc
 */
@WebService
public class PublisherService {
        
    public List<Publisher> getPublishers() {

        List<Publisher> publishers = new ArrayList<>();

        try {
            DBHelper dBHelper = new DBHelper();

            Connection conn = dBHelper.createDBConnection();
            ResultSet rs = null;
            String queryString = "Select * from publishers";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                Publisher p = new Publisher(rs.getInt("PublisherID"), rs.getString("PublisherName"));
                publishers.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publishers;
    }
    
    public List<Publisher> addPublisher(@WebParam(name = "PublisherName") String publisherNameString) {

        try {

            DBHelper dBHelper = new DBHelper();
            Connection conn = dBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("insert into publishers values(null,?)");

            stmt.setString(1, publisherNameString);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getPublishers();
    }
    
    public List<Publisher> updatePublisher(@WebParam(name = "PublisherID") int publisherID,@WebParam(name = "PublisherName") String publisherNameString) {

        try {

            DBHelper dBHelper = new DBHelper();
            Connection conn = dBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("update publishers set PublisherName = ? where PublisherID = ?;");

            stmt.setString(1, publisherNameString);
            stmt.setInt(2, publisherID);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getPublishers();
    }
    
    public List<Publisher> deletePublisher(@WebParam(name = "ID") int ID) {

        try {

            DBHelper dBHelper = new DBHelper();
            Connection conn = dBHelper.createDBConnection();

            PreparedStatement stmt = conn.prepareStatement("delete from publishers where PublisherID =?");

            stmt.setInt(1, ID);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getPublishers();
    }
    
}
