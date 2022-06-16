package com.example.listviewtodo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.listviewtodo.model.Person;
import com.example.listviewtodo.model.Sex;
import com.example.listviewtodo.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InsertDialogFragment extends DialogFragment {

    private Editable editable;

    private LayoutInflater inflater;

    Person person;

    EditText editFirstName, editSecondName;

    RadioGroup radioGroup;

    DatePicker datePicker;

    Date currentDate;

    int checked; //default

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editable = (Editable) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog, null, false);

        editFirstName = view.findViewById(R.id.editFirstName);
        editSecondName = view.findViewById(R.id.editSecondName);
        datePicker = view.findViewById(R.id.datePicker);
        radioGroup = view.findViewById(R.id.radios);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                checked = radioGroup.indexOfChild(radioButton);
            }
        });

        builder.setView(view);

        return builder
                .setTitle("Редактировать сотрудника")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setView(view)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String firstName = editFirstName.getText().toString();
                        String secondName = editSecondName.getText().toString();

                        Sex sex = radioGroup.getChildAt(checked).getId() == R.id.male ? Sex.MALE : Sex.FEMALE;

                        currentDate = Utils.getDateFromDatePicker(datePicker);

                        Person newPerson = new Person(firstName, secondName, sex, currentDate);

                        ((MainActivity)getActivity()).insert(newPerson);
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}
