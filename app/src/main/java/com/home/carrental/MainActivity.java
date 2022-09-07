package com.home.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseOperation db = new DatabaseOperation(this);
    private Button buttonUserProfile;
    private Button buttonSearchCars;
    private Button buttonRegisterCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUserProfile = findViewById(R.id.button_user_profile);
        buttonSearchCars = findViewById(R.id.button_search_cars);
        buttonRegisterCar = findViewById(R.id.button_register_car);

        buttonUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        buttonSearchCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Car> cars = db.getAllCars();
                Log.d("", "cars.size(): " + cars.size());
                Intent intent = new Intent(MainActivity.this, SearchCarsActivity.class);
                startActivity(intent);
            }
        });

        buttonRegisterCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterCarActivity.class);
                startActivity(intent);
            }
        });
    }
}