package com.example.listview_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<>();
    ArrayList<String> selectedUsers = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView usersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Collections.addAll(users, "Nikolay", "Evgen", "Boris", "Daniil", "Alexey");

        usersList = findViewById(R.id.usersList);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, users);

        usersList.setAdapter(adapter);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String user = adapter.getItem(position);
                if(usersList.isItemChecked(position)) {
                    selectedUsers.add(user);
                } else {
                    selectedUsers.remove(user);
                }
            }
        });
    }

    public void add(View view) {
        EditText userName = findViewById(R.id.userName);

        String user = userName.getText().toString();

        if(!user.isEmpty()) {
            adapter.add(user);
            userName.setText("");

            //refresh list view
            adapter.notifyDataSetChanged();
        }
    }
    public void remove(View view) {
        for(int i =0; i< selectedUsers.size(); i++) {
            adapter.remove(selectedUsers.get(i));
        }
        usersList.clearChoices();

        selectedUsers.clear();
        //refresh list view
        adapter.notifyDataSetChanged();
    }

}