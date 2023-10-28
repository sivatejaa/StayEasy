package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stayeasy.model.Hotel;

public class Room_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);
        Button button = findViewById(R.id.button2);

        Intent intent = getIntent();
        Hotel hotel = intent.getParcelableExtra("hotelObject");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Room_Info.this, Preview_Info.class);

                intent.putExtra("hotelObject", hotel);

                startActivity(intent);
            }
        });
    }
}