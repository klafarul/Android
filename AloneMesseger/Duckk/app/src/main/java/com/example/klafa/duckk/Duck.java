package com.example.klafa.duckk;

public class Duck {
    private String name;
    private String description;
    private int imageResourceId;

    public static final Duck[] ducks = {
            new Duck("Kryakva", "Green head", R.drawable.kryak),
            new Duck("Home duck","white duck", R.drawable.home),
            new Duck("Long neck", "LOng neck", R.drawable.longn)
    };

    private Duck(String name, String description, int imageResourceId){
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }

    public String toString(){
        return this.name;
    }
}

