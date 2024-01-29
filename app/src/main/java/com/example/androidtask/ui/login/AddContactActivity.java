package com.example.androidtask.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtask.Data.ContactRepository;
import com.example.androidtask.Data.UserRepository;
import com.example.androidtask.R;
import com.example.androidtask.UserEntity.Contact;

public class AddContactActivity extends AppCompatActivity {
    private ContactRepository contactRepository;
    private UserRepository userRepository;
    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private EditText EmailEditText;
    private EditText AddressEditText;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        userRepository = UserRepository.getInstance(this);

        contactRepository = new ContactRepository(this,userRepository);

        nameEditText = findViewById(R.id.editTextName);
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        EmailEditText = findViewById(R.id.editTextEmail);
        AddressEditText = findViewById(R.id.editTextAddress);

    }

    public void Add(View view){

        String name = nameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String email = EmailEditText.getText().toString();
        String address = AddressEditText.getText().toString();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhoneNumber(phoneNumber);
        contact.setAddress(address);
        contact.setEmail(email);

        contactRepository.addContact(contact);
        Intent in = new Intent(AddContactActivity.this, ContactListActivity.class);
        startActivity(in);
    }
}
