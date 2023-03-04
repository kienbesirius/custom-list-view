package com.fetchmefun.myapplication.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fetchmefun.myapplication.R;

@Entity
public class BookWorm {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    private String wormId;
    @NonNull
    private String wormName;
    private boolean wormSex;
    private int imgWorm;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    @Ignore
    public BookWorm() {
        wormName = "";
        wormId = "";
    }

    public BookWorm(@NonNull String wormId, @NonNull String wormName, boolean wormSex, boolean checked) {
        this.wormId = wormId;
        this.wormName = wormName;
        this.checked = checked;
        if(wormSex){
            this.wormSex = true;
            this.imgWorm = R.drawable.ic_male;
        }else{
            this.wormSex = false;
            this.imgWorm = R.drawable.ic_female;
        }
    }

    public int getImgWorm() {
        return imgWorm;
    }

    public void setImgWorm(int imgWorm) {
        this.imgWorm = imgWorm;
    }

    @NonNull
    public String getWormId() {
        return wormId;
    }

    public void setWormId(@NonNull String wormId) {
        this.wormId = wormId;
    }

    @NonNull
    public String getWormName() {
        return wormName;
    }

    public void setWormName(@NonNull String wormName) {
        this.wormName = wormName;
    }

    public boolean isWormSex() {
        return wormSex;
    }

    public void setWormSex(boolean wormSex) {
        this.wormSex = wormSex;
    }
}
