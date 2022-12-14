package com.home.carrental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    DatabaseOperation db = new DatabaseOperation(this);
    TextInputEditText uname, uemail, upass;
    TextView gologin;
    Switch switchCarOwner;
    Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        gologin = findViewById(R.id.gologin);
        uname = findViewById(R.id.uname);
        uemail = findViewById(R.id.uemail);
        upass = findViewById(R.id.upass);
        switchCarOwner = findViewById(R.id.switch_car_owner);
        btn_signin = findViewById(R.id.btn_signin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (switchCarOwner.isChecked()) {
                    db.registerUser(uname.getText().toString(), uemail.getText().toString(), upass.getText().toString(), "", "", "", 1);
                } else {
                    db.registerUser(uname.getText().toString(), uemail.getText().toString(), upass.getText().toString(), "", "", "", 0);
                }

                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}