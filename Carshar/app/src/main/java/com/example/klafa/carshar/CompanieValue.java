package com.example.klafa.carshar;


import java.util.ArrayList;

public class CompanieValue {


    public Pair comAndval[] = new Pair[8];

    String getCompany(int i){
        return comAndval[i].company;

    }
    public int getVallueByCompanie(String companie){
        for (int i = 0; i < comAndval.length; i++){
            if (comAndval[i].company.equals(companie)){
                return comAndval[i].value;
            }
        }
        return 0;
    }
    public String getCompanieByValue(int value){
        String err = "нет команиии которая соответствует этому значению";
        for (int i = 0; i < comAndval.length; i++){
            if (comAndval[i].value == value){
                return comAndval[i].company;
            }
        }
        return err;
    }

    public int getPositionByValue(int value){
        switch (value){
            case 8: return 0;
            case 9: return 1;
            case 11: return 2;
            case 13: return 3;
            case 14: return 4;
            case 24: return 5;
            case 26: return 6;
            case 27: return 7;
            default: return 8;
        }
    }
    public void fillOutList(){

        for (int i = 0; i < comAndval.length; i++){
            comAndval[i] = new Pair();
        }

        comAndval[0].updateValues("Делимобиль",8);
        comAndval[1].updateValues("Car5", 9);
        comAndval[2].updateValues("YouDrive", 11);
        comAndval[3].updateValues("RentMee", 13);
        comAndval[4].updateValues("МатрешCar", 14);
        comAndval[5].updateValues("CAR4YOY", 24);
        comAndval[6].updateValues("Anytime", 26);
        comAndval[7].updateValues("Berimobil", 27);

    }

}

class Pair{
    public String company;
    public int value;


    public void updateValues(String comp, int val){
        this.company = comp;
        this.value = val;
    }

    public String getCompany(){
        return company;
    }
    int getValue(){
        return value;
    }
}