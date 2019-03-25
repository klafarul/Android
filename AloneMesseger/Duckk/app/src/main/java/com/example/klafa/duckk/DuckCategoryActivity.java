package com.example.klafa.duckk;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DuckCategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck_category);

        ArrayAdapter<Duck> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Duck.ducks);

        ListView listView = (ListView) findViewById(R.id.list_ducks);
        listView.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DuckCategoryActivity.this, DuckActivity.class);
                intent.putExtra(DuckActivity.EXTRA_DUCK, (int) id);
                startActivity(intent);

            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }

}
