package com.example.myapplication;

import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class Order {

    // string variable for
    // storing User name.
    private String FirstName;

    // string variable for storing
    // employee Last Name
    private String LastName;

    // string variable for storing
    // Phone Number.
    private String PhoneNumber;

    // string variable for storing
    // email address.
    private String EmailAddress;

    // string variable for storing
    // quantity.
    private String Quantity;

    // string variable for storing
    // address.
    private String Address;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public Order() {

    }

    public Order(String toString, String toString1, String toString2, String toString3, String toString4, String toString5) {
    }

    // created getter and setter methods
    // for all our variables.
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = Address;
    }
}

