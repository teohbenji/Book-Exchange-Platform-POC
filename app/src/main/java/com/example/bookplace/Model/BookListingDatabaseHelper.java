package com.example.bookplace.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookListingDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "book_listing_database";
    private static final int DATABASE_VERSION = 1;

    public BookListingDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookListingSchema.BookListingEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BookListingSchema.BookListingEntry.DELETE_TABLE);
        onCreate(db);
    }
}


