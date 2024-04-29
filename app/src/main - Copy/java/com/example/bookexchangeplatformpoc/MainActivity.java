package com.example.bookexchangeplatformpoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bookexchangeplatformpoc.networking.NetworkChecker;

public class MainActivity extends AppCompatActivity {
    private NetworkChecker networkChecker;

    public NetworkChecker getNetworkChecker(Context context) {
        if (networkChecker == null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            networkChecker = new NetworkChecker(connectivityManager);
        }
        return networkChecker;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}