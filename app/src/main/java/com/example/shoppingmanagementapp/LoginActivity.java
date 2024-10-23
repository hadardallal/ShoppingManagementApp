package com.example.shoppingmanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton, registerButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.blink);
        loginButton.startAnimation(scaleAnimation);



        registerButton = findViewById(R.id.registerButton);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        loginButton.setOnClickListener(v -> loginUser());
        registerButton.setOnClickListener(v -> registerNewUser());
    }

    private void loginUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        String storedPassword = sharedPreferences.getString(username, null);
        if (storedPassword != null && storedPassword.equals(password)) {
            Intent intent = new Intent(LoginActivity.this, ShoppingActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerNewUser() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
