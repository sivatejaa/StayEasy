package com.example.stayeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.stayeasy.model.Hotel;
import com.stayeasy.model.PersonalInfo;
import com.stayeasy.model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Room_Info extends AppCompatActivity {

    private EditText checkinDateEditText;
    private EditText checkoutDateEditText;
    private Calendar checkinCalendar;
    private Calendar checkoutCalendar;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);
        Button button = findViewById(R.id.button2);

        checkinCalendar = Calendar.getInstance();
        checkoutCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        TextView logoutTextView = findViewById(R.id.logoutId);
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logout functionality here
                logoutUser();
            }
        });

        final Spinner roomTypeSpinner = findViewById(R.id.spinnerType);
        final EditText numberOfRoomsEditText = findViewById(R.id.textViewRooms);
        checkinDateEditText = findViewById(R.id.textViewCheckin);
        checkoutDateEditText = findViewById(R.id.textViewCheckout);

        checkinDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(checkinCalendar, checkinDateEditText);
            }
        });

        checkoutDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(checkoutCalendar, checkoutDateEditText);
            }
        });

        Intent intent = getIntent();
        Hotel hotel = intent.getParcelableExtra("hotelObject");
        PersonalInfo personalInfo = intent.getParcelableExtra("personalInfo");

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
                } else {
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

    private void showDatePickerDialog(final Calendar calendar, final EditText dateEditText) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        dateEditText.setText(dateFormat.format(calendar.getTime()));
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private double calculatePrice(Hotel hotel) {
        // Your existing calculatePrice code...
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

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Room_Info.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity after logout
    }
}
