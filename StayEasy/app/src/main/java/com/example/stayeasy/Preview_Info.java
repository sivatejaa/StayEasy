package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stayeasy.model.Hotel;

public class Preview_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_info);

        Button button = findViewById(R.id.buttonSubmit1);

        TextView textViewName = findViewById(R.id.textViewName1);
        TextView textViewEmail = findViewById(R.id.textViewEmail1);

        Intent intent = getIntent();
        Hotel hotel = intent.getParcelableExtra("hotelObject");

        if (hotel != null) {
            textViewName.setText(hotel.getName());
            textViewEmail.setText(hotel.getDescription());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Preview_Info.this, Confirmation.class);

                intent.putExtra("hotelObject", hotel);

                Log.d("previewInfo123",hotel.getName());

                startActivity(intent);
            }
        });
    }
}