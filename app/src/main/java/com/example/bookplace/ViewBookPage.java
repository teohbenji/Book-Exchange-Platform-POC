package com.example.bookplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookplace.Model.Book;
import com.example.bookplace.Model.BookListing;
import com.example.bookplace.Model.BookListingDB;
import com.example.bookplace.Model.GoogleBooksApi;

public class ViewBookPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_book_page);

        // Receive the Book object from the intent
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("clicked_book");

        ImageView imageViewThumbnail = findViewById(R.id.imageViewThumbnail);
        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewAuthor = findViewById(R.id.textViewAuthor);
        TextView textViewPublished = findViewById(R.id.textViewPublished);
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        Button buttonListBook = findViewById(R.id.buttonListBook);

        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthors());
        textViewPublished.setText(book.getPublishedDate());
        textViewDescription.setText(book.getDescription());
        Glide.with(this).load(book.getThumbnail()).into(imageViewThumbnail);

        buttonListBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookListing bookListing = new BookListing(
                        book.getTitle(),
                        book.getAuthors(),
                        book.getPublishedDate(),
                        book.getDescription(),
                        book.getSmallThumbnail(),
                        book.getThumbnail(),
                        "Luka", //TODO: default value
                        "Singapore" //TODO: default value
                );

                // Open the database
                BookListingDB dataSource = new BookListingDB(ViewBookPage.this);
                dataSource.open();

                // Add the book listing to the database
                long insertedId = dataSource.addBookListing(bookListing);

                // Close the database
                dataSource.close();

                // Check if the book listing was successfully added
                if (insertedId != -1) {
                    Toast.makeText(ViewBookPage.this, book.getTitle() + " successfully listed!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewBookPage.this, "Failed to list " + book.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}