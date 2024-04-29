package com.example.bookexchangeplatformpoc;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;

public class GoogleBooksApi {
    private NetworkRequestHandler mRequestHandler;
    private String url = "https://catfact.ninja/fact";

    public void getData(Context context) {
        mRequestHandler = new NetworkRequestHandler(context);
        mRequestHandler.makeStringRequest(url, new NetworkRequestHandler.NetworkRequestListener<String>() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(context, "Response :" + response, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("NetworkRequestHandler", "Error: " + errorMessage);
            }
        });
    }
}