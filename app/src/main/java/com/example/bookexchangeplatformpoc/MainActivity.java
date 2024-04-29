package com.example.bookexchangeplatformpoc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGetData = findViewById(R.id.buttonGetData);

        // Set OnClickListener for the button
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleBooksApi googleBooksApi = new GoogleBooksApi();
                googleBooksApi.getData(getApplicationContext()); // Pass the application context
            }
        });
    }
}