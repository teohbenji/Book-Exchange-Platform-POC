package com.example.bookplace.Model;

public class BookListing extends Book{
    private String username;
    private String location;

    public BookListing(String title, String authors, String publishedDate, String description,
                       String smallThumbnail, String thumbnailString, String username, String location){
        super(title, authors, publishedDate, description, smallThumbnail, thumbnailString);
        this.username = username;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
