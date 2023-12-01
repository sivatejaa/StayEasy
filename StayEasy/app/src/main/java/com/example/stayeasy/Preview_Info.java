package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stayeasy.model.Hotel;
import com.stayeasy.model.PersonalInfo;
import com.stayeasy.model.Room;
import com.utility.SendMail;

import java.util.ArrayList;

public class Preview_Info extends AppCompatActivity {


    private DatabaseReference bookingsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.bookingsDatabase = FirebaseDatabase.getInstance().getReference().child("Bookings");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_info);

        Button button = findViewById(R.id.buttonSubmit1);

        TextView textViewName = findViewById(R.id.textViewName1);
        TextView textViewEmail = findViewById(R.id.textViewEmail1);

        TextView textViewPrice = findViewById(R.id.textViewPrice1);

        Intent intent = getIntent();
        Hotel hotel = intent.getParcelableExtra("hotelObject");
        PersonalInfo personalInfo=intent.getParcelableExtra("personalInfo");
        Room room=intent.getParcelableExtra("room");

        double price = intent.getDoubleExtra("roomPrice", 0.0);



        if (hotel != null) {
            textViewName.setText(hotel.getName());
            textViewEmail.setText(hotel.getDescription());
            textViewPrice.setText(String.valueOf(price));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Preview_Info.this, Confirmation.class);

                hotel.setRoomInfo(room);
                hotel.setPersonalInfo(personalInfo);

                saveBookingToDatabase(hotel);

                intent.putExtra("hotelObject", hotel);
                intent.putExtra("personalInfo", personalInfo);
                intent.putExtra("room", room);


                //SendMail.sendMail("shivateja.tangella@gmail.com");
                Log.d("previewInfo123",hotel.getName());



                startActivity(intent);
            }
        });
    }

  private void saveBookingToDatabase(Hotel hotel) {

        String bookingId = bookingsDatabase.push().getKey();
        bookingsDatabase.child(bookingId).setValue(hotel);
    }
}