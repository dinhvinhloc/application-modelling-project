/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.models;

/**
 *
 * @author dinhloc
 */
public class BookRequest {
    private int bookId;
    private int userId;
    private String requestedDate;

    public BookRequest() {
    }

    public BookRequest(int bookId, int userId, String requestedDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.requestedDate = requestedDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }
    
    
    
}
