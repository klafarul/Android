package com.example.klafa.carshar;

import android.location.Location;

import java.util.ArrayList;

public class Json {


    ArrayList<Integer> arrayOfComp = new ArrayList<Integer>();
    double longitude;
    double latitude;
    double rad;
    String address;
    Location location;

    public void convertArrayToArrayList(int[] arr){
        arrayOfComp.clear();
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != 0){
                arrayOfComp.add(arr[i]);
            }
        }
    }
     public String arrayToString(ArrayList<Integer> arr){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.size(); i++){
            result.append(arr.get(i));
            result.append('q');
        }
        return null;
     }
     Json(){
        longitude = 0.0;
        latitude = 0.0;
        rad = 0.0;
        address = "";
        location = null;
    }
}
