package com.example.dialogfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements Removable{
    private ArrayAdapter<Phone> adapter;
    ArrayList<Phone> phones;

    @Override
    public void Remove(int pos) {
        //adapter.remove(phone);

        phones.remove(pos);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView phonesList = findViewById(R.id.phonesList);

        phones = new ArrayList<>();
        phones.add(new Phone(29000,"Google Pixel"));
        phones.add(new Phone(14000,"Huawei P9"));
        phones.add(new Phone(17000, "LG G5"));
        phones.add(new Phone(22000,"Samsung Galaxy S8"));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phones);
        phonesList.setAdapter(adapter);

        phonesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Phone selectedPhone = adapter.getItem(position);
                CustomDialogFragment dialog = new CustomDialogFragment();
                Bundle args = new Bundle();

                args.putString("phone", Utils.getGsonParser().toJson(selectedPhone));
                args.putInt("position", position);

                dialog.setArguments(args);
                dialog.show(getSupportFragmentManager(), "custom");
            }
        });
    }
}