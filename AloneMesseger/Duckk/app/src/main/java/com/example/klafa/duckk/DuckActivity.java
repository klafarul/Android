package com.example.klafa.duckk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DuckActivity extends Activity {

    TextView name;
    TextView description;
    ImageView img;


    public static final String EXTRA_DUCK = "DuckId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck);

        Intent intent = new Intent();
        int listId = (int) getIntent().getExtras().get(EXTRA_DUCK);
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        img = (ImageView) findViewById(R.id.img);

        Duck duck = Duck.ducks[listId];

        name.setText(duck.getName());
        description.setText(duck.getDescription());
        img.setImageResource(duck.getImageResourceId());
        img.setContentDescription(duck.getName());

    }
}
