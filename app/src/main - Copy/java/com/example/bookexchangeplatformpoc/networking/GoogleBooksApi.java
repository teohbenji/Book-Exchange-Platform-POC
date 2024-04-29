package com.example.bookexchangeplatformpoc.networking;

import java.net.URI;

import java.io.IOException;


public class GoogleBooksApiClient {

    private static final String API_KEY = "your-api-key";

    public static void main(String[] args) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.googleapis.com/books/v1/volumes?q=search+terms&key=" + API_KEY))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (response != null && response.statusCode() == 200) {
            System.out.println(response.body());
        } else {
            System.out.println("Failed to fetch data. Response code: " + (response != null ? response.statusCode() : "null"));
        }
    }
}
