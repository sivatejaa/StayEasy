package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.stayeasy.model.Hotel;
import com.stayeasy.model.PersonalInfo;
import com.stayeasy.model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

                PersonalInfo personalInfo=intent.getParcelableExtra("personalInfo");

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


                hotel.setSelectedRoomType(selectedRoomType);
                hotel.setCheckinDate(checkinDate);
                hotel.setNumberOfRooms(numberOfRooms);
                hotel.setCheckoutDate(checkoutDate);

                Room room = new Room(selectedRoomType, numberOfRooms, checkinDate, checkoutDate);
                double roomPrice = calculatePrice(hotel);
                hotel.setPrice(roomPrice);

                intent.putExtra("hotelObject", hotel);

                intent.putExtra("personalInfo", personalInfo);
                Log.d("personalInfo.getName()", personalInfo.getName());
                intent.putExtra("room", room);
                intent.putExtra("roomPrice", roomPrice);

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

    private double calculatePrice(Hotel hotel){

        String roomType=hotel.getSelectedRoomType();
        String checkInDate=hotel.getCheckinDate();
        String checkoutDate=hotel.getCheckoutDate();
        int noOfRooms=Integer.parseInt(hotel.getNumberOfRooms());

        double roomPrice=0.0;
        if(roomType.equalsIgnoreCase("Single BedRoom")){
            roomPrice=100.0;
        }else if(roomType.equalsIgnoreCase("Double BedRoom")){
            roomPrice=200.0;
        }else if(roomType.equalsIgnoreCase("Deluxe BedRoom")){
            roomPrice=300.0;
        }else if(roomType.equalsIgnoreCase("Super Deluxe BedRoom")){
            roomPrice=400.0;
        }

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        long differenceInDays=0;
        try {
            Date startDate = format.parse(checkInDate);
            Date endDate = format.parse(checkoutDate);

            long differenceInMilliseconds = endDate.getTime() - startDate.getTime();
            differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);

            System.out.println("Number of days between the dates: " + differenceInDays);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        roomPrice=(differenceInDays*roomPrice)*noOfRooms;

        return roomPrice;







    }
}