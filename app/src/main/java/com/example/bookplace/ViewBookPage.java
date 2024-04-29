package com.example.bookplace;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bookplace.Model.GoogleBooksApi;

public class ViewBookPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_book_page);

        Button buttonGetData = findViewById(R.id.buttonGetData);

        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleBooksApi googleBooksApi = new GoogleBooksApi();
                googleBooksApi.getData(getApplicationContext(), "Da Vinci code");
            }
        });
    }
}