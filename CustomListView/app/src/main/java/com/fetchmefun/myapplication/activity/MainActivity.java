package com.fetchmefun.myapplication.activity;

import android.os.Bundle;
import android.view.View;

import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.model.Book;
import com.fetchmefun.myapplication.model.BookWorm;
import com.fetchmefun.myapplication.model.NationalFlag;

public class MainActivity extends BaseActivity {

    private View first;
    private View second;
    private View third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        set();
    }

    @Override
    public void onRoomModifier(NationalFlag model, int ACTION) {

    }

    @Override
    public void onRoomModifier(Book model, int ACTION) {

    }

    @Override
    public void onRoomModifier(BookWorm model, int ACTION) {

    }

    private void set(){
        first.setOnClickListener(v -> {
            showToast(getString(R.string.label_first_exe));
            startActivity(MainActivity.this,FirstActivity.class);
        });
        second.setOnClickListener(v -> {
            showToast(getString(R.string.label_second_exe));
            startActivity(MainActivity.this,SecondActivity.class);
        });
        third.setOnClickListener(v -> {
            showToast(getString(R.string.label_third_exe));
            startActivity(MainActivity.this,ThirdActivity.class);
        });
    }
    private void init(){
        first = findViewById(R.id.btn_first_exe);
        second = findViewById(R.id.btn_second_exe);
        third = findViewById(R.id.btn_third_exe);
    }
}