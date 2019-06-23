package com.example.klafa.carshar;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class PreMap extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, LocationListener{
    TextView textView, addr, addr2;
    SeekBar seekBar;
    Button button;

    int currentProgress;
    int currentMax = 1000, step = 100;

    LocationManager locationManager;
    Location lastKnownLocation;

    Json jso;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    SendingJson sendingJson;

    PostJSON postJSON;

    ArrayList<JsonResponse> arrObj;

    String[] str = new String[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_map);

        jso = new Json();
        Intent intent = new Intent();
        intent = getIntent();
        jso.arrayOfComp = intent.getIntegerArrayListExtra("array");
        jso.latitude = intent.getDoubleExtra("lat", 17.897633);
        jso.longitude = (Double)intent.getDoubleExtra("long", 17.781636);
        postJSON = new PostJSON();

        addr = findViewById(R.id.addr);
        addr2 = findViewById(R.id.addr2);
        addr.setText(jso.longitude + "");
        addr2.setText(jso.latitude + "");

        textView = findViewById(R.id.text);
        seekBar = findViewById(R.id.radius);
        seekBar.setMax(currentMax / step);
        seekBar.setOnSeekBarChangeListener(this);
        jso.rad = seekBar.getProgress();
        button = findViewById(R.id.nextMap);
        settings = getSharedPreferences("AppSettings", MODE_PRIVATE);
        editor = settings.edit();
        sendingJson = new SendingJson();

        arrObj = new ArrayList<>();

        button.setEnabled(false);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, (LocationListener) this);
    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        currentProgress = progress * step;
        textView.setText(currentProgress + "");
        if (progress == 1| progress == 0){
            jso.rad = 0.0014;
        }
        jso.rad = progress * 0.0007;

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    ////********************LOCATION*********************////
    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            return;
        }else {

            lastKnownLocation = location;

            jso.latitude = location.getLatitude();
            jso.longitude = location.getLongitude();
            jso.location = location;
            button.setEnabled(true);

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.putInt("Radius", currentProgress);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentProgress = settings.getInt("Radius", 100);
        seekBar.setProgress(currentProgress/100);
    }

    public void nextMap (View v) throws Exception {
        Intent intent = new Intent(this, Map.class);

        //intent.putExtra("lat", lastKnownLocation.getLatitude());
        //intent.putExtra("long", lastKnownLocation.getLongitude());

        JsonSend jsonSend = new JsonSend();

        jsonSend.setArrCompany(jso.arrayOfComp);

        //jsonSend.setLatitude(jso.latitude);
        //jsonSend.setLongitude(jso.longitude);
        jsonSend.setLatitude(37.646);
        jsonSend.setLongitude(55.762);
        //jsonSend.setRadius(jso.rad);
        jsonSend.setRadius(0.005);
        jsonSend.setDeLatitude(55.642);
        jsonSend.setDeLongitude(55.766);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://176.223.138.38:4222")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        CarsApi carsApi = retrofit.create(CarsApi.class);
        Call<ArrayList<JsonResponse>> call = carsApi.getStringScalar(jsonSend);
        call.enqueue(new Callback<ArrayList<JsonResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonResponse>> call, Response<ArrayList<JsonResponse>> response) {
                if (response.isSuccessful()){
                    addr.setText(response.body().size() + "Все ок");
                    /*for (int i = 0; i < response.body().size(); i++){
                        arrObj.add(response.body().get(i));
                    }*/

                }
                else {
                    addr.setText("Все плохо");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<JsonResponse>> call, Throwable t) {
                addr2.setText(t.getMessage());


            }
        });

        intent.putExtra("Cars", arrObj);
        intent.putExtra("lat", jso.latitude);
        intent.putExtra("long", jso.longitude);
        //startActivity(intent);
    }
}
