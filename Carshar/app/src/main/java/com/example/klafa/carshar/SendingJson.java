package com.example.klafa.carshar;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class SendingJson extends AsyncTask<String, Void, Void> {

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;
    JSONObject object;
    ObjectOutputStream oos;
    DataInputStream is;
    Json jso;
    JSONArray json;

    int[] ik = new int[8];

    ArrayList<Integer> stringToArray (StringBuilder str){
        ArrayList<Integer> mas = new ArrayList<>();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) != 'q'){
                temp.append(str.charAt(i));
            }
            else {
                mas.add(Integer.parseInt(temp.toString()));
                temp.delete(0, temp.length()-1);
            }
        }
        return mas;
    }

    @Override
    protected Void doInBackground(String... voids){

        jso = new Json();
        String mes = voids[3];
        StringBuilder stringBuilder = new StringBuilder(voids[3]);
        ArrayList<Integer> mas = new ArrayList<>(stringToArray(stringBuilder));
        object = new JSONObject();
        try {
            object.put("longitude", Double.parseDouble(voids[0]));
            object.put("latitude", Double.parseDouble(voids[1]));
            object.put("deLatitude", 55.443);
            object.put("deLongitude", 37.391);
            object.put("radius", Double.parseDouble(voids[2]));
            object.put("ArrayOfCompanies", mas);
            //object.put("Longitude", Double.parseDouble(voids[1]));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{
            URL url = new URL("http://176.223.138.38:4222/fetch");
            Socket s = new Socket();
            pw = new PrintWriter(s.getOutputStream(), true);
            oos = new ObjectOutputStream(s.getOutputStream());
            StringBuilder stringEntity = new StringBuilder(object.toString());
            oos.writeObject(object);
            oos.flush();

            is = new DataInputStream(s.getInputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            json = new JSONArray(in.readLine());
            oos.close();
            in.close();
            is.close();
            //String str = json.getJSONObject(1).getString("name");



        }
        catch (IOException ex){
            ex.getMessage();
        }catch (JSONException e){
            e.getMessage();
        }
        return null;
    }

    String retNameCar () throws JSONException {
        return json.getJSONObject(1).getString("name");
    }

}
