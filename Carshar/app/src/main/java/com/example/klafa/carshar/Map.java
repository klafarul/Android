package com.example.klafa.carshar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.arrival.ArrivalPoint;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.Direction;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.AnimatedIcon;
import com.yandex.mapkit.map.Callback;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectDragListener;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.ModelStyle;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.uri.Uri;
import com.yandex.mapkit.*;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.ui_view.ViewProvider;

import java.util.ArrayList;
import java.util.List;


public class Map extends Activity {

    TextView textView;

    MapView mapview;

    ArrayList<JsonResponse> carsObjs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        MapKitFactory.setApiKey("babb47bb-0ad6-44e9-a3c9-761608953c82");
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_map);
        super.onCreate(savedInstanceState);

        Intent intent;
        intent = getIntent();
        Json jso = new Json();
        jso.latitude = intent.getDoubleExtra("lat",8.12);
        jso.longitude = intent.getDoubleExtra("long", 7.36  );

        carsObjs = new ArrayList<JsonResponse>();
        //Object cars = carsObjs.clone(intent.getParcelableArrayExtra("Cars"));


        textView = findViewById(R.id.carNameTitle);
        textView.setText("Машина ");

        ArrivalPoint arrivalPoint = new ArrivalPoint("123",new Point(jso.latitude, jso.longitude),new Direction(55.924970, 37.972180));


        mapview = (MapView)findViewById(R.id.mapview);



        mapview.getMap().move(
                new CameraPosition(new Point(jso.latitude, jso.longitude), 17.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
               null);


        mapview.getMap().getMapObjects().addPlacemark(new Point(jso.latitude, jso.longitude)).setDirection(4);
        mapview.getMap().getMapObjects().addPlacemark(new Point(jso.latitude-0.0007, jso.longitude));
        mapview.getMap().getMapObjects().addPlacemark(new Point(jso.latitude+0.0007, jso.longitude));
        }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent();
        Json jso = new Json();
        jso.latitude = intent.getDoubleExtra("lat",8.12);
        jso.longitude = intent.getDoubleExtra("long", 7.36);
        mapview.onStart();
        MapKitFactory.getInstance().onStart();

    }
}
