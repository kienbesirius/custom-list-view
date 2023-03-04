package com.fetchmefun.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.adapter.NationalFlagAdapter;
import com.fetchmefun.myapplication.database.DataBaseModifier;
import com.fetchmefun.myapplication.model.NationalFlag;

import java.util.ArrayList;

public class SubSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_second);
        ListView lv = findViewById(R.id.subListViewSecond);
        DataBaseModifier dataBaseModifier = DataBaseModifier.getInstance(this);
        ArrayList<NationalFlag> list = (ArrayList<NationalFlag>) dataBaseModifier.getNationalFlagDAO().selectAllNationalFlag();
        NationalFlagAdapter nationalFlagAdapter = new NationalFlagAdapter(this, list);
        lv.setAdapter(nationalFlagAdapter);
    }
}