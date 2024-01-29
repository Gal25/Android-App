package com.example.androidtask.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidtask.UserEntity.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insert(Contact contact);

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contacts WHERE userId = :userId")
    LiveData<List<Contact>> getContactsForUser(int userId);

    @Delete
    void delete(Contact contact);
    @Query("UPDATE contacts SET id = id - 1 WHERE id > :deletedItemId")
    void updateId(int deletedItemId);

    @Update
    void updateContact(Contact updatedContact);
}
