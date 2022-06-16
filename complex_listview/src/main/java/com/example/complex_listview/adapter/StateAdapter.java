package com.example.complex_listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.complex_listview.R;
import com.example.complex_listview.model.State;

import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {
    private LayoutInflater inflater;

    private int layout;

    private List<State> states;

    public StateAdapter(@NonNull Context context, int resource, @NonNull List<State> states) {
        super(context, resource, states);

        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    //отображение элемента списка
    public View getView(int position, View convertView, ViewGroup parent) {
        //создаем объект View для каждого отдельного элемента в списке
        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.flag);
        TextView nameView = view.findViewById(R.id.name);
        TextView capitalView = view.findViewById(R.id.capital);

        State state = states.get(position);

        flagView.setImageResource(state.getFlagResource());
        nameView.setText(state.getName());
        capitalView.setText(state.getCapital());

        return view;
    }
}
