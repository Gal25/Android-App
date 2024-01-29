package com.example.androidtask.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtask.Data.ContactRepository;
import com.example.androidtask.Data.UserRepository;
import com.example.androidtask.R;
import com.example.androidtask.UserEntity.Contact;

public class EditContactActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private Button saveButton;
    private ContactRepository contactRepository;
    private UserRepository userRepository;
    private EditText emailEditText;
    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        // Initialize views
        nameEditText = findViewById(R.id.editTextName);
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        emailEditText = findViewById(R.id.editTextEmail);
        addressEditText = findViewById(R.id.editTextAddress);
        saveButton = findViewById(R.id.saveButton);

        int contactId = getIntent().getIntExtra("contactId", 0);
        String contactName = getIntent().getStringExtra("contactName");
        String contactPhoneNumber = getIntent().getStringExtra("contactPhoneNumber");
        String contactEmail= getIntent().getStringExtra("contactEmail");
        String contactAddress = getIntent().getStringExtra("contactAddress");
        int userID = getIntent().getIntExtra("contactUserID",0);


        nameEditText.setText(contactName);
        phoneNumberEditText.setText(contactPhoneNumber);
        emailEditText.setText(contactEmail);
        addressEditText.setText(contactAddress);

        userRepository = UserRepository.getInstance(this);
        contactRepository = new ContactRepository(this,userRepository);

        // Set OnClickListener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the edited contact details from the EditText fields
                String newName = nameEditText.getText().toString();
                String newPhoneNumber = phoneNumberEditText.getText().toString();
                String newEmail = emailEditText.getText().toString();
                String newAddress = addressEditText.getText().toString();

                // Validate the input (optional)
                if (newName.isEmpty() || newPhoneNumber.isEmpty() || newEmail.isEmpty() || newAddress.isEmpty()) {
                    Toast.makeText(EditContactActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update the contact details in the database (or perform other actions)
                updateContact(contactId,newName, newPhoneNumber,newEmail,newAddress, userID);

                // Optionally, navigate back to the previous screen
                finish();
            }
        });
    }

    private void updateContact(int contactID,String newName, String newPhoneNumber,String newEmail,String newAddress, int userID) {
        Contact updatedContact = new Contact();
        updatedContact.setId(contactID);
        updatedContact.setName(newName);
        updatedContact.setPhoneNumber(newPhoneNumber);
        updatedContact.setEmail(newEmail);
        updatedContact.setAddress(newAddress);
        updatedContact.setUserId(userID);

        contactRepository.editContact(updatedContact);
        Intent in = new Intent(EditContactActivity.this, ContactListActivity.class);
        startActivity(in);


    }

}
