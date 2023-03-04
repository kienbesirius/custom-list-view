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
import com.fetchmefun.myapplication.model.NationalFlag;

import java.util.ArrayList;

public class NationalFlagAdapter extends ArrayAdapter<NationalFlag> {
    private Context context;
    private ArrayList<NationalFlag> mListNationalFlag;

    public NationalFlagAdapter(@NonNull Context context, ArrayList<NationalFlag> mList) {
        super(context, R.layout.item_nation_flag, mList);
        this.context = context;
        this.mListNationalFlag = mList;
    }

    private static class NationalFlagHolder {
        private ImageView imageView;
        private TextView tv_nation_name, tv_currency;

        public NationalFlagHolder(ImageView imageView, TextView tv_nation_name, TextView tv_currency) {
            this.imageView = imageView;
            this.tv_nation_name = tv_nation_name;
            this.tv_currency = tv_currency;
        }

        public NationalFlagHolder() {
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTv_nation_name() {
            return tv_nation_name;
        }

        public void setTv_nation_name(TextView tv_nation_name) {
            this.tv_nation_name = tv_nation_name;
        }

        public TextView getTv_currency() {
            return tv_currency;
        }

        public void setTv_currency(TextView tv_currency) {
            this.tv_currency = tv_currency;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NationalFlag mFlag = mListNationalFlag.get(position);
        NationalFlagHolder nationalFlagHolder = new NationalFlagHolder();

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_nation_flag, parent, false);
            nationalFlagHolder.imageView = convertView.findViewById(R.id.imageView_nation_flag);
            nationalFlagHolder.tv_nation_name = convertView.findViewById(R.id.textView_nation_name);
            nationalFlagHolder.tv_currency = convertView.findViewById(R.id.textView_currency);
            convertView.setTag(nationalFlagHolder);
        } else {
            nationalFlagHolder = (NationalFlagHolder) convertView.getTag();
        }
        nationalFlagHolder.tv_nation_name.setText(mFlag.getNation());
        nationalFlagHolder.tv_currency.setText(mFlag.getCurrency());
        Glide.with(convertView).load(mFlag.getImgFlag()).into(nationalFlagHolder.imageView);
        return convertView;
    }
}
