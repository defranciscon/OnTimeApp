package com.snhustudent.ontime.controllers;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Locale;

public class DateTimeController {
    
    //Global Variables

    private Button DATE_BUTTON;
    private Button TIME_BUTTON;
    
    private TextView SELECTED_DATE_TV;
    private TextView SELECTED_TIME_TV;

    private DatePickerDialog DATE_PICKER_DIALOG;
    
    private TimePickerDialog TIME_PICKER_DIALOG;

    private DatePickerDialog.OnDateSetListener DATE_SET_LISTENER;

    TimePickerDialog.OnTimeSetListener TIME_SET_LISTENER;
    
    private Context CONTEXT;
    private int HOUR, MIN, YEAR, MONTH, DAY;
    private DateTimeController() {}
    
    private DateTimeController(Button dateButton, TextView selectedDate, DatePickerDialog datePickerDialog, DatePickerDialog.OnDateSetListener onDateSetListener, Context context) {
        this.DATE_BUTTON = dateButton;
        this.SELECTED_DATE_TV = selectedDate;
        this.DATE_PICKER_DIALOG = datePickerDialog;
        this.DATE_SET_LISTENER = onDateSetListener;
        this.CONTEXT = context;
    }
    
    public DateTimeController(Button timeButton, TextView selectedTime, TimePickerDialog timePickerDialog, TimePickerDialog.OnTimeSetListener timeSetListener, Context context) {
        this.TIME_BUTTON = timeButton;
        this.SELECTED_TIME_TV = selectedTime;
        this.TIME_PICKER_DIALOG = timePickerDialog;
        this.TIME_SET_LISTENER = timeSetListener;
        this.CONTEXT = context;
    }

    public static DateTimeController createDateTimeFormatter() {
        return new DateTimeController();
    }

    public void initDatePicker(Button dateButton, TextView selectedDate, DatePickerDialog datePickerDialog, DatePickerDialog.OnDateSetListener onDateSetListener, Context context) {

        DATE_SET_LISTENER = onDateSetListener;

        DATE_SET_LISTENER = getOnDateSetListener(dateButton, selectedDate);
        Calendar c = Calendar.getInstance();
        YEAR = c.get(Calendar.YEAR);
        MONTH = c.get(Calendar.MONTH);
        DAY = c.get(Calendar.DAY_OF_MONTH);

        DATE_PICKER_DIALOG = datePickerDialog;

        CONTEXT = context;

        DATE_PICKER_DIALOG = new DatePickerDialog(CONTEXT, DATE_SET_LISTENER, YEAR, MONTH, DAY);
        DATE_PICKER_DIALOG.getDatePicker().setMinDate(System.currentTimeMillis());
        DATE_PICKER_DIALOG.show();
    }

    @NonNull
    private DatePickerDialog.OnDateSetListener getOnDateSetListener(Button dateButton, TextView selectedDate) {
        DATE_BUTTON = dateButton;
        SELECTED_DATE_TV = selectedDate;
        return (datePicker, year, month, day) -> {
            String date = makeDateString(day, month + 1, year);
            DATE_BUTTON.setText(date);
            SELECTED_DATE_TV.setText(date);
        };
    }

    public void openDatePicker(View view) {
        DATE_PICKER_DIALOG.show();
    }

    public void initTimePicker(Button timeButton, TextView selectedTime, TimePickerDialog timePickerDialog, TimePickerDialog.OnTimeSetListener timeSetListener,  Context context) {

        TIME_SET_LISTENER = timeSetListener;

        TIME_SET_LISTENER = getOnTimeSetListener(timeButton, selectedTime);

        TIME_PICKER_DIALOG = timePickerDialog;

        CONTEXT = context;

        TIME_PICKER_DIALOG = new TimePickerDialog(CONTEXT, TIME_SET_LISTENER, HOUR, MIN, false);

        TIME_PICKER_DIALOG.setTitle("Select Time");
    }

    @NonNull
    private TimePickerDialog.OnTimeSetListener getOnTimeSetListener(Button timeButton, TextView selectedTime) {

        TIME_BUTTON = timeButton;
        SELECTED_TIME_TV = selectedTime;

        return (timePicker, selectedHour, selectedMinute) -> {
            HOUR = selectedHour;
            MIN = selectedMinute;
            TIME_BUTTON.setText(String.format(Locale.getDefault(), "%02d:%02d", HOUR, MIN));
            SELECTED_TIME_TV.setText(String.format(Locale.getDefault(), "%02d:%02d", HOUR, MIN));
        };
    }
    public void openTimePicker(View view) {
        TIME_PICKER_DIALOG.show();
    }

    public String getTodaysDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month + 1, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String getMonthFormat(int month) {
        if (month == 1) {
            return "Jan";
        }
        if (month == 2) {
            return "Feb";
        }
        if (month == 3) {
            return "Mar";
        }
        if (month == 4) {
            return "Apr";
        }
        if (month == 5) {
            return "May";
        }
        if (month == 6) {
            return "Jun";
        }
        if (month == 7) {
            return "Jun";
        }
        if (month == 8) {
            return "Aug";
        }
        if (month == 9) {
            return "Sep";
        }
        if (month == 10) {
            return "Oct";
        }
        if (month == 11) {
            return "Nov";
        }
        if (month == 12) {
            return "Dec";
        }
        return "MON";
    }
}
