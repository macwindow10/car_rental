package com.home.carrental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchCarsAdapter extends RecyclerView.Adapter<SearchCarsAdapter.MyViewHolder>
        implements View.OnClickListener {

    private Context context;
    private RecyclerView recyclerView;
    private List<Car> products;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onClick(View view) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewCarModel;
        public TextView textViewCarBrand;
        public TextView textViewCarNumber;
        public TextView textViewCarSeats;

        public MyViewHolder(View view) {
            super(view);
            textViewCarModel = view.findViewById(R.id.textview_car_model);
            textViewCarBrand = view.findViewById(R.id.textview_car_brand);
            textViewCarNumber = view.findViewById(R.id.textview_number);
            textViewCarSeats = view.findViewById(R.id.textview_seats);
        }
    }

    public SearchCarsAdapter(Context context, List<Car> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public SearchCarsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_car_item, parent, false);
        return new SearchCarsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchCarsAdapter.MyViewHolder holder, int position) {
        Car car = this.products.get(position);
        holder.textViewCarModel.setText("Model : " + car.model);
        holder.textViewCarBrand.setText("Brand : " + car.brand);
        holder.textViewCarNumber.setText("Number : " + car.number);
        holder.textViewCarSeats.setText("Seats : " + car.seats);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
