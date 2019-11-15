package com.example.ccimp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ccimp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class SupplierProfileActivity extends AppCompatActivity {

    String userEmail;
    TextView user_Email;
    Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_profile);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            userEmail = bundle.getString("email");
        }

        btnlogout = findViewById(R.id.btnlogout);
        user_Email = findViewById(R.id.user_email);
        user_Email.setText(userEmail);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SupplierProfileActivity.this, MainActivity.class));
            }
        });

        BottomNavigationView navigation = findViewById(R.id.supplierNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.supplier_navigation_home:
                        Intent c = new Intent(SupplierProfileActivity.this,SupplierHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_supplier_inventory:
                        Intent d = new Intent(SupplierProfileActivity.this,SupplierInventoryActivity.class);
                        startActivity(d);
                        break;
                    case R.id.navigation_supplier_profile:
                        Intent b = new Intent(SupplierProfileActivity.this,SupplierProfileActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }
}