package com.example.klafa.carshar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.security.Permission;

public class MainActivity extends Activity implements LocationListener, SeekBar.OnSeekBarChangeListener {

    CheckBox checkBox;
    CompanieValue companieValue;
    LocationManager locationManager;
    Location lastKnownLocation;
    TextView loc,bar;
    EditText address;
    SeekBar seekBar;
    int currentMax = 1000, step = 100;
    int currentProgress;
    int seek;
    Json jso;



    int arrayOfCars[]; // массив с values машин, которые выбрал пользователь(отправится в json файле)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loc = findViewById(R.id.location);
        companieValue = new CompanieValue();
        arrayOfCars = new int[4];
        companieValue.fillOutList();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        bar = (TextView) findViewById(R.id.seek);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(currentMax / step);

        seekBar.setOnSeekBarChangeListener(this);
        jso = new Json();

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
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            return;
        }else {
            lastKnownLocation = location;
            jso.latitude = location.getLatitude();
            jso.longitude = location.getLongitude();
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


    protected void showCars(View view) {
        int i = 0;

        StringBuilder comp = new StringBuilder("");
        checkBox = (CheckBox) findViewById(R.id.Deli);
        if (checkBox.isChecked()) {
            arrayOfCars[i] = companieValue.getVallueByCompanie(checkBox.getText().toString());
        } else {
            arrayOfCars[i] = 0;
        }
        i++;

        checkBox = (CheckBox) findViewById(R.id.Car5);
        if (checkBox.isChecked()) {
            arrayOfCars[i] = companieValue.getVallueByCompanie(checkBox.getText().toString());
        } else {
            arrayOfCars[i] = 0;
        }
        i++;

        checkBox = (CheckBox) findViewById(R.id.YouDrive);
        if (checkBox.isChecked()) {
            arrayOfCars[i] = companieValue.getVallueByCompanie(checkBox.getText().toString());
        } else {
            arrayOfCars[i] = 0;
        }
        i++;

        checkBox = (CheckBox) findViewById(R.id.Karenda);
        if (checkBox.isChecked()) {
            arrayOfCars[i] = companieValue.getVallueByCompanie(checkBox.getText().toString());
        } else {
            arrayOfCars[i] = 0;
        }

        for (i = 0; i < arrayOfCars.length; i++) {
            if (arrayOfCars[i] != 0) {
                comp.append(companieValue.getCompanieByValue(arrayOfCars[i]));
                comp.append(" ");
            }
        }
       // System.arraycopy(arrayOfCars,0, jso.arrayOfCompanies, 0, arrayOfCars.length);
        TextView textView = (TextView) findViewById(R.id.selectedCars);
        textView.setText(comp.toString());


        comp.delete(0,comp.length());

        if (lastKnownLocation != null) {
            loc.setText(comp.append(lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude()));
            loc.setText(comp.append(" "));
        }
        else
            loc.setText("Wait pls");



    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        currentProgress = progress * step;
        bar.setText("" + currentProgress);
        jso.rad  = currentProgress;

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {


    }
    public  void send(View view){
        address = (EditText) findViewById(R.id.address);
        jso.address = address.getText().toString();
        TextView textView = (TextView) findViewById(R.id.selectedCars);
        textView.setTextColor(Color.CYAN);

    }
}
