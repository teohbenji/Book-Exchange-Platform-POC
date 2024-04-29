package com.example.bookplace;

import android.content.Intent;
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

public class ListBookPage extends AppCompatActivity implements GoogleBooksDataListener,
                                                    BookAdapter.BookClickListener{
    private static ArrayList<Book> bookArrayList = new ArrayList<>();

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
    public void onBooksDataReceived(ArrayList<Book> bookArrayList) {
        // Handle received book data here
        Log.d("ListBookPage", "Received books: " + bookArrayList.size());

        RecyclerView recyclerViewBooks = findViewById(R.id.recyclerViewBooks);

        ListBookPage.bookArrayList = bookArrayList;

        // Initialize adapter, and set vertical orientation
        BookAdapter bookAdapter = new BookAdapter(ListBookPage.this, bookArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListBookPage.this, LinearLayoutManager.VERTICAL, false);

        // Set layout manager and adapter to the recycler view
        recyclerViewBooks.setLayoutManager(linearLayoutManager);
        recyclerViewBooks.setAdapter(bookAdapter);
    }

    @Override
    public void onBookClick(int position) {
        // Handle click events for individual books here
        Book clickedBook = bookArrayList.get(position);
        Log.d("ListBookPage", "onBookClick " + clickedBook.getTitle());
        handleBookClick(clickedBook);
    }

    private void handleBookClick(Book clickedBook) {
        Intent intent = new Intent(ListBookPage.this, ViewBookPage.class);
        intent.putExtra("clicked_book", clickedBook);
        startActivity(intent);
    }
}
