package com.fetchmefun.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fetchmefun.myapplication.model.Book;

import java.util.List;

@Dao
public interface BookDAO {
    @Insert
    public void insertBook(Book model);

    @Delete
    public void deleteBook(Book model);

    @Update
    public void updateBook(Book model);

    @Query("SELECT * FROM book ORDER BY name")
    public List<Book> selectAllBook();

    @Query("SELECT * FROM book WHERE name == :name")
    public  Book getABook(String name);
}
