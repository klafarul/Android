package com.example.klafa.carshar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonSend {

    @SerializedName("longitude")
    @Expose
    double longitude;
    @SerializedName("latitude")
    @Expose
    double latitude;
    @SerializedName("deLatitude")
    @Expose
    double deLatitude;
    @SerializedName("deLongitude")
    @Expose
    double deLongitude;
    @SerializedName("radius")
    @Expose
    protected double radius;
    @SerializedName("arrCompany")
    @Expose
    ArrayList<Integer> arrCompany = new ArrayList<Integer>();


    double getLongitude() {
        return this.longitude;
    }

    void setLongitude(double longitude){
        this.longitude = longitude;
    }

    double getLatitude(){
        return this.latitude;
    }

    void setLatitude(double latitude){
        this.latitude = latitude;
    }

    double getDeLongitude(){
        return this.deLongitude;
    }

    void setDeLongitude(double deLongitude){
        this.longitude = deLongitude;
    }

    double getDeLatitude(){
        return this.deLatitude;
    }

    void setDeLatitude(double deLatitude){
        this.deLatitude = deLatitude;
    }


    double getRadius(){
        return this.radius;
    }
    void setRadius(double radius){
        this.radius = radius;
    }

    ArrayList<Integer> getArrCompany(){
            return  arrCompany;
    }

    void setArrCompany(ArrayList<Integer> arrCompanyy){
        for (int i = 0; i < arrCompanyy.size(); i++){
            this.arrCompany.add(arrCompanyy.get(i));
        }
    }



}
