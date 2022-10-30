package com.example.mapprojectbook;




public class User {

    public String email;

    public String username;

    public String fname;

    public String lname;

    public String password;

    public String address;

    public String contactNo;

    public User(String email, String username, String fname, String lname, String password, String address, String contactNo) {
        this.email = email;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.address = address;
        this.contactNo = contactNo;
    }

    public User() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }

}
