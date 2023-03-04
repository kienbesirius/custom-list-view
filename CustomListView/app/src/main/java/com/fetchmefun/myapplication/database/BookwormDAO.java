package com.fetchmefun.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fetchmefun.myapplication.model.Book;
import com.fetchmefun.myapplication.model.BookWorm;

import java.util.List;

@Dao
public interface BookwormDAO {
    @Insert
    public void insertBookworm(BookWorm model);

    @Delete
    public void deleteBookworm(BookWorm model);

    @Update
    public void updateBookworm(BookWorm model);

    @Query("SELECT * FROM bookworm ORDER BY wormId")
    public List<BookWorm> selectAllBookworm();

    @Query("SELECT * FROM bookworm WHERE wormId == :id AND wormName == :name")
    public BookWorm getABookworm(String id, String name);
}
