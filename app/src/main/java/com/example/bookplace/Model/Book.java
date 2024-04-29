package com.example.bookplace.Model;

public class Book {
    private String title;
    private String authors;
    private String publishedDate;
    private String description;
    private String smallThumbnail;
    private String thumbnail;

    public Book(String title, String authors, String publishedDate, String description, String smallThumbnail, String thumbnail) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
