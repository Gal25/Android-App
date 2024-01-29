package com.example.androidtask.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtask.R;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private CheckBox nameCheckBox;
    private CheckBox phoneNumberCheckBox;
    private CheckBox EmailCheckBox;
    private CheckBox AddressCheckBox;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        nameCheckBox = findViewById(R.id.nameCheckBox);
        phoneNumberCheckBox = findViewById(R.id.phoneNumberCheckBox);
        EmailCheckBox = findViewById(R.id.EmailCheckBox);
        AddressCheckBox = findViewById(R.id.AddressCheckBox);


        // Load user preferences and update UI
        loadPreferences();

        // Save preferences when user changes them
        nameCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> savePreferences());
        phoneNumberCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> savePreferences());
        EmailCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> savePreferences());
        AddressCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> savePreferences());
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void loadPreferences() {
        boolean showName = sharedPreferences.getBoolean("showName", true);
        boolean showPhoneNumber = sharedPreferences.getBoolean("showPhoneNumber", true);
        boolean showEmail = sharedPreferences.getBoolean("showEmail", true);
        boolean showAddress = sharedPreferences.getBoolean("showAddress", true);


        nameCheckBox.setChecked(showName);
        phoneNumberCheckBox.setChecked(showPhoneNumber);
        EmailCheckBox.setChecked(showEmail);
        AddressCheckBox.setChecked(showAddress);
    }

    private void savePreferences() {
        boolean showName = nameCheckBox.isChecked();
        boolean showPhoneNumber = phoneNumberCheckBox.isChecked();
        boolean showEmail = EmailCheckBox.isChecked();
        boolean showAddress = AddressCheckBox.isChecked();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("showName", showName);
        editor.putBoolean("showPhoneNumber", showPhoneNumber);
        editor.putBoolean("showEmail", showEmail);
        editor.putBoolean("showAddress", showAddress);
        editor.apply();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(SettingsActivity.this, ContactListActivity.class);
        startActivity(in);
    }
}
