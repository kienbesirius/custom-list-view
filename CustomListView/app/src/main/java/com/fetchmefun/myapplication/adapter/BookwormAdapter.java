package com.fetchmefun.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.model.BookWorm;

import java.util.ArrayList;

public class BookwormAdapter extends ArrayAdapter<BookWorm> {
    private Context context;
    private ArrayList<BookWorm> mListBookworm;

    public BookwormAdapter(@NonNull Context context, ArrayList<BookWorm> mListBookworm) {
        super(context, R.layout.item_bookworm, mListBookworm);
        this.context = context;
        this.mListBookworm = mListBookworm;
    }

    private static class BookwormHolder  extends ViewModel {
        @SuppressLint("StaticFieldLeak")
        private ImageView imageView;
        @SuppressLint("StaticFieldLeak")
        private TextView tv;
        @SuppressLint("StaticFieldLeak")
        private CheckBox checkBox;

        public BookwormHolder() {
        }

        public BookwormHolder(ImageView imageView, TextView tv, CheckBox checkBox) {
            this.imageView = imageView;
            this.tv = tv;
            this.checkBox = checkBox;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTv() {
            return tv;
        }

        public void setTv(TextView tv) {
            this.tv = tv;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BookWorm mBookworm = mListBookworm.get(position);
        BookwormHolder bookwormHolder = new BookwormHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_bookworm, parent, false);
            bookwormHolder.imageView = convertView.findViewById(R.id.imageView_sex);
            bookwormHolder.tv = convertView.findViewById(R.id.textView_info_bookworm);
            bookwormHolder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(bookwormHolder);
        } else {
            bookwormHolder = (BookwormHolder) convertView.getTag();
        }
        String infoBookworm = mBookworm.getWormId() + " - " + mBookworm.getWormName();
        bookwormHolder.tv.setText(infoBookworm);
        bookwormHolder.checkBox.setChecked(mBookworm.isChecked());
        bookwormHolder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mBookworm.setChecked(isChecked);
            Toast.makeText(context, infoBookworm + (mBookworm.isWormSex() ? " Nam" : " Nữ"), Toast.LENGTH_SHORT).show();
        });
        if (mBookworm.isWormSex()) {
            // Male;
            Glide.with(convertView).load(R.drawable.ic_male).into(bookwormHolder.imageView);
        } else {
            Glide.with(convertView).load(R.drawable.ic_female).into(bookwormHolder.imageView);
        }
        return convertView;
    }
}
