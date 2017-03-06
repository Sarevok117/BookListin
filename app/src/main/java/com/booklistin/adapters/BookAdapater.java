package com.booklistin.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.booklistin.R;
import com.booklistin.models.Book;
import com.booklistin.utils.ServiceSingleton;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Varkiil on 06/03/2017.
 */

public class BookAdapater extends RecyclerView.Adapter<BookAdapater.ViewHolder> {
    Context context;
    List<Book> values;

    public BookAdapater(Context context, List<Book> books) {
//        super(context, R.layout.row_book, books);
        this.context = context;
        values = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_book, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.tv_title.setText(values.get(position).getTitle());
        if (values.get(position).getAuthors().size() > 0) {
            String authors = values.get(position).getAuthors().get(0).getName();
            viewHolder.tv_author.setText(authors);
        }
        Picasso
                .with(viewHolder.iv_book.getContext())
                .load(values.get(position).getImage())
                .into(viewHolder.iv_book);
    }


    @Override
    public int getItemCount() {
        if (values != null)
            return values.size();
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_book;
        private TextView tv_title;
        private TextView tv_author;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_book = (ImageView) itemView.findViewById(R.id.book_image);
            this.tv_author = (TextView) itemView.findViewById(R.id.author);
            this.tv_title = (TextView) itemView.findViewById(R.id.title);
        }
    }

}
