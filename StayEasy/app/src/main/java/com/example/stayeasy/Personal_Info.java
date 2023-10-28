package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stayeasy.model.Hotel;

public class Personal_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        Button button = findViewById(R.id.button);

        Intent intent = getIntent();
        Hotel hotel = intent.getParcelableExtra("hotelObject");

        EditText name= findViewById(R.id.edName);
        EditText email= findViewById(R.id.edEmail);
        EditText phone= findViewById(R.id.edPhone);
        EditText address= findViewById(R.id.editTexAddress);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent roomInfoIntent = new Intent(Personal_Info.this, Room_Info.class);
                roomInfoIntent.putExtra("hotelObject", hotel);
                roomInfoIntent.putExtra("name",name.getText().toString());
                roomInfoIntent.putExtra("email",email.getText().toString());
                roomInfoIntent.putExtra("phone",phone.getText().toString());
                roomInfoIntent.putExtra("address",address.getText().toString());

                Log.d("hotel.getName()", hotel.getName());
                startActivity(roomInfoIntent);
            }
        });
    }
}
