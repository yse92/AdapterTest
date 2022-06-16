package com.example.listviewtodo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.listviewtodo.model.Person;
import com.example.listviewtodo.model.Sex;
import com.example.listviewtodo.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.Inflater;

public class CustomDialogFragment extends DialogFragment {

    private Editable editable;

    private LayoutInflater inflater;

    Person person;

    int position;

    EditText editFirstName, editSecondName;

    DatePicker datePicker;

    Date currentDate;

    Calendar calendar = new GregorianCalendar();

//    вызывается в начале жизненного цикла фрагмента,
//    и именно здесь мы можем получить контекст фрагмента,
//    в качестве которого выступает класс MainActivity.
//    Так как MainActivity реализует интерфейс Removable,
//    то мы можем преобразовать контекст к данному интерфейсу.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editable = (Editable) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /**
         *         set person's data to dialog edit text
         *
         *         setTitle: устанавливает заголовок окна
         *
         *         setView: устанавливает разметку интерфейса окна
         *
         *         setIcon: устанавливает иконку окна
         *
         *         setPositiveButton: устанавливает кнопку подтверждения действия
         *
         *         setNeutralButton: устанавливает "нейтральную" кнопку, действие которой может отличаться от действий подтверждения или отмены
         *
         *         setNegativeButton: устанавливает кнопку отмены
         *
         *         setMessage: устанавливает текст диалогового окна, но при использовании setView данный метод необязателен или может рассматриваться в качестве альтернативы, если нам надо просто вывести сообщение.
         *
         *         create: создает окно
         */

        inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog, null, false);
        person = Utils.getPersonFromJson(getArguments().getString("person"));
        position = getArguments().getInt("position");

        editFirstName = view.findViewById(R.id.editFirstName);
        editSecondName = view.findViewById(R.id.editSecondName);
        datePicker = view.findViewById(R.id.datePicker);

        editFirstName.setText(person.getFirstName());
        editSecondName.setText(person.getSecondName());

        currentDate = person.getBirthDate();
        calendar.setTime(currentDate);
        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

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
                        Sex sex = person.getSex();
                        Date date = Utils.getDateFromDatePicker(datePicker);
                        Person changedPerson = new Person(firstName, secondName, sex, date);
                        //case edit:
                        ((MainActivity)getActivity()).edit(changedPerson, position);
                        //case add:

                    }
                })
//                )
                .setNegativeButton("Отмена", null)
                .create();
    }
}
