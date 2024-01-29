package com.example.androidtask.ui.login;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtask.Data.UserRepository;
import com.example.androidtask.R;
import com.example.androidtask.UserEntity.User;

public class SignupActivity extends AppCompatActivity {
    private UserRepository userRepository;
    private EditText usernameEditText;
    private  EditText passwordEditText;
    private EditText emailEditText;
    private  EditText phoneEditText;
    private String username,password,phone,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userRepository = new UserRepository(this);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        emailEditText = findViewById(R.id.editTextEmail);
        phoneEditText = findViewById(R.id.editTextPhoneNumber);
    }

    public void buttonSignup(View view){
        System.out.println("buttonSignup");
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        phone = phoneEditText.getText().toString();
        email = emailEditText.getText().toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phone);

        userRepository.signup(user);
        Intent in = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(in);
    }
}
