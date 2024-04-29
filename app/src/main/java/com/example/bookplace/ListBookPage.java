package com.example.bookplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookplace.Model.GoogleBooksApi;

public class ListBookPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_book_page);

        Button buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleBooksApi googleBooksApi = new GoogleBooksApi();
                googleBooksApi.getData(getApplicationContext(), "Beautiful Creatures");

                //RecylerView recyclerViewBooks = findViewById(R.id.recyclerViewBooks);
            }
        });
    }


}
