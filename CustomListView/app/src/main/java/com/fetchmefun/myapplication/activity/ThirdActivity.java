package com.fetchmefun.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.adapter.BookwormAdapter;
import com.fetchmefun.myapplication.database.DataBaseModifier;
import com.fetchmefun.myapplication.model.Book;
import com.fetchmefun.myapplication.model.BookWorm;
import com.fetchmefun.myapplication.model.BookWorm;
import com.fetchmefun.myapplication.model.NationalFlag;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThirdActivity extends BaseActivity {

    private BookwormAdapter bookwormAdapter;
    private ListView listView;
    private ArrayList<BookWorm> bookWormArrayList;
    private RadioGroup radioGroup;
    private Button button_add_bookworm;
    private EditText edt_name_bookworm, edt_bookworm_code;
    private ImageView imageViewdelete;
    private DataBaseModifier dataBaseModifier = DataBaseModifier.getInstance(this);
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mapIdToView();
        setupViews();
        setButton();
    }

    @Override
    public void onRoomModifier(NationalFlag model, int ACTION) {

    }

    @Override
    public void onRoomModifier(Book model, int ACTION) {

    }

    @Override
    public void onRoomModifier(BookWorm model, int ACTION) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        // THis handler allow to access action in dialog
        Handler handler = new Handler(Looper.getMainLooper());
        service.execute(() -> {
            try{
                if(ACTION == DataBaseModifier.ACTION_INSERT){
                    dataBaseModifier.getBookWormDAO().insertBookworm(model);
                    bookWormArrayList.clear();
                    bookWormArrayList.addAll(dataBaseModifier.getBookWormDAO().selectAllBookworm());
                }else if(ACTION == DataBaseModifier.ACTION_DELETE){
                    dataBaseModifier.getBookWormDAO().deleteBookworm(model);
                    bookWormArrayList.clear();
                    bookWormArrayList.addAll(dataBaseModifier.getBookWormDAO().selectAllBookworm());
                }else if(ACTION == DataBaseModifier.ACTION_UPDATE){
                    dataBaseModifier.getBookWormDAO().updateBookworm(model);
                    bookWormArrayList.clear();
                    bookWormArrayList.addAll(dataBaseModifier.getBookWormDAO().selectAllBookworm());
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
                bookwormAdapter.notifyDataSetChanged();
            });
        });
    }

    private void setButton(){
        button_add_bookworm.setOnClickListener(v -> {
            if(edt_name_bookworm.getText().toString().equals("") || edt_bookworm_code.getText().toString().equals("")){
                showToast("Không được để trống dữ liệu!" + (edt_name_bookworm.getText().toString().equals("") ? "Tên độc giả trống!!!" : "Mã độc giả trống!!!"));
            }
            else if(!edt_name_bookworm.getText().toString().matches("[A-Z]")){
                showToast("Vui lòng viết hoa tên độc giả");
            }
            else if(radioGroup.getCheckedRadioButtonId() != R.id.male && radioGroup.getCheckedRadioButtonId() != R.id.female){
                showToast("Vui lòng chọn giới tính");
            }else{
                onRoomModifier(new BookWorm(edt_bookworm_code.getText().toString().trim(),edt_name_bookworm.getText().toString().trim(), radioGroup.getCheckedRadioButtonId() == R.id.male,false),DataBaseModifier.ACTION_INSERT);
            }
        });
        imageViewdelete.setOnClickListener(v -> {
            for (BookWorm bw:bookWormArrayList) {
                if(bw.isChecked()){
                    onRoomModifier(bw,DataBaseModifier.ACTION_DELETE);
                }
            }

        });
    }
    private void setupViews(){
        setData();
        bookwormAdapter = new BookwormAdapter(ThirdActivity.this, bookWormArrayList);
        listView.setAdapter(bookwormAdapter);

    }
    private void setData(){
        bookWormArrayList.addAll(dataBaseModifier.getBookWormDAO().selectAllBookworm());
    }
    private void mapIdToView(){
        bookWormArrayList = new ArrayList<>();
        listView = findViewById(R.id.listView_3);
        button_add_bookworm = findViewById(R.id.button_add_bookworm);
        edt_bookworm_code = findViewById(R.id.editText_ma_docgia);
        edt_name_bookworm = findViewById(R.id.editText_ten_docgia3);
        radioGroup = findViewById(R.id.radioGroup3);
        imageViewdelete = findViewById(R.id.imageViewdelete);
    }
}