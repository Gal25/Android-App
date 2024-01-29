package com.example.androidtask.UserEntity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import java.util.UUID;

@Entity(tableName = "contacts",
        foreignKeys ={ @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)},
        indices={@Index(value="userId")})

public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private int userId; // Foreign key referencing the User entity


    public Contact() {
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Ignore
    public Contact(int id, String name, String phoneNumber, int userId) {
        Id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }
}
