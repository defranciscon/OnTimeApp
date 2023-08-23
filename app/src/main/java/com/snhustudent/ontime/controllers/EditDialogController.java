package com.snhustudent.ontime.controllers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.snhustudent.ontime.R;
import com.snhustudent.ontime.model.Event;
import com.snhustudent.ontime.viewmodel.EventListViewModel;

public class EditDialogController {

    private AlertDialog.Builder EDIT_EVENT_DIALOG;

    private Event EVENT;
    private View VIEW;
    private DateTimeController DATE_TIME_CONTROLLER;
    private DatePickerDialog DATE_PICKER_DIALOG;
    private DatePickerDialog.OnDateSetListener ON_DATE_SET_LISTENER;
    private Button DATE_BUTTON;
    private Button TIME_BUTTON;
    private TimePickerDialog TIME_PICKER_DIALOG;
    private TimePickerDialog.OnTimeSetListener TIME_SET_LISTENER;
    private Context CONTEXT;

    private EventListViewModel EVENT_LIST;

    private EditDialogController() {}

    public EditDialogController(AlertDialog.Builder dialogBuilder, View view, Context context, DateTimeController dateTimeController, EventListViewModel viewModel) {

    }

    public static EditDialogController createDialog() {
        return new EditDialogController();
    }

    public void startEditEventDialog(View view, Context context) {

        VIEW = view;
        DATE_TIME_CONTROLLER = DateTimeController.createDateTimeFormatter();
        CONTEXT = context;
        EDIT_EVENT_DIALOG = new AlertDialog.Builder(CONTEXT);

        initDateListener(DATE_TIME_CONTROLLER, VIEW, CONTEXT);
        initTimeListener(DATE_TIME_CONTROLLER, VIEW, CONTEXT);

        EDIT_EVENT_DIALOG.setView(R.layout.dialog_edit_layout);
        EDIT_EVENT_DIALOG.setTitle(R.string.update_event_details);
        EDIT_EVENT_DIALOG.setMessage(R.string.update_event_message);

        EDIT_EVENT_DIALOG.setPositiveButton(R.string.save, (dialog, which) -> {

        });
        EDIT_EVENT_DIALOG.setNegativeButton(R.string.btn_cancel, (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = EDIT_EVENT_DIALOG.create();
        alertDialog.show();
    }


    public void initDateListener(DateTimeController controller, View view, Context context) {
        TextView selectedDate = view.findViewById(R.id.selected_date);

        DATE_BUTTON.setOnClickListener(v -> {
            controller.initDatePicker(DATE_BUTTON, selectedDate, DATE_PICKER_DIALOG, ON_DATE_SET_LISTENER, context);
            controller.openDatePicker(view);
        });
    }

    public void initTimeListener(DateTimeController controller, View view, Context context) {

        TextView selectedTime = view.findViewById(R.id.selected_time);
        TIME_BUTTON.setOnClickListener(v -> {
            controller.initTimePicker(TIME_BUTTON, selectedTime, TIME_PICKER_DIALOG, TIME_SET_LISTENER, context);
            controller.openTimePicker(v);
        });
    }
}
