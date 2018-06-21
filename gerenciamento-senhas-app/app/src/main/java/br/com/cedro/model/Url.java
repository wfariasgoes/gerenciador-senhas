package br.com.cedro.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "url")
public class Url {
    public static final String FIELD_NAME_USER  = "user";

    @DatabaseField(generatedId = true, columnName = "url_id")
    private Long id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "password")
    private String password;
    @DatabaseField(columnName = "email")
    private String email;
    @DatabaseField(columnName = "url")
    private String url;


    @DatabaseField(columnName = FIELD_NAME_USER, foreign = true, foreignAutoRefresh = true)
    private User mUser;

    public Url() {
    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }
}
