package com.example.androidtask.Data;

import com.example.androidtask.UserEntity.User;

public interface LoginCallback {

    void onLoginSuccess(User user);


    void onLoginFailure();

}