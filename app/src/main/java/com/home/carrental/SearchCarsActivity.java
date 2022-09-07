package com.home.carrental;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchCarsActivity extends AppCompatActivity {

    private DatabaseOperation db = new DatabaseOperation(this);
    private List<Car> cars;
    private SearchCarsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cars);

        cars = db.getAllCars();
        if (cars.size() == 0) {
            Toast.makeText(SearchCarsActivity.this, "No car found", Toast.LENGTH_SHORT).show();
        }

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new SearchCarsAdapter(this, cars);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter);
    }
}