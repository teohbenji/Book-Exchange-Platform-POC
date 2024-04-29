package com.example.bookplace.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookListingDB{
    private SQLiteDatabase database;
    private BookListingDatabaseHelper dbHelper;

    public BookListingDB(Context context) {
        dbHelper = new BookListingDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addBookListing(BookListing bookListing) {
        ContentValues values = new ContentValues();
        values.put(BookListingSchema.BookListingEntry.COLUMN_TITLE, bookListing.getTitle());
        values.put(BookListingSchema.BookListingEntry.COLUMN_AUTHORS, bookListing.getAuthors());
        values.put(BookListingSchema.BookListingEntry.COLUMN_PUBLISHED_DATE, bookListing.getPublishedDate());
        values.put(BookListingSchema.BookListingEntry.COLUMN_DESCRIPTION, bookListing.getDescription());
        values.put(BookListingSchema.BookListingEntry.COLUMN_SMALL_THUMBNAIL, bookListing.getSmallThumbnail());
        values.put(BookListingSchema.BookListingEntry.COLUMN_THUMBNAIL, bookListing.getThumbnail());
        values.put(BookListingSchema.BookListingEntry.COLUMN_USERNAME, bookListing.getUsername());
        values.put(BookListingSchema.BookListingEntry.COLUMN_LOCATION, bookListing.getLocation());

        return database.insert(BookListingSchema.BookListingEntry.TABLE_NAME, null, values);
    }

    public List<BookListing> getAllBookListings() {
        List<BookListing> bookListings = new ArrayList<>();

        Cursor cursor = database.query(
                BookListingSchema.BookListingEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int titleIndex = cursor.getColumnIndex(BookListingSchema.BookListingEntry.COLUMN_TITLE);
            int authorsIndex = cursor.getColumnIndex(BookListingSchema.BookListingEntry.COLUMN_AUTHORS);
            int publishedDateIndex = cursor.getColumnIndex(BookListingSchema.BookListingEntry.COLUMN_PUBLISHED_DATE);
            int descriptionIndex = cursor.getColumnIndex(BookListingSchema.BookListingEntry.COLUMN_DESCRIPTION);
            int smallThumbnailIndex = cursor.getColumnIndex(BookListingSchema.BookListingEntry.COLUMN_SMALL_THUMBNAIL);
            int thumbnailIndex = cursor.getColumnIndex(BookListingSchema.BookListingEntry.COLUMN_THUMBNAIL);
            int usernameIndex = cursor.getColumnIndex(BookListingSchema.BookListingEntry.COLUMN_USERNAME);
            int locationIndex = cursor.getColumnIndex(BookListingSchema.BookListingEntry.COLUMN_LOCATION);

            // Check if column exists in the cursor
            if (titleIndex != -1 && authorsIndex != -1 && publishedDateIndex != -1 && descriptionIndex != -1 &&
                    smallThumbnailIndex != -1 && thumbnailIndex != -1 && usernameIndex != -1 && locationIndex != -1) {

                String title = cursor.getString(titleIndex);
                String authors = cursor.getString(authorsIndex);
                String publishedDate = cursor.getString(publishedDateIndex);
                String description = cursor.getString(descriptionIndex);
                String smallThumbnail = cursor.getString(smallThumbnailIndex);
                String thumbnail = cursor.getString(thumbnailIndex);
                String username = cursor.getString(usernameIndex);
                String location = cursor.getString(locationIndex);

                BookListing bookListing = new BookListing(title, authors, publishedDate, description, smallThumbnail,
                        thumbnail, username, location);
                bookListings.add(bookListing);
            }
        }


        cursor.close();
        return bookListings;
    }

    // Add other CRUD operations as needed
}
