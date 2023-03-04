package com.fetchmefun.myapplication.activity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.database.DataBaseModifier;
import com.fetchmefun.myapplication.model.Book;
import com.fetchmefun.myapplication.model.BookWorm;
import com.fetchmefun.myapplication.model.NationalFlag;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DataBaseModifier.getInstance(this).getBookDAO().selectAllBook();
        startSplash();
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

    private void startSplash() {
        showProgressDialog(true);
        Handler h = new Handler();
        h.postDelayed(() -> {
            startActivity(this, MainActivity.class);
            finish();
        }, 2000);
    }
}