package com.home.carrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    DatabaseOperation db = new DatabaseOperation(this);
    TextView goregister;
    TextInputEditText uemail, upass;
    Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        goregister = findViewById(R.id.goregister);
        uemail = findViewById(R.id.uemail);
        upass = findViewById(R.id.upass);
        btn_signin = findViewById(R.id.btn_signin);
        final SharedPreferences shared = getSharedPreferences("credientials", MODE_PRIVATE);
        final SharedPreferences.Editor edit = shared.edit();

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = db.login(uemail.getText().toString(), upass.getText().toString());
                if (user.id > 0) {
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    edit.putString("uname", user.name);
                    edit.putString("uemail", user.email);
                    edit.putString("upass", user.password);
                    edit.putBoolean("carowner", user.carowner);
                    edit.commit();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login UnSuccessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // test
        if (db.getAllCars().size() == 0) {
            //db.addCar("Office Chairs");
            //db.addCar("Office Tables");
            //db.addCar("Dining Tables");
            //db.addCar("Sofas");
        }
    }
}