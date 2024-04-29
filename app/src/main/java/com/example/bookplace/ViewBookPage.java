package com.example.bookplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookplace.Model.Book;
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

        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthors());
        textViewPublished.setText(book.getPublishedDate());
        textViewDescription.setText(book.getDescription());
        Glide.with(this).load(book.getThumbnail()).into(imageViewThumbnail);

    }
}