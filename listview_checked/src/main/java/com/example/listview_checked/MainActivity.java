package com.example.listview_checked;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] countries = {"Мексика", "Канада", "США", "Панама", "Никарагуа"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get text view
        TextView selection = findViewById(R.id.selection);
        //get list view
        ListView countriesList = findViewById(R.id.countriesList);
        //create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);
        //set adapter
        countriesList.setAdapter(adapter);

        //create listener
        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = countries[position];
                selection.setText(selectedItem);
            }
        });

    }
}