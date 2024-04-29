package com.example.bookexchangeplatformpoc.Model;

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
    public void getData(Context context, String title) {

        String searchTerms = "+intitle:" + title;
        String url = baseUrl + searchTerms + "&key=" + API_KEY;

        mRequestHandler = new NetworkRequestHandler(context);
        mRequestHandler.makeStringRequest(url, new NetworkRequestHandler.NetworkRequestListener<String>() {
            @Override
            public void onSuccess(String response) {
                // Handle successful response
                List<Book> booksArrayList = parseBooksResponse(response);
                displayBooks(context, booksArrayList);
            }

            @Override
            public void onError(String errorMessage) {
                // Handle error
                Log.e("NetworkRequestHandler", "Error: " + errorMessage);
            }
        });
    }

    private List<Book> parseBooksResponse(String response) {
        List<Book> bookArrayList = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        if (jsonObject.has("items")) {
            JsonArray itemsArray = jsonObject.getAsJsonArray("items");
            for (JsonElement itemElement : itemsArray) {
                if (bookArrayList.size() >= 1) break; // Limit to top result
                Book book = new Book();
                JsonObject volumeInfo = itemElement.getAsJsonObject().getAsJsonObject("volumeInfo");

                book.title = volumeInfo.get("title").getAsString();
                book.authors = gson.fromJson(volumeInfo.getAsJsonArray("authors"), String[].class);
                book.publishedDate = volumeInfo.get("publishedDate").getAsString();
                book.description = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "";

                JsonObject imageLinks = volumeInfo.getAsJsonObject("imageLinks");
                if (imageLinks != null) {
                    book.smallThumbnail = imageLinks.get("smallThumbnail").getAsString();
                    book.thumbnail = imageLinks.get("thumbnail").getAsString();
                }

                bookArrayList.add(book);
            }
        }
        return bookArrayList;
    }

    private void displayBooks(Context context, List<Book> books) {
        for (Book book : books) {
            // Display book information
            String message = "Title: " + book.title + "\n" +
                    "Authors: " + String.join(", ", book.authors) + "\n" +
                    "Published Date: " + book.publishedDate + "\n" +
                    "Description: " + book.description + "\n" +
                    "Small Thumbnail: " + book.smallThumbnail + "\n" +
                    "Thumbnail: " + book.thumbnail;
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}