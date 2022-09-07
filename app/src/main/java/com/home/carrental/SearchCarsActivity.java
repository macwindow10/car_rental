package com.home.carrental;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchCarsActivity extends AppCompatActivity {

    private DatabaseOperation db = new DatabaseOperation(this);
    private List<Car> cars;
    private SearchCarsAdapter adapter;
    private EditText editTextSearch;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cars);

        cars = db.getAllCars();
        if (cars.size() == 0) {
            Toast.makeText(SearchCarsActivity.this, "No car found", Toast.LENGTH_SHORT).show();
        }

        editTextSearch = findViewById(R.id.edit_search);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new SearchCarsAdapter(this, cars);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter);
    }

    private void filter(String text) {
        List<Car> filteredCars = new ArrayList();
        for (Car d : cars) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.model.toLowerCase().contains(text.toLowerCase())) {
                filteredCars.add(d);
            }

            if (d.brand.toLowerCase().contains(text.toLowerCase())) {
                filteredCars.add(d);
            }

            if (d.number.toLowerCase().contains(text.toLowerCase())) {
                filteredCars.add(d);
            }
        }
        //update recyclerview
        adapter = new SearchCarsAdapter(this, filteredCars);
        recyclerView.setAdapter(adapter);
    }
}