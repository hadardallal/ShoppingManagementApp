package com.example.shoppingmanagementapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, phoneEditText, emailEditText;
    private Button registerButton;
    private SharedPreferences sharedPreferencesUsername;
    private SharedPreferences sharedPreferencesEmail;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        phoneEditText = findViewById(R.id.phone);
        emailEditText = findViewById(R.id.email);
        registerButton = findViewById(R.id.registerButton);

        sharedPreferencesUsername = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        sharedPreferencesEmail = getSharedPreferences("EmailPrefs", MODE_PRIVATE);

        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String email = emailEditText.getText().toString();

        if (sharedPreferencesUsername.contains(username)) {
            Toast.makeText(this, "Username already taken", Toast.LENGTH_SHORT).show();
        } else if (sharedPreferencesEmail.contains(email)) {
            Toast.makeText(this, "Email already taken", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPreferencesUsername.edit();
            editor.putString(username, password);
            editor.putString(username + "_phone", phone);
            editor.putString(username + "_email", email);
            editor.apply();
            SharedPreferences.Editor emailEditor = sharedPreferencesEmail.edit();
            emailEditor.putString(email, username);
            emailEditor.apply();
            Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

