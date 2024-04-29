package com.example.bookplace.Model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class GoogleBooksApi {
    private NetworkRequestHandler mRequestHandler;
    private String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    private String API_KEY = "AIzaSyBJDgOE6twnM9XnMYgpTpYbyMrZLidhNfI";

   // public void getData(Context context, String title, String author, String publisher) {
    public void getGoogleBooksData(Context context, String title, final GoogleBooksDataListener googleBooksDataListener) {

        String searchTerms = "+intitle:" + title;
        String url = baseUrl + searchTerms + "&key=" + API_KEY;

        mRequestHandler = new NetworkRequestHandler(context);
        mRequestHandler.makeStringRequest(url, new NetworkRequestHandler.NetworkRequestListener<String>() {
            @Override
            public void onSuccess(String response) {
                // Handle successful response
                googleBooksDataListener.onBooksDataReceived(parseBooksResponse(response));
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("NetworkRequestHandler", "Error: " + errorMessage);

                String toastMessage = "HTTP Error: " + errorMessage;
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
                googleBooksDataListener.onBooksDataReceived(new ArrayList<>());
            }
        });
    }

    private ArrayList<Book> parseBooksResponse(String response) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        if (jsonObject.has("items")) {
            JsonArray itemsArray = jsonObject.getAsJsonArray("items");
            for (JsonElement itemElement : itemsArray) {
                if (bookArrayList.size() >= 5) break; // Limit to top 5 results

                JsonObject volumeInfo = itemElement.getAsJsonObject().getAsJsonObject("volumeInfo");

                String[] authorsArr = gson.fromJson(volumeInfo.getAsJsonArray("authors"), String[].class);

                StringBuilder authorsStrBuilder = new StringBuilder();

                if (authorsArr != null) {
                    for (int i = 0; i < authorsArr.length; i++) {
                        authorsStrBuilder.append(authorsArr[i]);
                        if (i < authorsArr.length - 1) {
                            authorsStrBuilder.append(", ");
                        }
                    }
                }
                String authorsStr = authorsStrBuilder.toString();
                String titleStr = volumeInfo.get("title").getAsString();
                String publishedDateStr = volumeInfo.get("publishedDate").getAsString();
                String descriptionStr = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "";

                JsonObject imageLinks = volumeInfo.getAsJsonObject("imageLinks");
                String smallThumbnailStr, thumbnailStr;
                if (imageLinks != null) {
                    smallThumbnailStr = imageLinks.get("smallThumbnail").getAsString();
                    thumbnailStr = imageLinks.get("thumbnail").getAsString();
                } else {
                    smallThumbnailStr = "@drawable/image_not_found";
                    thumbnailStr = "@drawable/image_not_found";
                }

                Book book = new Book(titleStr, authorsStr, publishedDateStr, descriptionStr, smallThumbnailStr, thumbnailStr);

                bookArrayList.add(book);
            }
        }
        return bookArrayList;
    }

}