package com.fetchmefun.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.model.Book;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private ArrayList<Book> mListBook;

    public BookAdapter(@NonNull Context context, ArrayList<Book> mListBook) {
        super(context, R.layout.item_book, mListBook);
        this.context = context;
        this.mListBook = mListBook;
    }

    private static class BookViewHolder {
        ImageView imageView;
        TextView tv_name;
        TextView tv_type;

        public BookViewHolder() {
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTv_name() {
            return tv_name;
        }

        public void setTv_name(TextView tv_name) {
            this.tv_name = tv_name;
        }

        public TextView getTv_type() {
            return tv_type;
        }

        public void setTv_type(TextView tv_type) {
            this.tv_type = tv_type;
        }

        public BookViewHolder(ImageView imageView, TextView tv_name, TextView tv_type) {
            this.imageView = imageView;
            this.tv_name = tv_name;
            this.tv_type = tv_type;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book mBook = mListBook.get(position);
        BookViewHolder bookViewHolder = new BookViewHolder();

        final View view;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_book, parent, false);

            bookViewHolder.imageView = convertView.findViewById(R.id.imageView_book_ic);
            bookViewHolder.tv_name = convertView.findViewById(R.id.textView_name_book);
            bookViewHolder.tv_type = convertView.findViewById(R.id.textView_type);

            convertView.setTag(bookViewHolder);
        } else {
            bookViewHolder = (BookViewHolder) convertView.getTag();
        }
        bookViewHolder.tv_name.setText(mBook.getName());
        bookViewHolder.tv_type.setText(mBook.getType());
        Glide.with(convertView).load(mBook.getImgBook()).into(bookViewHolder.imageView);

        return convertView;
    }
}
