package com.example.bookplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookplace.Model.GoogleBooksApi;

public class ListBookPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_book_page);

        Button buttonSearch = findViewById(R.id.buttonSearch);
        ImageView imageViewSmallThumbnail = findViewById(R.id.imageViewSmallThumbnail);
        String smallThumbnailUrl = "https://books.google.com/books/content?id=JHEkAQAAMAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api";
        Glide.with(this).load(smallThumbnailUrl).into(imageViewSmallThumbnail);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleBooksApi googleBooksApi = new GoogleBooksApi();
                googleBooksApi.getData(getApplicationContext(), "Da Vinci code");
            }
        });
    }


}
