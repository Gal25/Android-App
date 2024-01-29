package com.example.androidtask.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtask.Data.ContactRepository;
import com.example.androidtask.Data.UserRepository;
import com.example.androidtask.R;
import com.example.androidtask.UserEntity.Contact;

import java.util.List;

public class ContactListActivity extends AppCompatActivity {
    private ContactRepository contactRepository;
    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private final ContactAdapter contactAdapter = new ContactAdapter();
    private RecyclerView recyclerView;
    private Button buttonDeleteContact;
    private Button buttonEditContact;
    private Button viewDetailsButton;
    private LiveData<List<Contact>> contactsForLoggedInUser;
    private UserRepository userRepository;
    private SharedPreferences sharedPreferences;
    private boolean showName;
    private boolean showPhoneNumber;
    private boolean showEmail;
    private boolean showAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);


        buttonDeleteContact = findViewById(R.id.buttonDelete);
        buttonEditContact = findViewById(R.id.buttonEditContact);
        viewDetailsButton = findViewById(R.id.viewDetailsButton);

        userRepository = UserRepository.getInstance(this);
        contactRepository = new ContactRepository(this,userRepository);

        recyclerView = findViewById(R.id.recyclerViewContacts);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadPreferences();

        applyPreferences(recyclerView, contactAdapter);

        viewDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact selectedContact = contactAdapter.getSelectedContact();
                if (selectedContact != null) {
                    // Delete the selected contact
                    moreDetails(selectedContact);
                } else {
                    // Handle case when no contact is selected
                    Toast.makeText(ContactListActivity.this, "Select a contact to see more details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up button click listener to delete selected contact
        buttonDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact selectedContact = contactAdapter.getSelectedContact();
                if (selectedContact != null) {
                    // Delete the selected contact
                    deleteContact(selectedContact);
                } else {
                    // Handle case when no contact is selected
                    Toast.makeText(ContactListActivity.this, "Select a contact to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle click event for a contact in the RecyclerView
        buttonEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact selectedContact = contactAdapter.getSelectedContact();
                if (selectedContact != null) {
                    Intent intent = new Intent(ContactListActivity.this, EditContactActivity.class);
                    intent.putExtra("contactId", selectedContact.getId());
                    intent.putExtra("contactName", selectedContact.getName());
                    intent.putExtra("contactPhoneNumber", selectedContact.getPhoneNumber());
                    intent.putExtra("contactAddress", selectedContact.getAddress());
                    intent.putExtra("contactEmail", selectedContact.getEmail());
                    intent.putExtra("contactUserID", selectedContact.getUserId());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ContactListActivity.this, "Select a contact to edit", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Observe changes in the list of contacts
        contactRepository.getContactsForLoggedInUser().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactAdapter.setContacts(contacts);
            }
        });
    }

    private void deleteContact(Contact contact) {
        // Delete the contact from the database
        contactRepository.deleteContact(contact);
    }

    private void moreDetails(Contact contact){
        Intent intent = new Intent(this, FullDetailsActivity.class);
        intent.putExtra("contactName", contact.getName());
        intent.putExtra("contactPhoneNumber", contact.getPhoneNumber());
        intent.putExtra("contactEmail", contact.getEmail());
        intent.putExtra("contactAddress", contact.getAddress());
        startActivity(intent);
    }


    public void Add(View view){

        Intent in = new Intent(ContactListActivity.this, AddContactActivity.class);
        startActivity(in);
    }

    public void openSettingsActivity(View view) {

        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void loadPreferences() {
        showName = sharedPreferences.getBoolean("showName", true);
        showPhoneNumber = sharedPreferences.getBoolean("showPhoneNumber", true);
        showEmail = sharedPreferences.getBoolean("showEmail", true);
        showAddress = sharedPreferences.getBoolean("showAddress", true);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void applyPreferences(RecyclerView recyclerView, ContactAdapter contactAdapter) {
        // Retrieve user preferences
        boolean showName = sharedPreferences.getBoolean("showName", true);
        boolean showPhoneNumber = sharedPreferences.getBoolean("showPhoneNumber", true);
        boolean showEmail = sharedPreferences.getBoolean("showEmail", true);
        boolean showAddress = sharedPreferences.getBoolean("showAddress", true);

        // Update adapter to show/hide name and phone number based on preferences
        if (contactAdapter != null) {
            contactAdapter.setShowName(showName);
            contactAdapter.setShowPhoneNumber(showPhoneNumber);
            contactAdapter.setShowEmail(showEmail);
            contactAdapter.setShowAddress(showAddress);
            contactAdapter.notifyDataSetChanged();
        }
    }

}
