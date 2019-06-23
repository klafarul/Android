package com.example.klafa.carshar;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Intent intent;
    Button btnMap, btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnMap = findViewById(R.id.map);
        btnSet = findViewById(R.id.settings);

        //btnMap.setTextColor(Color.WHITE);
        //btnMap.setBackgroundColor(Color.BLACK);

    }

    public void openSettings(View view){
        intent = new Intent(this, settings.class);

        startActivity(intent);
    }

    public void openPreMap(View view){
        intent = new Intent(this, PreMap.class);

        startActivity(intent);
    }
}
