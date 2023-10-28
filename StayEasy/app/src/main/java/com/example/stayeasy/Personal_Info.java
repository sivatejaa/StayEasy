package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

        EditText nameEditText= findViewById(R.id.edName);
        EditText emailEditText= findViewById(R.id.edEmail);
        EditText phoneEditText= findViewById(R.id.edPhone);
        EditText addressEditText= findViewById(R.id.editTexAddress);
        Button button = findViewById(R.id.button);

        Intent intent = getIntent();
        Hotel hotel = intent.getParcelableExtra("hotelObject");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)) {

                    new AlertDialog.Builder(Personal_Info.this)
                            .setTitle("Error")
                            .setMessage("Please enter all the fields.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }else{
                    Intent roomInfoIntent = new Intent(Personal_Info.this, Room_Info.class);
                    roomInfoIntent.putExtra("hotelObject", hotel);
                    roomInfoIntent.putExtra("name",name);
                    roomInfoIntent.putExtra("email",email);
                    roomInfoIntent.putExtra("phone",phone);
                    roomInfoIntent.putExtra("address",address);
                    Log.d("hotel.getName()", hotel.getName());
                    startActivity(roomInfoIntent);
                }




            }
        });
    }
}
