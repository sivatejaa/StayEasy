package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stayeasy.model.Hotel;

import java.util.ArrayList;

public class Hotels_List extends AppCompatActivity {

    private ArrayList<Hotel> hotels = new ArrayList<>();
    private RecyclerView recyclerView;
    private HotelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list);

        hotels.add(new Hotel( R.drawable.motel_2,"Holiday Inn", "5 Hotel Chains with Two-Bedroom Suites You Can Book."));
        hotels.add(new Hotel(R.drawable.img,"Haytt", "2 bed 1 bath Super Deluxe Hotel 2 Star Hotel"));
        hotels.add(new Hotel(R.drawable.motel_4,"Skyline", "3 bed 1 bath Hotel 3 Star Hotel with a wide range of Facilities" ));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HotelAdapter(this, hotels);
        recyclerView.setAdapter(adapter);


    }
}
