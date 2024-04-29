package com.example.bookplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookplace.Model.BookListing;
import com.example.bookplace.Model.BookListingDB;

import java.util.List;

public class AllBookListingsPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_book_listings);

        TextView textViewAllBookListings = findViewById(R.id.textViewAllBookListings);
        Button buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Retrieve all book listings from the database
                BookListingDB dataSource = new BookListingDB(AllBookListingsPage.this);
                dataSource.open();
                List<BookListing> allBookListings = dataSource.getAllBookListings();
                dataSource.close();

                // Build a string containing names of all books
                StringBuilder stringBuilder = new StringBuilder();
                for (BookListing bookListing : allBookListings) {
                    stringBuilder.append(bookListing.getTitle()).append("\n");
                }

                // Update textViewAllBookListings with the names of all books
                textViewAllBookListings.setText(stringBuilder.toString());
            }
        });
    }
}