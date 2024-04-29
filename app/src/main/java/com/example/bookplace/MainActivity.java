package com.example.bookplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bookplace.Model.GoogleBooksApi;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonListABook = findViewById(R.id.buttonListABook);
        Button buttonBrowseBooks = findViewById(R.id.buttonBrowseBooks);

        buttonListABook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListBookPage.class);
                startActivity(intent);
            }
        });

        buttonBrowseBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBookListingsPage.class);
                startActivity(intent);
            }
        });
    }

}