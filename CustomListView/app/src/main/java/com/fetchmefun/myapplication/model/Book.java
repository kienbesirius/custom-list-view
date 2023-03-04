package com.fetchmefun.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fetchmefun.myapplication.R;

@Entity
public class Book {
    public static final String TEXT_BOOK = "Giáo Khoa";
    public static final String NOVEL_BOOK = "Tiểu Thuyết";
    public static final String SCIENCE_BOOK = "Khoa Học";
    public static final String PSYCHOLOGY_BOOK = "Tâm Lý";
    public static final String NO_TYPE_BOOK = "Không thể loại";

    public static final int TEXT_BOOK_CODE_IMG = 44566;
    public static final int NOVEL_BOOK_CODE_IMG = 44567;
    public static final int SCIENCE_BOOK_CODE_IMG = 44568;
    public static final int PSYCHOLOGY_BOOK_CODE_IMG = 44569;

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String type;
    private int imgBook;

    @Ignore
    public Book() {
        name = "";
    }

    public Book(@NonNull String name, int imgBook) {
        this.name = name;

        switch (imgBook){
            case TEXT_BOOK_CODE_IMG:{
                this.type = TEXT_BOOK;
                this.imgBook = R.drawable.text_book;
                break;
            }
            case SCIENCE_BOOK_CODE_IMG:{
                this.type = SCIENCE_BOOK;
                this.imgBook = R.drawable.science_book;
                break;
            }
            case NOVEL_BOOK_CODE_IMG:{
                this.type = NOVEL_BOOK;
                this.imgBook = R.drawable.novel_book;
                break;
            }
            case PSYCHOLOGY_BOOK_CODE_IMG:{
                this.type = PSYCHOLOGY_BOOK;
                this.imgBook = R.drawable.psychology_book;
                break;
            }
            default:{
                this.type = NO_TYPE_BOOK;
                this.imgBook = R.drawable.novel_book;
            }
        }
    }

    public int getImgBook() {
        return imgBook;
    }

    public void setImgBook(int imgBook) {
        this.imgBook = imgBook;
    }

    public void setType( String type) {
        this.type = type;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(int TYPE_BOOK_CODE_IMG) {
        switch (TYPE_BOOK_CODE_IMG){
            case TEXT_BOOK_CODE_IMG:{
                this.type = TEXT_BOOK;
                break;
            }
            case SCIENCE_BOOK_CODE_IMG:{
                this.type = SCIENCE_BOOK;
                break;
            }
            case NOVEL_BOOK_CODE_IMG:{
                this.type = NOVEL_BOOK;
                break;
            }
            case PSYCHOLOGY_BOOK_CODE_IMG:{
                this.type = PSYCHOLOGY_BOOK;
                break;
            }
            default:{
                this.type = NO_TYPE_BOOK;
            }
        }
    }
}
