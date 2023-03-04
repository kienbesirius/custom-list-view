package com.fetchmefun.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.adapter.BookAdapter;
import com.fetchmefun.myapplication.database.DataBaseModifier;
import com.fetchmefun.myapplication.model.Book;

import com.fetchmefun.myapplication.model.BookWorm;
import com.fetchmefun.myapplication.model.NationalFlag;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private ListView listView;
    private BookAdapter bookAdapter;
    private EditText editText;
    private Button btn_booking, btn_update;
    private ArrayList<Book> mListBook;
    private DataBaseModifier dataBaseModifier = DataBaseModifier.getInstance(this);
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mapIdToView();
        setupViews();
        setButton();
    }

    @Override
    public void onRoomModifier(NationalFlag model, int ACTION) {

    }

    @Override
    public void onRoomModifier(Book model, int ACTION) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        service.execute(() -> {
            try{
                if(ACTION == DataBaseModifier.ACTION_INSERT){
                    dataBaseModifier.getBookDAO().insertBook(model);
                    mListBook.clear();
                    mListBook.addAll(dataBaseModifier.getBookDAO().selectAllBook());
                }else if(ACTION == DataBaseModifier.ACTION_DELETE){
                    dataBaseModifier.getBookDAO().deleteBook(model);
                    mListBook.clear();
                    mListBook.addAll(dataBaseModifier.getBookDAO().selectAllBook());
                }else if(ACTION == DataBaseModifier.ACTION_UPDATE){
                    dataBaseModifier.getBookDAO().updateBook(model);
                    mListBook.clear();
                    mListBook.addAll(dataBaseModifier.getBookDAO().selectAllBook());
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
                bookAdapter.notifyDataSetChanged();
            });
        });
    }

    @Override
    public void onRoomModifier(BookWorm model, int ACTION) {

    }

    private void setButton() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Book book = mListBook.get(position);
            mId = book.getId();
            editText.setText(book.getName());
            switch (book.getType()){
                case Book.TEXT_BOOK:{
                    radioGroup.check(R.id.sgk);
                    break;
                }
                case Book.PSYCHOLOGY_BOOK:{
                    radioGroup.check(R.id.tl);
                    break;
                }
                case Book.NOVEL_BOOK:{
                    radioGroup.check(R.id.tt);
                    break;
                }
                case Book.SCIENCE_BOOK:{
                    radioGroup.check(R.id.kh);
                    break;
                }
                default:{
                    radioGroup.clearCheck();
                }
            }
        });
        btn_update.setOnClickListener(v -> {
            int imgBook;
            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.tt:{
                    imgBook = Book.NOVEL_BOOK_CODE_IMG;
                    break;
                }
                case R.id.sgk:{
                    imgBook = Book.TEXT_BOOK_CODE_IMG;
                    break;
                }
                case R.id.tl:{
                   imgBook = Book.PSYCHOLOGY_BOOK_CODE_IMG;
                    break;
                }
                case R.id.kh:{
                    imgBook = Book.SCIENCE_BOOK_CODE_IMG;
                    break;
                }
                default:{
                    imgBook = Book.TEXT_BOOK_CODE_IMG;
                }
            }
            Book b = new Book(editText.getText().toString().trim(),imgBook);
            b.setId(mId);
            onRoomModifier(b, DataBaseModifier.ACTION_UPDATE);
        });
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            LayoutInflater inflater = LayoutInflater.from(this);
            View mBookView = inflater.inflate(R.layout.item_book,null);
            Book b = mListBook.get(position);
            // Build Alert Dialog with custom View
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(mBookView);

            ImageView imageView = mBookView.findViewById(R.id.imageView_book_ic);
            TextView textView = mBookView.findViewById(R.id.textView_name_book);
            TextView textView1 = mBookView.findViewById(R.id.textView_type);
            textView.setText(b.getName());
            textView1.setText(b.getType());
            Glide.with(mBookView).load(b.getImgBook()).into(imageView);

            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton(getString(R.string.action_delete), (dialogInterface, i) -> {
                        onRoomModifier(mListBook.get(position), DataBaseModifier.ACTION_DELETE);
                        dialogInterface.cancel();
                    })
                    .setNegativeButton(getString(R.string.action_cancel),(dialogInterface, i) -> {
                        dialogInterface.cancel();
                    });

            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return true;
        });
    }


    private void setupViews() {
        setData();
        bookAdapter = new BookAdapter(FirstActivity.this, mListBook);
        listView.setAdapter(bookAdapter);
    }

    private void setData() {
        mListBook.addAll(dataBaseModifier.getBookDAO().selectAllBook());
    }

    private void mapIdToView() {
        mListBook = new ArrayList<>();
        radioGroup = findViewById(R.id.radioGroupbook);
        listView = findViewById(R.id.listView_1);
        editText = findViewById(R.id.editText_ten_sach);
        btn_booking = findViewById(R.id.button_booking);
        btn_update = findViewById(R.id.button_update);
    }

}