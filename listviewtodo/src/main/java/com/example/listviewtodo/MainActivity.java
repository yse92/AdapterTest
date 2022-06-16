package com.example.listviewtodo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listviewtodo.adapter.PersonAdapter;
import com.example.listviewtodo.model.Person;
import com.example.listviewtodo.model.Sex;
import com.example.listviewtodo.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements Editable {

    @SuppressLint("SimpleDateFormat")
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    ArrayList<Person> persons = new ArrayList<>();
    ListView personsList;
    PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        personsList = findViewById(R.id.personsList);

        personAdapter = new PersonAdapter(this, R.layout.list_item, persons);

        personsList.setAdapter(personAdapter);

        /**
         * не работает вместе с конпками внутри отдельного элемента list_item
         */
//                AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        //                Log.d("TAG", "1111111111111111111111111");
//                        Person p = (Person) parent.getItemAtPosition(position);
//                        Toast.makeText(getApplicationContext(), "person " + p.getFirstName() + " " + p.getSecondName(), Toast.LENGTH_SHORT).show();
//                    }
//                };
//                personsList.setOnItemClickListener(itemClickListener);

    }

    private void initData() {
        try {
            persons.add(new Person("Николай", "Мулин", Sex.MALE, format.parse("15/10/1992")));
            persons.add(new Person("Анна", "Волкова", Sex.FEMALE, format.parse("17/12/2002")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void showDialog(View v) {

        int position = personsList.getPositionForView((View) v.getParent());
        Person person = personAdapter.getItem(position);

        CustomDialogFragment dialog = new CustomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("person", Utils.getGsonParser().toJson(person));
        bundle.putInt("position", position);

        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "custom");
    }

    public void deletePerson(View v) {
        int position = personsList.getPositionForView((View) v.getParent());
        persons.remove(position);
        personAdapter.notifyDataSetChanged();
    }

    @Override
    public void edit(Person person, int position) {

        persons.set(position, person);
        personAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}