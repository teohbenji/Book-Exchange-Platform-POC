package com.example.bookplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookplace.Model.Book;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Book> bookList;

    // Constructor
    public BookAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set data to TextView and ImageView of each card layout
        Book book = bookList.get(position);
        holder.textViewBookName.setText(book.getTitle());
        holder.textViewAuthorNames.setText(book.getAuthors());
        Glide.with(context).load(book.getSmallThumbnail()).into(holder.imageViewSmallThumbnail);
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the RecyclerView
        return bookList.size();
    }

    // ViewHolder class for initializing views such as TextView and ImageView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewSmallThumbnail;
        private final TextView textViewBookName;
        private final TextView textViewAuthorNames;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSmallThumbnail = itemView.findViewById(R.id.imageViewSmallThumbnail);
            textViewBookName = itemView.findViewById(R.id.textViewBookName);
            textViewAuthorNames = itemView.findViewById(R.id.textViewAuthorNames);
        }
    }
}

