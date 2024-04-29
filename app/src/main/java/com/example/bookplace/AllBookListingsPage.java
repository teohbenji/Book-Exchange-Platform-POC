package com.example.bookplace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookplace.Model.Book;
import com.example.bookplace.Model.BookConverter;
import com.example.bookplace.Model.BookListing;
import com.example.bookplace.Model.BookListingDB;

import java.util.ArrayList;

public class AllBookListingsPage extends AppCompatActivity implements BookAdapter.BookClickListener{
    private ArrayList<Book> bookArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_book_listings);

        // Retrieve all book listings from the database
        BookListingDB bookListingDB = new BookListingDB(AllBookListingsPage.this);
        bookListingDB.open();
        ArrayList<BookListing> allBookListings = bookListingDB.getAllBookListings();
        bookListingDB.close();

        // Log retrieved book listings
        StringBuilder stringBuilder = new StringBuilder();
        for (BookListing bookListing : allBookListings) {
            stringBuilder.append(bookListing.getTitle()).append("\n");
        }
        Log.d("AllBookListingsPage",  "List of books: " + stringBuilder.toString());

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Convert BookListing objects to Books
        ArrayList<Book> booksList = BookConverter.convertBookListingsToBooks((allBookListings));

        //For onBookClick
        bookArrayList = booksList;

        // Create adapter and set it to RecyclerView
        BookAdapter adapter = new BookAdapter(this, booksList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBookClick(int position) {
        // Handle click events for individual books here
        Book clickedBook = bookArrayList.get(position);
        Log.d("ListBookPage", "onBookClick " + clickedBook.getTitle());
        handleBookClick(clickedBook);
    }

    private void handleBookClick(Book clickedBook) {
        Intent intent = new Intent(AllBookListingsPage.this, ViewBookPage.class);
        intent.putExtra("clicked_book", clickedBook);
        startActivity(intent);
    }
}