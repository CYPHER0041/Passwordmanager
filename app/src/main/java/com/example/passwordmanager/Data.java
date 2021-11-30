package com.example.passwordmanager;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_data")
public class Data {
    @ColumnInfo(name="username") String username;
    @ColumnInfo(name="password") String password;
    @PrimaryKey @NonNull @ColumnInfo(name = "site_url") String site_url;

    public Data(String username, String password, String site_url) {
        this.username = username;
        this.password = password;
        this.site_url = site_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }


}
