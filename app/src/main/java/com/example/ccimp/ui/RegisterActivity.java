package com.example.ccimp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.RegisterModel;
import com.example.ccimp.ui.presenter.RegisterPresenter;
import com.example.ccimp.ui.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    EditText etEmail, etAddress, etPhone, etPassword, etRePassword;
    TextView tvLogin;
    Spinner spinner;
    Button btnSignup;
    RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.input_email);
        etAddress = findViewById(R.id.input_address);
        etPhone = findViewById(R.id.input_phoneNumber);
        etPassword = findViewById(R.id.input_password);
        etRePassword = findViewById(R.id.input_reEnterPassword);
        spinner = (Spinner)findViewById(R.id.input_userType);
        tvLogin = findViewById(R.id.link_login);

        btnSignup = findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String address = etAddress.getText().toString();
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();
                String rePassword = etRePassword.getText().toString();
                String userType = spinner.getSelectedItem().toString();
                String type = "register";
                mRegisterPresenter.performSignup(type, email, address, phone, password, rePassword, userType, RegisterActivity.this);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        mRegisterPresenter = new RegisterModel(RegisterActivity.this);
    }

    @Override
    public void signupValidations(){
        Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_LONG).show();
    }

    @Override
    public void signupSuccess(){
        Toast.makeText(getApplicationContext(), "Signup Success", Toast.LENGTH_LONG).show();
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    @Override
    public void pwdUnmatch(){
        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_LONG).show();
    }
}
