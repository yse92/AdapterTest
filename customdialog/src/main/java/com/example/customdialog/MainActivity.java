package com.example.customdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity
{
    // ----- Class constants -----------------------------------------------
    /**
     * Индекс в массиве arrStyle для стиля текста BOLD
     */

    private	final static int TEXT_STYLE_INDEX_BOLD = 0;

    /**
     * Индекс в массиве arrStyle для стиля текста ITALIC
     */

    private	final static int TEXT_STYLE_INDEX_ITALIC = 1;

    /**
     * Индекс в массиве arrStyle для стиля текста ALLCAPS
     */
    private	final static int TEXT_STYLE_INDEX_ALLCAPS = 2;

    // ----- Class members -------------------------------------------------
    /*
     * Поля, относящиеся к примеру на Диалоговое окно со
     * списком одиночного выбора
     * ---------------------------------------------------------------------
     */
    /**
     * Ссылка на главный виджет-контейнер Активности
     */
    private	LinearLayout llMain;

    /**
     * Цвет фона для главного виджета-контейнера llMain
     */
    private	int	clrForMain = Color.rgb(0xFF, 0xFF, 0xFF);

    /**
     * Сюда будем записывать текущий выбираемый пользователем цвет.
     * Если пользователь в Диалоговом окне нажмет "Cancel" то цвет
     * фона виджета llMain вернется в значение, какое указано в clrForMain.
     * Если пользоатель в Диалоговом окне нажмет "ОК" то clrForMain = clrTmpMain
     */
    private	int	clrTmpMain;

    /*
     * Поля, относящиеся к примеру на Диалоговое окно со
     * списком множественного выбора
     * ---------------------------------------------------------------------
     */
    /**
     * Ссылка на текстовое поле android.widget.TextView стиль
     * текста которого будет меняться с помощью диалогового
     * окна множественного выбора
     */
    private	TextView tvText;

    /**
     * Массив логических значений, содержащий признак наличия соответствующего
     * стиля у текста из виджета tvText.
     * Индексы массива задаются с помощью статических констант TEXT_STYLE_INDEX_XXXXX
     * <br />
     * true означает наличие соответствующего стиля у текста из виджета tvText
     */
    private	boolean[] arrStyle = new boolean[3];

    /**
     * Такой же массив, что и arrStyle, только этот массив непосредственно
     * передается диалоговому окну для значений списка множественного выбора.
     * Этот массив содержит
     * текущие выбираемые пользователем настройки стиля текста. В случае отмены
     * пользователем настроек, предыдущие значения настроек остаются в массиве
     * arrStyle. В случае подтверждения пользователем настроек стиля, значения
     * этого массива копируются в массив tvStyle.
     */
    private	boolean[] arrTmpStyle = new boolean[3];

    /*
     * Поля, относящиеся к примеру на Диалоговое окно выбора
     * даты android.app.DatePickerDialog
     * ---------------------------------------------------------------------
     */
    /**
     * Выбранный пользователем год
     */
    private	int	year;

    /**
     * Выбранный пользователем месяц (0..11)
     */
    private	int	month;

    /**
     * Выбранный пользователем день месяца
     */
    private	int	day;

    /*
     * Поля, относящиеся к примеру на Диалоговое окно выбора
     * времени android.app.TimePickerDialog
     * ---------------------------------------------------------------------
     */
    /**
     * Выбранное пользователем значение часа
     */
    private	int	hour = -1;

    /**
     * Выбранные пользователем значения минут
     */
    private	int	minute	= -1;

    // ----- Class methods -------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----- Инициализация полей объекта -----------------------------------
        this.llMain	= (LinearLayout)	this.findViewById(R.id.llMain);
        this.tvText	= (TextView)		this.findViewById(R.id.tvText);

    }

    public	void	btnClick(View v)
    {
        switch (v.getId())
        {
            /*
             * Обработка события нажатия на кнопку "Ответить на вопрос"
             * ----------------------------------------------------------------------
             */
            case R.id.btnOne :
            {
                // ----- Шаг 1. Создание объекта android.app.AlertDialog.Builder -------
                AlertDialog.Builder builder = new AlertDialog.Builder(this,
                        //		android.R.style.Theme_DeviceDefault_Dialog_Alert
                        //		android.R.style.Theme_Holo_Dialog
                        android.R.style.Theme_Holo_Light_Dialog
                        //		android.R.style.ThemeOverlay_Material_Dialog_Alert
                );

                // ----- Шаг 2. Формирование заголовка окна и его содержимого ----------
                builder.setMessage("2 * 2 = 4?");
                builder.setTitle("Ответьте на вопрос");

                // ----- Шаг 3. Назначение диалоговому окну кнопок ---------------------
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Toast.makeText(MainActivity.this, "Верно!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this, "Не верно!", Toast.LENGTH_SHORT).show();
                    }
                });


                // ----- Шаг 4. Создание объекта диалогового окна android.app.AlertDialog
                AlertDialog dialog = builder.create();

                dialog.setCancelable(false);		// Отмена аннулирования окна (необязательно)

                // ----- Шаг 5. Показываем диалог --------------------------------------
                dialog.show();
            }
            break;


            /*
             * Обработка события нажатия на кнопку "Задать цвет фона"
             * ----------------------------------------------------------------------
             */
            case R.id.btnTwo :
            {
                // ----- Шаг 1. Создание объекта android.app.AlertDialog.Builder -------
                AlertDialog.Builder builder = new AlertDialog.Builder(this,
                        android.R.style.Theme_Holo_Light_Dialog
                );

                // ----- Шаг 2. Формирование заголовка окна и его содержимого ----------
                builder.setTitle("Выберите цвет фона");

                builder.setSingleChoiceItems(
                        new String[]{"Красный", "Желтый", "Зеленый", "Белый"}, -1,
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                switch (which)
                                {
                                    // ----- Выбран красный цвет -------------------------------------------
                                    case 0:
                                        MainActivity.this.clrTmpMain = Color.rgb(0xFF, 0x00, 0x00);
                                        break;

                                    // ----- Выбран желтый цвет --------------------------------------------
                                    case 1:
                                        MainActivity.this.clrTmpMain = Color.rgb(0xFF, 0xFF, 0x00);
                                        break;

                                    // ----- Выбран зеленый цвет -------------------------------------------
                                    case 2:
                                        MainActivity.this.clrTmpMain = Color.rgb(0x00, 0xFF, 0x00);
                                        break;

                                    // ----- Выбран белый цвет --------------------------------------------
                                    case 3:
                                        MainActivity.this.clrTmpMain = Color.rgb(0xFF, 0xFF, 0xFF);
                                        break;
                                }
                                MainActivity.this.llMain.setBackgroundColor(MainActivity.this.clrTmpMain);
                            }
                        });

                // ----- Шаг 3. Назначение диалоговому окну кнопок ---------------------
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // ----- Пользователь подтверждает смену цвета -------------------------
                        MainActivity.this.clrForMain	= MainActivity.this.clrTmpMain;
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // ----- Пользователь отменяет решение - возвращаем предыдущий цвет фона
                        MainActivity.this.llMain.setBackgroundColor(MainActivity.this.clrForMain);
                    }
                });

                // ----- Шаг 4. Создание объекта диалогового окна android.app.AlertDialog
                AlertDialog dialog = builder.create();

                // ----- Шаг 5. Показываем диалог --------------------------------------
                dialog.show();
            }
            break;

            /*
             * Обработка события нажатия на кнопку "Стиль текста"
             * ----------------------------------------------------------------------
             */
            case R.id.btnThree :
            {
                // ----- Шаг 1. Создание объекта android.app.AlertDialog.Builder -------
                AlertDialog.Builder builder = new AlertDialog.Builder(this,
                        android.R.style.Theme_Holo_Light_Dialog
                );

                // ----- Шаг 2. Формирование заголовка окна и его содержимого ----------
                builder.setTitle("Отметьте требуемые стили");

                for (int i = 0; i < this.arrStyle.length; i++) this.arrTmpStyle[i] = this.arrStyle[i];
                builder.setMultiChoiceItems(
                        new String[] {"BOLD", "ITALIC", "ALL CAPS"},
                        this.arrTmpStyle,
                        new DialogInterface.OnMultiChoiceClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked)
                            {
                                switch (which)
                                {
                                    // ----- Выбран элемент списка "BOLD" ----------------------------------
                                    case MainActivity.TEXT_STYLE_INDEX_BOLD :
                                        if (isChecked)
                                        {
                                            MainActivity.this.tvText.setTypeface(Typeface.DEFAULT,
                                                    (arrTmpStyle[MainActivity.TEXT_STYLE_INDEX_ITALIC])?
                                                            Typeface.BOLD_ITALIC:Typeface.BOLD);
                                        }
                                        else
                                        {
                                            MainActivity.this.tvText.setTypeface(Typeface.DEFAULT,
                                                    (arrTmpStyle[MainActivity.TEXT_STYLE_INDEX_ITALIC])?
                                                            Typeface.ITALIC:Typeface.NORMAL);
                                        }
                                        break;

                                    // ----- Выбран элемент списка "ITALIC" --------------------------------
                                    case MainActivity.TEXT_STYLE_INDEX_ITALIC :
                                        if (isChecked)
                                        {
                                            MainActivity.this.tvText.setTypeface(Typeface.DEFAULT,
                                                    (arrTmpStyle[MainActivity.TEXT_STYLE_INDEX_BOLD])?
                                                            Typeface.BOLD_ITALIC:Typeface.ITALIC);
                                        }
                                        else
                                        {
                                            MainActivity.this.tvText.setTypeface(Typeface.DEFAULT,
                                                    (arrTmpStyle[MainActivity.TEXT_STYLE_INDEX_BOLD])?
                                                            Typeface.BOLD:Typeface.NORMAL);
                                        }
                                        break;

                                    // ----- Выбран элемент списка "ALL CAPS" ------------------------------
                                    case MainActivity.TEXT_STYLE_INDEX_ALLCAPS :
                                        MainActivity.this.tvText.setAllCaps(isChecked);
                                        break;
                                }
                            }
                        });

                // ----- Шаг 3. Назначение диалоговому окну кнопок ---------------------
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // ----- Пользователь подтвердил смену стилей - запомним новые значения
                        for (int i = 0; i < arrStyle.length; i++) arrStyle[i] = arrTmpStyle[i];
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // ----- Пользователь отменил смену стилей - восстановим предыдущие значения
                        MainActivity.this.tvText.setAllCaps(arrStyle[MainActivity.TEXT_STYLE_INDEX_ALLCAPS]);

                        if (arrStyle[MainActivity.TEXT_STYLE_INDEX_BOLD] &&
                                arrStyle[MainActivity.TEXT_STYLE_INDEX_ITALIC])
                        {
                            MainActivity.this.tvText.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC);
                        }
                        else
                        if (arrStyle[MainActivity.TEXT_STYLE_INDEX_BOLD] &&
                                !arrStyle[MainActivity.TEXT_STYLE_INDEX_ITALIC])
                        {
                            MainActivity.this.tvText.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                        }
                        else
                        if (!arrStyle[MainActivity.TEXT_STYLE_INDEX_BOLD] &&
                                arrStyle[MainActivity.TEXT_STYLE_INDEX_ITALIC])
                        {
                            MainActivity.this.tvText.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
                        }
                        else
                        {
                            MainActivity.this.tvText.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                        }

                    }
                });

                // ----- Шаг 4. Создание объекта диалогового окна android.app.AlertDialog
                AlertDialog dialog = builder.create();

                // ----- Шаг 5. Показываем диалог --------------------------------------
                dialog.show();
            }
            break;

            /*
             * Обработка события нажатия на кнопку "Приветствие"
             * ----------------------------------------------------------------------
             */
            case R.id.btnFour :
            {
                // ----- Шаг 1. Создание объекта android.app.AlertDialog.Builder -------
                AlertDialog.Builder builder = new AlertDialog.Builder(this,
                        android.R.style.Theme_Holo_Light_Dialog
                );

                // ----- Шаг 2. Формирование заголовка окна и его содержимого ----------
                builder.setTitle("Введите данные");

                LayoutInflater inflater	= this.getLayoutInflater();
                final 	View	view		= inflater.inflate(R.layout.dialog_content, null, false);
                builder.setView(view);

                // ----- Шаг 3. Назначение диалоговому окну кнопок ---------------------
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // ----- Чтение содержимого текстовых полей Даилогового окна -----------
                        EditText etLName	= (EditText) view.findViewById(R.id.etLName);
                        EditText	etFName	= (EditText) view.findViewById(R.id.etFName);

                        String greeting= etLName.getText().toString() + " " +
                                etFName.getText().toString();

                        // ----- Вывод приветствия в Тосте -------------------------------------
                        Toast.makeText(MainActivity.this,
                                "Привет, " + greeting + "!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                    }
                });

                // ----- Шаг 4. Создание объекта диалогового окна android.app.AlertDialog
                AlertDialog dialog = builder.create();

                // ----- Шаг 5. Показываем диалог --------------------------------------
                dialog.show();
            }
            break;
        }
    }

    /**
     * Обработчик события клика на кнопку "Установить дату"
     * @param v	- ссылка на объект android.widget.Button который
     *          является источником события.
     */
    public	void	btnDateClick(View v)
    {
        if (this.year == 0 || this.month == 0 || this.day == 0)
        {
            // ----- Получаем текущую дату с помощью java.util.Calendar ------------
            Calendar C	= Calendar.getInstance();

            this.year	= C.get(Calendar.YEAR);
            this.month	= C.get(Calendar.MONTH);
            this.day	= C.get(Calendar.DAY_OF_MONTH);
        }

        // ----- 1. Создание объекта android.app.DatePickerDialog --------------
        DatePickerDialog	DPD	= new DatePickerDialog(this, null,
                this.year, this.month, this.day)
        {
            @Override
            public	void	onDateChanged(DatePicker view, int year, int month, int day)
            {
                // ----- Сохраним выбор пользователя -----------------------------------
                MainActivity.this.year		= year;
                MainActivity.this.month		= month;
                MainActivity.this.day		= day;
            }
        };

        // ----- 2. Назначение кнопок "OK", "Cancel" Диалоговому окну ----------
        DPD.setButton(DialogInterface.BUTTON_POSITIVE, "Выбрать",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // ----- Вывод в Тосте выбранной даты ----------------------------------
                        String	txt	= ((MainActivity.this.day < 10)?"0":"")		+
                                MainActivity.this.day			+ "/"	+
                                ((MainActivity.this.month < 9)?"0":"")	+
                                (MainActivity.this.month + 1)	+ "/"	+
                                MainActivity.this.year;

                        Toast.makeText(MainActivity.this,
                                "Выбранная дата дд/мм/гггг : " + txt, Toast.LENGTH_LONG).show();
                    }
                });

        DPD.setButton(DialogInterface.BUTTON_NEGATIVE, "Отменить",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // ----- Пользователь отменил выбор - ничего не делаем -----------------
                    }
                });

        // ----- 3. Показываем Диалоговое окно android.app.DatePickerDialog ----
        DPD.show();
    }

    /**
     * Обработчик события клика на кнопку "Установить время"
     * @param v	- ссылка на объект android.widget.Button который
     *          является источником события.
     */
    public	void	btnTimeClick(View v)
    {
        if (this.hour == -1 || this.minute == -1)
        {
            // ----- Значения не проинициализированы - проинициализируем -----------
            Calendar C	= Calendar.getInstance();
            this.hour		= C.get(Calendar.HOUR_OF_DAY);
            this.minute		= C.get(Calendar.MINUTE);
        }

        // ----- 1. Создание объекта android.app.TimePickerDialog --------------
        TimePickerDialog TPD	= new TimePickerDialog(this, null,
                this.hour, this.minute, true)
        {
            @Override
            public	void	onTimeChanged(TimePicker view, int hour, int minute)
            {
                // ----- Запомним выбранные пользователем значения часов и минут -------
                MainActivity.this.hour		= hour;
                MainActivity.this.minute	= minute;
            }
        };

        // ----- 2. Назначение Диалоговому окну кнопок -------------------------
        TPD.setButton(DialogInterface.BUTTON_POSITIVE, "Выбрать",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String	txt	= ((MainActivity.this.hour < 10)?"0":"")	+
                                MainActivity.this.hour	+ ":"	+
                                ((MainActivity.this.minute < 10)?"0":"") +
                                MainActivity.this.minute;

                        // ----- Вывод в Тосте выбранного времени ------------------------------
                        Toast.makeText(MainActivity.this,
                                "Выбранное время чч:мм : " + txt, Toast.LENGTH_LONG).show();
                    }
                });

        TPD.setButton(DialogInterface.BUTTON_NEGATIVE, "Отменить",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // ----- Пользователь отменил выбор - ничего не делаем -----------------
                    }
                });

        // ----- 3. Показываем Диалоговое окно android.app.TimePickerDialog ----
        TPD.show();
    }
}