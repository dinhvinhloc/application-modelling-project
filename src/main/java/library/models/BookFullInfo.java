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
public class BookFullInfo {
    private int id;
    private String isbn;
    private String publisherName;
    private String title;
    private String authorName;
    private int pages;
    private String publishedDate;

    public BookFullInfo() {
    }

    public BookFullInfo(int id, String isbn, String publisherName, String title, String authorName, int pages, String publishedDate) {
        this.id = id;
        this.isbn = isbn;
        this.publisherName = publisherName;
        this.title = title;
        this.authorName = authorName;
        this.pages = pages;
        this.publishedDate = publishedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


    
}
