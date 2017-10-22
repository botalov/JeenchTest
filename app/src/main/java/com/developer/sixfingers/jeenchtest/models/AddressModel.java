package com.developer.sixfingers.jeenchtest.models;


public class AddressModel {
    String street;
    String suite;
    String city;
    String zipcode;
    GeoModel geo;


    @Override
    public String toString(){

        return "Address: "
                .concat(street).concat(", ")
                .concat(suite).concat(", ")
                .concat(city).concat(", ")
                .concat(zipcode).concat(". ")
                .concat("Position: ").concat(geo.toString())
                ;
    }
}
