package com.example.adaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Способ 1
         */
//
//        ListView countriesList = findViewById(R.id.countriesList);
//
//        //adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, countries);
//        //this - текущий объект activity
//
//        //android.R.layout.simple_list_item_1 - файл разметки списка, который фреймворк представляет по умолчанию.
//        // Он находится в папке Android SDK по
//        // пути platforms/[android-номер_версии]/data/res/layout.
//        // Если нас не удовлетворяет стандартная разметка списка,
//        // мы можем создать свою и потом в коде изменить id на id нужной нам разметки
//
//        //countries - массив данных. Здесь необязательно указывать именно массив, это может быть список ArrayList<T>
//
//        countriesList.setAdapter(adapter);

        /**
         * Способ 2
         */
        ListView countriesList = findViewById(R.id.countriesList);

        //get resource
        String [] c = getResources().getStringArray(R.array.countries);

        //create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, c);

        countriesList.setAdapter(adapter);

    }
}