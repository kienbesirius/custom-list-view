package com.fetchmefun.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fetchmefun.myapplication.model.Book;
import com.fetchmefun.myapplication.model.NationalFlag;

import java.util.List;

@Dao
public interface NationalFlagDAO {
    @Insert
    public void insertNationalFlag(NationalFlag model);

    @Delete
    public void deleteNationalFlag(NationalFlag model);

    @Update
    public void updateNationalFlag(NationalFlag model);

    @Query("SELECT * FROM nationalflag ORDER BY nation")
    public List<NationalFlag> selectAllNationalFlag();

    @Query("SELECT * FROM nationalflag WHERE nation == :nation AND currency == :currency")
    public NationalFlag getANationalFlag(String nation, String currency);
}
