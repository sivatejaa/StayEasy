package com.stayeasy.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.stayeasy.Room_Info;

public class Hotel implements Parcelable {

    private PersonalInfo personalInfo;
    private Room roomInfo;

    private int imageResource;

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }







    public Room getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(Room roomInfo) {
        this.roomInfo = roomInfo;
    }

    private String name;
    private String description;

    private String selectedRoomType ;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelectedRoomType() {
        return selectedRoomType;
    }

    public void setSelectedRoomType(String selectedRoomType) {
        this.selectedRoomType = selectedRoomType;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String numberOfRooms ;
    private  String checkinDate;
    private String checkoutDate ;

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    private double price;

    public Hotel(int imageResource, String name, String description) {
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
    }

    protected Hotel(Parcel in) {
        imageResource = in.readInt();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        @Override
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResource);
        dest.writeString(name);
        dest.writeString(description);
    }
}
