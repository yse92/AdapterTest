package com.example.complex_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.complex_listview.adapter.StateAdapter;
import com.example.complex_listview.model.State;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<State> states = new ArrayList<>();
    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init
        setInitialData();
        countriesList = findViewById(R.id.countriesList);
        //adapter

        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        //set adapter
        countriesList.setAdapter(stateAdapter);
        //set listener
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                State selectedState = (State) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Item checked: " + selectedState.getName(), Toast.LENGTH_SHORT).show();

            }
        };

        countriesList.setOnItemClickListener(itemListener);
    }

    private void setInitialData() {
        states.add(new State("Brazil", "Brasilia", R.drawable.brazil));
        states.add(new State("Ecuador", "Quito", R.drawable.ecuador));
    }
}