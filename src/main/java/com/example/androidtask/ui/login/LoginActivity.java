package com.example.androidtask.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.androidtask.Data.LoginCallback;
import com.example.androidtask.Data.UserRepository;
import com.example.androidtask.R;
import com.example.androidtask.UserEntity.User;

public class LoginActivity extends AppCompatActivity implements LoginCallback {
    private UserRepository userRepository;
    private  EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = UserRepository.getInstance(this);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);

    }

    public void buttonLogin(View view){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        userRepository.login(username, password, this);
    }

    @Override
    public void onLoginSuccess(User user) {
        System.out.println("onloginsuccess   "+user.getId());
        User currentUser = new User();
        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setEmail(user.getEmail());

        userRepository.setCurrentUser(currentUser);
        Intent intent = new Intent(LoginActivity.this, ContactListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure() {
        Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
    }

    public void newUserButton(View view){
        Intent in = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(in);
    }
}
