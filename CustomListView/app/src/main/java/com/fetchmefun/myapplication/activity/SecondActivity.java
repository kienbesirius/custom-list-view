package com.fetchmefun.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.adapter.NationalFlagAdapter;
import com.fetchmefun.myapplication.database.DataBaseModifier;
import com.fetchmefun.myapplication.model.Book;
import com.fetchmefun.myapplication.model.BookWorm;
import com.fetchmefun.myapplication.model.NationalFlag;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SecondActivity extends BaseActivity {

    private Button btn_add, btn_view, btn_update;
    private EditText edt_nation_name, edt_currency;
    private ListView listView;
    private NationalFlagAdapter nationalFlagAdapter;
    private ArrayList<NationalFlag> list;
    private DataBaseModifier dataBaseModifier = DataBaseModifier.getInstance(this);
    private int imgId;
    private int mId;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mapIdToView();
        setupViews();
        setButton();
    }

    @Override
    public void onRoomModifier(NationalFlag model,int ACTION) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        service.execute(() -> {
            try{
                if(ACTION == DataBaseModifier.ACTION_INSERT){
                    dataBaseModifier.getNationalFlagDAO().insertNationalFlag(model);
                    list.clear();
                    list.addAll(dataBaseModifier.getNationalFlagDAO().selectAllNationalFlag());
                }else if(ACTION == DataBaseModifier.ACTION_DELETE){
                    dataBaseModifier.getNationalFlagDAO().deleteNationalFlag(model);
                    list.clear();
                    list.addAll(dataBaseModifier.getNationalFlagDAO().selectAllNationalFlag());
                }else if(ACTION == DataBaseModifier.ACTION_UPDATE){
                    dataBaseModifier.getNationalFlagDAO().updateNationalFlag(model);
                    list.clear();
                    list.addAll(dataBaseModifier.getNationalFlagDAO().selectAllNationalFlag());
                }else if(ACTION == DataBaseModifier.ACTION_SELECT_ONE){

                }else if(ACTION == DataBaseModifier.ACTION_SELECT_ALL) {
                }
            }catch (Exception e){
                Log.i("Keep Lost error", e.getMessage());
                e.printStackTrace();
            }

            /*

             */
            handler.post(() -> {
                nationalFlagAdapter.notifyDataSetChanged();
            });
        });
    }

    @Override
    public void onRoomModifier(Book model, int ACTION) {

    }

    @Override
    public void onRoomModifier(BookWorm model, int ACTION) {

    }

    private void setButton() {
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            LayoutInflater inflater = LayoutInflater.from(this);
            View mNFView = inflater.inflate(R.layout.item_nation_flag,null);
            NationalFlag nf = list.get(position);
            // Build Alert Dialog with custom View
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(mNFView);

            ImageView imageView = mNFView.findViewById(R.id.imageView_nation_flag);
            TextView textView = mNFView.findViewById(R.id.textView_nation_name);
            TextView textView1 = mNFView.findViewById(R.id.textView_currency);
            textView.setText(nf.getNation());
            textView1.setText(nf.getCurrency());
            Glide.with(mNFView).load(nf.getImgFlag()).into(imageView);

            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton(getString(R.string.action_delete), (dialogInterface, i) -> {
                        onRoomModifier(list.get(position), DataBaseModifier.ACTION_DELETE);
                        dialogInterface.cancel();
                    })
                    .setNegativeButton(getString(R.string.action_cancel),(dialogInterface, i) -> {
                        dialogInterface.cancel();
                    });

            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return true;
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            NationalFlag nf = list.get(position);
            mIndex = position;
            mId = nf.getId();
            imgId = nf.getImgFlag();
            edt_nation_name.setText(nf.getNation());
            edt_currency.setText(nf.getCurrency());
        });
        btn_update.setOnClickListener(v -> {
            NationalFlag nf = new NationalFlag(
                    edt_nation_name.getText().toString().trim(),
                    edt_currency.getText().toString().trim(),
                    imgId
            );
            nf.setId(imgId);
            onRoomModifier(nf, DataBaseModifier.ACTION_UPDATE);
        });
        btn_view.setOnClickListener(v -> {
            startActivity(this, SubSecondActivity.class);
        });
    }

    private void setupViews() {
        setData();
        nationalFlagAdapter = new NationalFlagAdapter(SecondActivity.this, list);
        listView.setAdapter(nationalFlagAdapter);
    }

    private void setData() {
        list.addAll(dataBaseModifier.getNationalFlagDAO().selectAllNationalFlag());
    }

    private void mapIdToView() {
        btn_add = findViewById(R.id.button_add_second);
        btn_update = findViewById(R.id.button_update_second);
        btn_view = findViewById(R.id.button_view);

        edt_currency = findViewById(R.id.edt_nation_currency);
        edt_nation_name = findViewById(R.id.edt_name_nation);

        listView = findViewById(R.id.listView_2);

        list = new ArrayList<>();
    }
}