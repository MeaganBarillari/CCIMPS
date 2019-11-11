package com.example.ccimp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.LoginInterface;
import com.example.ccimp.ui.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginInterface.LoginView{
    EditText etEmail, etPassword;
    Button btnLogin;
    Spinner spinner;
    TextView tvSignup;
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.input_email);
        etPassword = findViewById(R.id.input_password);
        spinner = (Spinner) findViewById(R.id.login_userType);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String userEmail = etEmail.getText().toString();
                String userPassword = etPassword.getText().toString();
                String userType = spinner.getSelectedItem().toString();
                String type = "login";
                mLoginPresenter.performLogin(type, userEmail, userPassword, userType, LoginActivity.this);
            }
        });
        tvSignup = findViewById(R.id.link_signup);
        tvSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        mLoginPresenter = new LoginPresenter((LoginInterface.LoginView) LoginActivity.this);
    }

    @Override
    public void loginValidations(){
        Toast.makeText(getApplicationContext(), "Please enter email and password", Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginSuccess(){
        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void loginError(){
        Toast.makeText(getApplicationContext(), "Login Failure", Toast.LENGTH_LONG).show();
    }
}