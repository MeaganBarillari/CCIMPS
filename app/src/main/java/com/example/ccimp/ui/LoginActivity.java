package com.example.ccimp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ccimp.R;

public class LoginActivity extends AppCompatActivity {
    EditText userEmail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
    }

    public void onLogin(View view) {
        String email = userEmail.getText().toString();
        String pass = password.getText().toString();
        String type = "login";

        backgroundWorker bgworker = new backgroundWorker(this);
        bgworker.execute(type, email, pass);
    }
}
