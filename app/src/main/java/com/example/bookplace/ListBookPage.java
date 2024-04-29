package com.example.bookplace;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookplace.Model.Book;
import com.example.bookplace.Model.GoogleBooksApi;
import com.example.bookplace.Model.GoogleBooksDataListener;

import java.util.ArrayList;

public class ListBookPage extends AppCompatActivity implements GoogleBooksDataListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_book_page);

        Button buttonSearch = findViewById(R.id.buttonSearch);
        EditText editTextTitle = findViewById(R.id.editTextTitle);
        EditText editTextAuthor = findViewById(R.id.editTextAuthor);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get data from GoogleBooksApi
                GoogleBooksApi googleBooksApi = new GoogleBooksApi();
                String titleStr = editTextTitle.getText().toString();
                String authorStr = editTextAuthor.getText().toString();
                googleBooksApi.getGoogleBooksData(getApplicationContext(), titleStr, authorStr, ListBookPage.this);
            }
        });
    }

    @Override
    public void onBooksDataReceived(ArrayList<Book> books) {
        // Handle received book data here
        Log.d("ListBookPage", "Received books: " + books.size());

        RecyclerView recyclerViewBooks = findViewById(R.id.recyclerViewBooks);

        // Initialize adapter, and set vertical orientation
        BookAdapter bookAdapter = new BookAdapter(ListBookPage.this, books);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListBookPage.this, LinearLayoutManager.VERTICAL, false);

        // Set layout manager and adapter to the recycler view
        recyclerViewBooks.setLayoutManager(linearLayoutManager);
        recyclerViewBooks.setAdapter(bookAdapter);
    }
}
