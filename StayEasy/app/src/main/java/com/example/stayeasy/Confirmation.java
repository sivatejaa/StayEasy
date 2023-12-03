package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView logoutTextView = findViewById(R.id.logoutId);
        Button backhome_button = findViewById(R.id.backhome_button);

        TextView confirmationNumberTextView = findViewById(R.id.bookingID);

        String confirmationNumber = generateConfirmationNumber();
        confirmationNumberTextView.setText("Booking ID: " + confirmationNumber);



        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logoutUser();
            }
        });

        backhome_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Confirmation.this, Hotels_List.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private String generateConfirmationNumber() {

        String timestamp = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());


        Random random = new Random();
        int randomNumber = random.nextInt(1000);


        return " " +timestamp+  randomNumber;
    }

    private void logoutUser() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Confirmation.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}