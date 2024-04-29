package com.example.bookplace;

import android.annotation.SuppressLint;
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

    private BookClickListener clickListener;

    // Constructor
    public BookAdapter(Context context, ArrayList<Book> bookList, BookClickListener clickListener) {
        this.context = context;
        this.bookList = bookList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Set data to TextView and ImageView of each card layout
        Book book = bookList.get(position);
        holder.textViewBookName.setText(book.getTitle());
        holder.textViewAuthorNames.setText(book.getAuthors());
        Glide.with(context).load(book.getSmallThumbnail()).into(holder.imageViewSmallThumbnail);

        //Set onclickListener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onBookClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the RecyclerView
        return bookList.size();
    }

    // ViewHolder class for initializing views such as TextView and ImageView
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ImageView imageViewSmallThumbnail;
        private final TextView textViewBookName;
        private final TextView textViewAuthorNames;

        private BookClickListener clickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSmallThumbnail = itemView.findViewById(R.id.imageViewSmallThumbnail);
            textViewBookName = itemView.findViewById(R.id.textViewBookName);
            textViewAuthorNames = itemView.findViewById(R.id.textViewAuthorNames);

            // Set the click listener for the entire item view
            itemView.setOnClickListener(this);

            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            // Handle click event
            if (clickListener != null) {
                // Pass the clicked book to the listener
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onBookClick(position);
                }
            }
        }
    }

    public interface BookClickListener {
        void onBookClick(int position);
    }

}

