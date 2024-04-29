package com.example.bookplace.Model;

import android.provider.BaseColumns;

public class BookListingSchema {
    private BookListingSchema() {}

    public static class BookListingEntry implements BaseColumns {
        public static final String TABLE_NAME = "book_listings";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AUTHORS = "authors";
        public static final String COLUMN_PUBLISHED_DATE = "published_date";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_SMALL_THUMBNAIL = "small_thumbnail";
        public static final String COLUMN_THUMBNAIL = "thumbnail";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_LOCATION = "location";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_TITLE + " TEXT," +
                        COLUMN_AUTHORS + " TEXT," +
                        COLUMN_PUBLISHED_DATE + " TEXT," +
                        COLUMN_DESCRIPTION + " TEXT," +
                        COLUMN_SMALL_THUMBNAIL + " TEXT," +
                        COLUMN_THUMBNAIL + " TEXT," +
                        COLUMN_USERNAME + " TEXT," +
                        COLUMN_LOCATION + " TEXT)";

        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
