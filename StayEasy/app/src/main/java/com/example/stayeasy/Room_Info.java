package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.stayeasy.model.Hotel;

public class Room_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);
        Button button = findViewById(R.id.button2);

        final Spinner roomTypeSpinner = findViewById(R.id.spinnerType);
        final EditText numberOfRoomsEditText = findViewById(R.id.textViewRooms);
        final EditText checkinDateEditText = findViewById(R.id.textViewCheckin);
        final EditText checkoutDateEditText = findViewById(R.id.textViewCheckout);
        Intent intent = getIntent();
        Hotel hotel = intent.getParcelableExtra("hotelObject");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedRoomType = roomTypeSpinner.getSelectedItem().toString();


                String numberOfRooms = numberOfRoomsEditText.getText().toString().trim();
                String checkinDate = checkinDateEditText.getText().toString().trim();
                String checkoutDate = checkoutDateEditText.getText().toString().trim();

                if (selectedRoomType.equals("Select") || numberOfRooms.isEmpty() || checkinDate.isEmpty() || checkoutDate.isEmpty()) {
                    if (selectedRoomType.equals("Select")) {

                        showErrorDialog("Please select a room type.");
                    } else {

                        showErrorDialog("Please enter all the fields.");
                    }
                }else{
                    Intent intent = new Intent(Room_Info.this, Preview_Info.class);

                    intent.putExtra("hotelObject", hotel);

                    startActivity(intent);
                }


            }
        });
    }
    private void showErrorDialog(String errorMessage) {
        new AlertDialog.Builder(Room_Info.this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }
}