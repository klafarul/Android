package com.example.klafa.carshar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class routeHuman {
    @SerializedName("durationHuman")
    @Expose
    double durationHuman;
    @SerializedName("distanceHuman")
    @Expose
    double distanceHuman;

    double getDurationHuman(){
        return this.durationHuman;
    }
    void setDurationHuman(double durationHuman){
        this.durationHuman = durationHuman;
    }

    double getDistanceHuman(){
        return this.distanceHuman;
    }
    void setDistanceHuman(double distanceHuman){
        this.distanceHuman = distanceHuman;
    }
}
