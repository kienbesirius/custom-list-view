package com.fetchmefun.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.model.Book;
import com.fetchmefun.myapplication.model.BookWorm;
import com.fetchmefun.myapplication.model.NationalFlag;

public abstract class BaseActivity extends AppCompatActivity {

    protected MaterialDialog progressDialog;
    protected MaterialDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Build 2 type Dialog
        createProgressDialog();
        createAlertDialog();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void startActivity(Context c, Class<?> clazz){
        Intent i = new Intent(c,clazz);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(i);
    }

    public void startActivity(Context c, Class<?> clazz, Bundle bundle){
        Intent i = new Intent(c,clazz);
        i.putExtras(bundle);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(i);
    }
    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void createProgressDialog() {
        progressDialog = new MaterialDialog.Builder(this)
                .content(R.string.msg_waiting)
                .progress(true, 10)
                .build();
    }

    public void showProgressDialog(boolean value){
        if(value){
            if(progressDialog != null && !progressDialog.isShowing()){
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        }else{
            if(progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }

    public void dismissDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        if(alertDialog != null && alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }

    public void setCancelProgress(boolean isCancel){
        if(progressDialog != null){
            progressDialog.setCancelable(isCancel);
        }
    }

    public void createAlertDialog() {
        alertDialog = new MaterialDialog.Builder(this)
                .title(R.string.holder)
                .positiveText(R.string.action_ok)
                .cancelable(true)
                .build();
    }

    public void setCancelAlert(boolean isCancel){
        if(alertDialog != null){
            alertDialog.setCancelable(isCancel);
        }
    }
    public void showAlertDialog(String content){
        alertDialog.setContent(content);
        alertDialog.show();
    }

    public void showAlertDialog(@StringRes int resourceId){
        alertDialog.setContent(resourceId);
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        if(alertDialog != null && alertDialog.isShowing()){
            alertDialog.dismiss();
        }
        if(progressDialog != null && progressDialog.isShowing()){
            alertDialog.dismiss();
        }
        super.onDestroy();
    }

    public abstract void onRoomModifier(NationalFlag model, int ACTION);
    public abstract void onRoomModifier(Book model, int ACTION);
    public abstract void onRoomModifier(BookWorm model, int ACTION);
}
