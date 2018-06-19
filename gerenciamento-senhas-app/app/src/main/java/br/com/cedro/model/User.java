package br.com.cedro.model;

import android.graphics.Bitmap;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Objects;

@DatabaseTable(tableName = "user")
public class User implements Serializable {
    @DatabaseField(generatedId = true, columnName = "management_id")
    private Long id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "password")
    private String password;
    @DatabaseField(columnName = "url")
    private String url;
    @DatabaseField(columnName = "email")
    private String email;

    private Bitmap picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String photo) {
        this.password = photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public Bitmap getPicture() {
        return picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(url, user.url) &&
                Objects.equals(email, user.email) &&
                Objects.equals(picture, user.picture);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, url, email, picture);
    }
}
