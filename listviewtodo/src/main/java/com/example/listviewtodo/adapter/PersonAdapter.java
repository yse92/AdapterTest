package com.example.listviewtodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.listviewtodo.R;
import com.example.listviewtodo.model.Person;
import com.example.listviewtodo.model.Sex;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.Inflater;

public class PersonAdapter extends ArrayAdapter<Person> {

    //create new view obj using inflater
    private LayoutInflater inflater;
    //id of layout
    private int layout;
    //list
    private List<Person> persons;

    DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);

        this.persons = objects;

        this.layout = resource;

        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = inflater.inflate(this.layout, viewGroup ,false);

        ImageView photo = view.findViewById(R.id.photo);
        TextView firstName = view.findViewById(R.id.firstName);
        TextView secondName = view.findViewById(R.id.secondName);
        TextView sex = view.findViewById(R.id.sex);
        TextView birthDate = view.findViewById(R.id.birthDay);

        Person person = persons.get(position);

        firstName.setText(person.getFirstName());
        secondName.setText(person.getSecondName());
        birthDate.setText(format.format(person.getBirthDate()));
        photo.setImageResource(person.getPhotoResource());

        /////////set template to date format !!!!!!

        if (person.getSex() == Sex.MALE) {
            sex.setText("пол: мужской");
        } else {
            sex.setText("пол: женский");
        }

        return view;
    }
}
