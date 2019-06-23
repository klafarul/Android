package com.example.klafa.carshar;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class PostJSON extends AsyncTask<String, Void, Void> {



    //PostJSON postJson;


    /*public void dosome(double longitude, double latitude, double radius, ArrayList<Integer> arr) throws Exception {
        postJson = new PostJSON();
        postJson.sendPost(longitude, latitude, radius, arr);
    }*/
    JSONObject object;
    JSONArray jsonArray;
    void sendPost(double longitude, double latitude, double radius, ArrayList<Integer> arr) throws Exception{


    }

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
    protected Void doInBackground(String... strings) {

        StringBuilder stringBuilder = new StringBuilder(strings[3]);
        ArrayList<Integer> mas = new ArrayList<>(stringToArray(stringBuilder));
        object = new JSONObject();

        try {
            object.put("longitude", Double.parseDouble(strings[0]));
            object.put("latitude", Double.parseDouble(strings[1]));
            object.put("deLatitude", 55.443);
            object.put("deLongitude", 37.391);
            object.put("radius", Double.parseDouble(strings[2]));
            object.put("ArrayOfCompanies", mas);
            //object.put("Longitude", Double.parseDouble(voids[1]));


        } catch (JSONException e) {
            e.printStackTrace();
        }

      /*  String url = "https://176.223.138.38:4222/fetch";
        URL obj = new URL(url);

        URLConnection con =   obj.openConnection();

        //con.connect();
        con.setDoOutput(true);
        //con.disconnect();
        //con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-type", "application/json");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Languge", "en-US,en;q=0.5");



         DataOutputStream dos = new DataOutputStream((DataOutputStream) con.getOutputStream());
      dos = new DataOutputStream(con.getOutputStream());
        //ObjectOutputStream oos = new ObjectOutputStream(con.getOutputStream());
        dos.writeBytes(object.toString());
        dos.flush();
        oos.close();

         BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = bf.readLine()) != null){
            response.append(line + "\n");
        }
        bf.close();

        String jsonArrayString = response.toString();
        jsonArray = new JSONArray(jsonArrayString);*/

        return null;
    }
}
