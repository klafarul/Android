package com.example.klafa.carshar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class routeCar {
    @SerializedName("durationCar")
    @Expose
    double durationCar;
    @SerializedName("distanceCar")
    @Expose
    double distanceCar;

    double getDurationCar(){        return durationCar;    }
    void setDurationCar(double durationCar){        this.durationCar = durationCar;    }

    double getDistanceCar(){        return distanceCar;    }
    void setDistanceCar(double distanceCar){        this.distanceCar = distanceCar;    }
}
