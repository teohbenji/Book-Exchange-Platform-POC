package com.example.bookplace.Model;

import com.example.bookplace.Model.Book;
import com.example.bookplace.Model.BookListing;

import java.util.ArrayList;
import java.util.List;

public class BookConverter {

    public static ArrayList<Book> convertBookListingsToBooks(List<BookListing> bookListings) {
        ArrayList<Book> books = new ArrayList<>();
        for (BookListing bookListing : bookListings) {
            // Convert each BookListing to a Book
            Book book = new Book(
                    bookListing.getTitle(),
                    bookListing.getAuthors(),
                    bookListing.getPublishedDate(),
                    bookListing.getDescription(),
                    bookListing.getSmallThumbnail(),
                    bookListing.getThumbnail()
            );
            books.add(book);
        }
        return books;
    }
}
