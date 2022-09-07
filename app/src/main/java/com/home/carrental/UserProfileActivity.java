package com.home.carrental;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    private DatabaseOperation db = new DatabaseOperation(this);
    private TextView textViewEmail;
    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPostalCode;
    private EditText editTextNote;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final SharedPreferences shared = getSharedPreferences("credientials", MODE_PRIVATE);

        User user = db.login(shared.getString("uemail", ""),
                shared.getString("upass", ""));

        textViewEmail = findViewById(R.id.textview_email);
        textViewEmail.setText(user.email);

        editTextName = findViewById(R.id.edit_name);
        editTextName.setText(user.name);

        editTextAddress = findViewById(R.id.edit_address);
        editTextAddress.setText(user.address);

        editTextPostalCode = findViewById(R.id.edit_postal);
        editTextPostalCode.setText(user.postal);

        editTextNote = findViewById(R.id.edit_note);
        editTextNote.setText(user.note);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.name = editTextName.getText().toString();
                user.address = editTextAddress.getText().toString();
                user.postal = editTextPostalCode.getText().toString();
                user.note = editTextNote.getText().toString();
                db.updateUser(user);
                Toast.makeText(UserProfileActivity.this, "User profile updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}