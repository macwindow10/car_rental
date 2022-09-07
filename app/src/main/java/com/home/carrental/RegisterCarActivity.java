package com.home.carrental;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterCarActivity extends AppCompatActivity {

    private DatabaseOperation db = new DatabaseOperation(this);
    private EditText editTextModel;
    private EditText editTextNumber;
    private EditText editTextSeats;
    private EditText editTextBrand;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_car);

        final SharedPreferences shared = getSharedPreferences("credientials", MODE_PRIVATE);

        User user = db.login(shared.getString("uemail", ""),
                shared.getString("upass", ""));

        editTextModel = findViewById(R.id.edit_model);
        editTextNumber = findViewById(R.id.edit_number);
        editTextSeats = findViewById(R.id.edit_seats);
        editTextBrand = findViewById(R.id.edit_brand);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addCar(user.id,
                        editTextModel.getText().toString(),
                        editTextNumber.getText().toString(),
                        editTextSeats.getText().toString(),
                        editTextBrand.getText().toString());
                Toast.makeText(RegisterCarActivity.this, "Car registered", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}