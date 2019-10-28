package com.example.ccimp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.PresenterImpl;
import com.example.ccimp.ui.presenter.LoginPresenter;
import com.example.ccimp.ui.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView{
    EditText etEmail, etPassword;
    Button btnlogin;

    LoginPresenter mLoginPresenter;
    LoginView mLoginView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = findViewById(R.id.input_password);
        etEmail = findViewById(R.id.input_email);
        btnlogin = findViewById(R.id.btn_login);

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                mLoginPresenter.performLogin(email, password);
            }
        });

        mLoginPresenter = new PresenterImpl(LoginActivity.this);

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
