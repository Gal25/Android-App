package com.example.androidtask.Data;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidtask.UserEntity.Contact;
import com.example.androidtask.UserEntity.User;

@Database(entities = {User.class, Contact.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract ContactDao contactDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if(instance == null) {
            System.out.println("getinst");
            instance = Room.databaseBuilder(
                            context,
                            AppDatabase.class,
                            "app_database")
                    .build();
        }
        instance.getOpenHelper().getWritableDatabase();

        return instance;
    }
}
