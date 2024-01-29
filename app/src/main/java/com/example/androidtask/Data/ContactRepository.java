package com.example.androidtask.Data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;


import com.example.androidtask.UserEntity.Contact;
import com.example.androidtask.UserEntity.User;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    private UserDao userDao;
    private LiveData<List<Contact>> allContacts;
    private User currentUser;
    private UserRepository userRepository;


    public ContactRepository(Context context, UserRepository userRepository) {
        AppDatabase database = AppDatabase.getInstance(context);
        contactDao = database.contactDao();
        userDao = database.userDao();
        this.userRepository = userRepository;
        currentUser = userRepository.getCurrentUser();
        System.out.println("ContactRepository"+ currentUser.getUsername());
        allContacts = contactDao.getContactsForUser(currentUser.getId());

    }

    @SuppressLint("StaticFieldLeak")
    public void addContact(Contact contact) {
            if (currentUser != null) {
                // Set the user ID on the contact
                contact.setUserId(currentUser.getId());

                // Insert the contact into the database
                insertContact(contact);
            }
        }

    @SuppressLint("StaticFieldLeak")
    private void insertContact(Contact contact) {
        // Insert the contact into the database on the main thread
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                contactDao.insert(contact);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public LiveData<List<Contact>> getContactsForLoggedInUser() {
        return allContacts;
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }


    public void deleteContact(Contact contact) {
        // Delete the contact from the database using AsyncTask
        new DeleteContactAsyncTask(contactDao).execute(contact);
    }
    @SuppressLint("StaticFieldLeak")
    public void editContact(Contact updatedContact) {
        // Insert the contact into the database on the main thread
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                contactDao.updateContact(updatedContact);
                return null;
            }
        }.execute();
    }

    private static class DeleteContactAsyncTask extends AsyncTask<Contact, Void, Void> {
        private final ContactDao contactDao;

        DeleteContactAsyncTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            contactDao.updateId(contacts[0].getId());
            return null;
        }
    }

}

