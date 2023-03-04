package com.fetchmefun.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class NationalFlag {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    private String nation;
    @NonNull
    private String currency;
    private int imgFlag;



    @Ignore
    public NationalFlag() {
        nation = "";
        currency = "";
    }

    public NationalFlag(@NonNull String nation, @NonNull String currency, int imgFlag) {
        this.nation = nation;
        this.currency = currency;
        this.imgFlag = imgFlag;
    }

    @NonNull
    public String getNation() {
        return nation;
    }

    public void setNation(@NonNull String nation) {
        this.nation = nation;
    }

    @NonNull
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(@NonNull String currency) {
        this.currency = currency;
    }

    public int getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(int imgFlag) {
        this.imgFlag = imgFlag;
    }
}
