package com.example.androidtask.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtask.Data.ContactRepository;
import com.example.androidtask.Data.UserRepository;
import com.example.androidtask.R;

public class FullDetailsActivity extends AppCompatActivity {
    private TextView nameEditText;
    private TextView phoneNumberEditText;
    private TextView emailEditText;
    private TextView addressEditText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_contact_details);

        nameEditText = findViewById(R.id.editTextName);
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        emailEditText = findViewById(R.id.editTextEmail);
        addressEditText = findViewById(R.id.editTextAddress);

        String contactEmail = getIntent().getStringExtra("contactEmail");
        String contactAddress= getIntent().getStringExtra("contactAddress");
        String contactPhoneNumber = getIntent().getStringExtra("contactPhoneNumber");
        String contactName = getIntent().getStringExtra("contactName");

        nameEditText.setText(contactName);
        phoneNumberEditText.setText(contactPhoneNumber);
        emailEditText.setText(contactEmail);
        addressEditText.setText(contactAddress);

    }

    public void returnButton(View view){

        Intent in = new Intent(FullDetailsActivity.this, ContactListActivity.class);
        startActivity(in);
    }
}