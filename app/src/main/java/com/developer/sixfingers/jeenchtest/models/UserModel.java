package com.developer.sixfingers.jeenchtest.models;


public class UserModel {
    int id;
    String name;
    String userName;
    String email;

    AddressModel address;

    String phone;
    String website;


    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getAddress() {return address.toString(); }
    public int getId() {return id;}


}
