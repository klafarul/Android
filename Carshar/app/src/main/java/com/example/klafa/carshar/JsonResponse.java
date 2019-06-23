package com.example.klafa.carshar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponse {

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("latitude")
    @Expose
    double latitude;

    @SerializedName("longitude")
    @Expose
    double longitude;

    @SerializedName("partner_id")
    @Expose
    String partner_id;

    @SerializedName("fuel")
    @Expose
    int fuel;

    @SerializedName("id")
    @Expose
    protected String id;

    @SerializedName("routeHuman")
    @Expose
    routeHuman routeHuman;

    @SerializedName("routeCar")
    @Expose
    routeCar routeCar;


    String get_id(){        return this._id;    }
    void set_id(String _id){        this._id = _id;   }

    String getName(){        return this.name;    }
    void setName(String name){        this.name = name;    }

    String getPartner_id(){        return this.partner_id;    }
    void setPartner_id(String partner_id){        this.partner_id = partner_id;    }

    int getFuel (){        return this.fuel;    }
    void setFuel(int fuel){        this.fuel = fuel;    }

    double getLongitude(){        return this.longitude;    }
    void setLongitude(double longitude){        this.longitude = longitude;    }

    double getLatitude(){        return this.latitude;    }
    void setLatitude(double latitude) {        this.latitude = latitude;    }

    String getId(){        return this.id;    }
    void setId(String id){        this.id = id;    }

    routeHuman getRouteHuman() {        return routeHuman;    }
    void setRouteHuman(routeHuman rh){        this.routeHuman = rh;    }

    routeCar getRouteCar(){        return routeCar;    }
    void setRouteCar(routeCar rc){        this.routeCar = rc;    }
}
