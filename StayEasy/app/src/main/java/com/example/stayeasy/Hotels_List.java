package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hotels_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list);
        Button textViewClickable = findViewById(R.id.buttonView1);

        // Set an OnClickListener for the TextView
        textViewClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the intent to start the SecondActivity
                Intent intent = new Intent(Hotels_List.this, Personal_Info.class);

                // Start the SecondActivity when the TextView is clicked
                startActivity(intent);
            }
        });
    }
}