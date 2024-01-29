package com.example.androidtask.UserEntity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Entity(tableName = "Users")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int Id;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;

    public User() {
    }

    @Ignore
    public User( int id, String username, String password, String phoneNumber, String email) {
        Id = id ;
        this.username = username;
        this.password = password;
        this.email=email;
        this.phoneNumber = phoneNumber;

    }


    public int getId() {
        return Id;
    }

    public void setId( int id) {
        Id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(User user) {
        this.Id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
    }
}