package com.example.listview_multiple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    String[] countries = {"Австралия", "Новая Зеландия", "Фиджи", "Французская Полинезия"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView selection = findViewById(R.id.selection);

        ListView countriesList = findViewById(R.id.countriesList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, countries);

        countriesList.setAdapter(adapter);

        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray selected = countriesList.getCheckedItemPositions();

                String selectedItem = "";

                for(int i=0; i < countries.length; i++)
                {
                    if(selected.get(i))
                        selectedItem += countries[i]+", ";
                }

                selection.setText(selectedItem);
            }
        });
    }

}