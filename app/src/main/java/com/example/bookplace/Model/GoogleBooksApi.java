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

    private void displayBooks(Context context, List<Book> books) {
        for (Book book : books) {
            // Display book information
            String message = "Title: " + book.getTitle() + "\n" +
                    "Authors: " + String.join(", ", book.getAuthors()) + "\n" +
                    "Published Date: " + book.getPublishedDate() + "\n" +
                    "Description: " + book.getDescription() + "\n" +
                    "Small Thumbnail: " + book.getSmallThumbnail() + "\n" +
                    "Thumbnail: " + book.getThumbnail();
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}