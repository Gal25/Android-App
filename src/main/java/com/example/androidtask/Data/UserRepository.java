package com.example.androidtask.Data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.androidtask.UserEntity.User;

public class UserRepository {
    private UserDao userDao;
    public User currentUser;
    public static String name;
    private static boolean instanceBool = false;
    private static UserRepository instance;


    @SuppressLint("StaticFieldLeak")
    public UserRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        instanceBool = true;
        userDao = database.userDao();

    }

    public static synchronized UserRepository getInstance(Context context) {
        if (!instanceBool) {
            instance = new UserRepository(context.getApplicationContext());
        }
       return instance;
    }

    @SuppressLint("StaticFieldLeak")
    public void signup(User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDao.insert(user);
                return null;
            }
        }.execute();

    }

    @SuppressLint("StaticFieldLeak")
    public void login(String username, String password ,LoginCallback callback) {
        name = username;

        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                 return userDao.login(username, password);
            }
            @Override
            protected void onPostExecute(User user) {
                System.out.println("post execute");
                if (user != null) {
                    callback.onLoginSuccess(user);
                } else {
                    callback.onLoginFailure();
                }
            }
        }.execute();
    }

    public  User getCurrentUser() {

        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = new User(currentUser.getId(),currentUser.getUsername(),currentUser.getPassword(),currentUser.getPhoneNumber(),currentUser.getEmail());
    }
}
