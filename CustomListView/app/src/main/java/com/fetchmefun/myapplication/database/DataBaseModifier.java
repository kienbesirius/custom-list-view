package com.fetchmefun.myapplication.database;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fetchmefun.myapplication.R;
import com.fetchmefun.myapplication.model.Book;
import com.fetchmefun.myapplication.model.BookWorm;
import com.fetchmefun.myapplication.model.NationalFlag;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class, BookWorm.class, NationalFlag.class}, version = 1)
public abstract class DataBaseModifier extends RoomDatabase {

    public static final int ACTION_DELETE = 34102;
    public static final int ACTION_INSERT = 34103;
    public static final int ACTION_UPDATE = 34104;
    public static final int ACTION_SELECT_ALL = 34105;
    public static final int ACTION_SELECT_ONE = 34106;

    public abstract BookDAO getBookDAO();

    public abstract BookwormDAO getBookWormDAO();

    public abstract NationalFlagDAO getNationalFlagDAO();

    // Create an INSTANCE so we can use the DAO whatever we want and do not recreate it
    public static DataBaseModifier instance;

    public static RoomDatabase.Callback mCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            try{
                InitializeData();
            }catch (Exception e){
                Log.i("Keep Lost error", e.getMessage());
                e.printStackTrace();
            }
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };


    public static void  InitializeData() {
        BookDAO bookDAO = instance.getBookDAO();
        BookwormDAO bookWormDAO = instance.getBookWormDAO();
        NationalFlagDAO nationalFlagDAO = instance.getNationalFlagDAO();

        ArrayList<Book> list1 = new ArrayList<>();
        ArrayList<BookWorm> list2 = new ArrayList<>();
        ArrayList<NationalFlag> list3 = new ArrayList<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            list1.add(new Book("B??ch Luy???n Th??nh Th???n", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("B??ch Luy???n Th??nh Ti??n", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("Ph??m Nh??n Tu Ti??n", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("B???t H??? Ph??m Nh??n", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("To??n Ch???c Cao Th???", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("To??n Ch???c Ph??p S??", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("Th???n ?????o ??an T??n", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("Nh???t Ni???m V??nh H???ng", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("Phi Thi??n", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("Cultivation Chat Group", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("Skeleton Soldier Couldn't Protect The Dungeon", Book.NOVEL_BOOK_CODE_IMG));
            list1.add(new Book("Strongest ANTI-METAL System", Book.NOVEL_BOOK_CODE_IMG));
            list2.add(new BookWorm("DG001", "B??? Ch?? Ki??n", true,false));
            list2.add(new BookWorm("DG002", "Tri???u Ho??ng ?????c", true,false));
            list2.add(new BookWorm("DG004", "L??nh Th??? S??ng Th????ng", false,false));
            list2.add(new BookWorm("DG005", "D????ng H???ng Qu??n", true,false));
            list2.add(new BookWorm("DG007", "L??nh H?? Vi", false,false));
            list2.add(new BookWorm("DG008", "L?? Nh?? Ph????ng", false,false));
            list2.add(new BookWorm("DG009", "L??ng Th??? Th??", false,false));
            list2.add(new BookWorm("DG010", "Ho??ng ?????c C??ng", true,false));

            list3.add(new NationalFlag("CANADA", "CAD Canadian Dollar", R.drawable.flag_of_cad));
            list3.add(new NationalFlag("Australia", "AUD Australian Dollar", R.drawable.flag_of_australia));
            list3.add(new NationalFlag("Brazil", "BRL Brazilian Real", R.drawable.flag_of_brazil));
            list3.add(new NationalFlag("Vi???t Nam", "VND Viet Nam Dong", R.drawable.flag_of_vietnam));

                for(int i = 0; i < list1.size(); i++ ){
                    bookDAO.insertBook(list1.get(i));
                }
                for(int i = 0; i < list2.size(); i++ ){
                    bookWormDAO.insertBookworm(list2.get(i));
                }
                for(int i = 0; i < list3.size(); i++ ){
                    nationalFlagDAO.insertNationalFlag(list3.get(i));
                }
        });

    }

    /*
      synchronized method: define what threads execute first.
     */
    public static synchronized DataBaseModifier getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DataBaseModifier.class,
                            "My_Trinity_Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(mCallBack)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
