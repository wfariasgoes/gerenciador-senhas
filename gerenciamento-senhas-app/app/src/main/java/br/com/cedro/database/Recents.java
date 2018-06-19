package br.com.cedro.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

@Entity(tableName = "recents", primaryKeys = {"name","url"})
public class Recents {

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "url")
    @NonNull
    private String url;

//    @ColumnInfo(name = "image")
//    private Bitmap image;

    @ColumnInfo(name = "saveTime")
    private String saveTime;


    public Recents(@NonNull String name, @NonNull String url,String saveTime) {
        this.name = name;
        this.url = url;
//        this.image = image;
        this.saveTime = saveTime;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String categoryId) {
        this.url = categoryId;
    }

//    public Bitmap getImage() {
//        return image;
//    }
//
//    public void setImage(Bitmap image) {
//        this.image = image;
//    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }


}
