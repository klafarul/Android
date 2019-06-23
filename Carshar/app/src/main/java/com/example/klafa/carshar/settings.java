package com.example.klafa.carshar;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class settings extends AppCompatActivity implements  LocationListener {

    ListView listView;
    TextView textView, loc;
    CompanieValue companieValue;

    int[] arrayToSend;


    LocationManager locationManager;
    Location lastKnownLocation;
    SharedPreferences settings;
    Json jso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = getSharedPreferences("AppSettings", MODE_PRIVATE);



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final String[] companies = getResources().getStringArray(R.array.companies);
        listView = (ListView) findViewById(R.id.companies);
        textView = (TextView) findViewById(R.id.text);

        arrayToSend = new int[8];
        companieValue = new CompanieValue();
        companieValue.fillOutList();
        jso = new Json();
        loc = findViewById(R.id.loc);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


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

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, companies );
        listView.setAdapter(adapter);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < arrayToSend.length; i++){
                    arrayToSend[i] = 0;
                }
                SparseBooleanArray chosen = ((ListView) parent).getCheckedItemPositions();
                for (int i = 0; i<chosen.size(); i++){

                    if (chosen.valueAt(i)){
                        arrayToSend[i] = companieValue.getVallueByCompanie(companies[chosen.keyAt(i)]);
                    }
                    else
                        arrayToSend[i] = 0;

                }
                jso.convertArrayToArrayList(arrayToSend);
            }
        });
    }

    //методы для seek бара


    //методы для геолокации
    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            return;
        }else {
            lastKnownLocation = location;

            jso.latitude = location.getLatitude();
            jso.longitude = location.getLongitude();
            jso.location = location;
            //loc.setText(jso.longitude + "");
            Toast.makeText(this, "" + jso.longitude, Toast.LENGTH_SHORT).show();
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

    public void next(View view){
        Intent intent = new Intent(this, PreMap.class);

        intent.putExtra("array", jso.arrayOfComp);
        intent.putExtra("lat", jso.latitude);
        intent.putExtra("long", jso.longitude);
        intent.putExtra("loc", jso.location);

        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //**********************СОХРАНЕНИЕ ВЫБРАННЫХ КОМПАНИЙ**********************//
        final SharedPreferences.Editor editor = settings.edit();
        for (int i = 0; i < arrayToSend.length; i++){
            editor.putInt("Count_" + i, arrayToSend[i]);
        }
        editor.apply();
    }


    @Override
    protected void onStop() {
        super.onStop();
        final SharedPreferences.Editor editor = settings.edit();
        for (int i = 0; i < arrayToSend.length; i++){
            editor.putInt("Count_" + i, arrayToSend[i]);
        }


        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i = 0; i < arrayToSend.length; i++){
            arrayToSend[i] = settings.getInt("Count_" + i, 0);

            if (arrayToSend[i] != 0){
                listView.setItemChecked(companieValue.getPositionByValue(arrayToSend[i]), true);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //**********************СОХРАНЕНИЕ ВЫБРАННЫХ КОМПАНИЙ**********************//
        final SharedPreferences.Editor editor = settings.edit();
        for (int i = 0; i < arrayToSend.length; i++){
            editor.putInt("Count_" + i, arrayToSend[i]);
        }
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //**********************ВОССТАНОВЛЕНИЕ ВЫБРАННЫХ В ПРОШЛЫЙ РАЗ КОМПАНИЙ********************//
        for (int i = 0; i < arrayToSend.length; i++){
            arrayToSend[i] = settings.getInt("Count_" + i, 0);
            if (arrayToSend[i] != 0){
                listView.setItemChecked(companieValue.getPositionByValue(arrayToSend[i]), true);
            }
        }
    }
}
