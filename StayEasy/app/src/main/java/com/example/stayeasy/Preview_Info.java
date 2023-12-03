package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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

        TextView logoutTextView = findViewById(R.id.logoutId);
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logout functionality here
                logoutUser();
            }
        });
        Button button = findViewById(R.id.buttonSubmit1);

        TextView textViewName = findViewById(R.id.textViewName1);
        TextView textViewEmail = findViewById(R.id.textViewEmail1);

        TextView textViewPrice = findViewById(R.id.textViewPrice1);
        TextView textViewPhone = findViewById(R.id.textViewPhone);
        TextView textViewAddress = findViewById(R.id.textViewAddress);
        TextView textViewRoomType = findViewById(R.id.textViewRoomType);
        TextView textViewCheckin = findViewById(R.id.textViewCheckinDate);
        TextView textViewCheckout = findViewById(R.id.textViewCheckoutDate);
        TextView textViewNoofRoom = findViewById(R.id.noOfRooms);

        Intent intent = getIntent();
        Hotel hotel = intent.getParcelableExtra("hotelObject");
        PersonalInfo personalInfo=intent.getParcelableExtra("personalInfo");
        Room room=intent.getParcelableExtra("room");

        double price = intent.getDoubleExtra("roomPrice", 0.0);


        if (hotel != null) {
            textViewName.setText(hotel.getName());
            textViewEmail.setText(personalInfo.getEmail());
            textViewPhone.setText(personalInfo.getPhone());
            textViewAddress.setText(personalInfo.getAddress());
            textViewRoomType.setText(room.getSelectedRoomType());
            textViewCheckin.setText(room.getCheckinDate());
            textViewCheckout.setText(room.getCheckoutDate());
            textViewNoofRoom.setText(room.getNumberOfRooms());
            textViewPrice.setText("$ "+String.valueOf(price)+" Pay at Hotel");
        }




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Preview_Info.this, Confirmation.class);

                    hotel.setRoomInfo(room);
                    hotel.setPersonalInfo(personalInfo);

                    hotel.setPrice(price);
                    saveBookingToDatabase(hotel);

                    intent.putExtra("hotelObject", hotel);
                    intent.putExtra("personalInfo", personalInfo);
                    intent.putExtra("room", room);


                    Log.d("previewInfo123", hotel.getName());


                    startActivity(intent);
                }catch(Exception e){
                    e.printStackTrace();
                    Log.d("",e.getMessage());
                }

            }
        });
    }

  private void saveBookingToDatabase(Hotel hotel) {

        String bookingId = bookingsDatabase.push().getKey();
        bookingsDatabase.child(bookingId).setValue(hotel);
    }

    private void logoutUser() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Preview_Info.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity after logout
    }
}