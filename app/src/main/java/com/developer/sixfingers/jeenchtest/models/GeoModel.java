package com.developer.sixfingers.jeenchtest.models;


public class GeoModel {
    double lat;
    double lng;

    @Override
    public String toString(){

        return "Latitude: ".concat(String.valueOf(lat)).concat(", ")
                .concat("Longitude: ").concat(String.valueOf(lng))
                ;
    }
}
