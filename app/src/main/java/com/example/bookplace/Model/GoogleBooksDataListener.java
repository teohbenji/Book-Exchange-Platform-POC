package com.example.bookplace.Model;

import java.util.ArrayList;

public interface GoogleBooksDataListener {
    void onBooksDataReceived(ArrayList<Book> books);
}