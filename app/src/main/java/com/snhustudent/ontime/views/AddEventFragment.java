package com.snhustudent.ontime.views;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.snhustudent.ontime.R;

import com.snhustudent.ontime.controllers.DateTimeController;
import com.snhustudent.ontime.model.Event;
import com.snhustudent.ontime.viewmodel.EventListViewModel;

import java.util.ArrayList;
import java.util.List;


public class AddEventFragment extends Fragment {

    EditText editNameView;
    EditText editLocationView;
    EditText editDescriptionView;
    TextView selectedDateTV;
    TextView selectedTimeTV;
    Button dateButton, timeButton;
    Button SaveButton;
    DateTimeController dateTimeController;
    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    EventListViewModel eventListViewModel;
    NavController navController;
    List<Event> eventList = new ArrayList<>();

    public AddEventFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        eventListViewModel = new ViewModelProvider(this).get(EventListViewModel.class);

        dateTimeController = DateTimeController.createDateTimeFormatter();

        editNameView = view.findViewById(R.id.add_name);
        selectedDateTV = view.findViewById(R.id.selected_date);
        dateButton = view.findViewById(R.id.add_date);
        dateButton.setText(dateTimeController.getTodaysDate());
        selectedTimeTV = view.findViewById(R.id.selected_time);
        timeButton = view.findViewById(R.id.add_time);
        editLocationView = view.findViewById(R.id.add_location);
        editDescriptionView = view.findViewById(R.id.add_description);

        dateButton.setOnClickListener(view13 -> {
            dateTimeController.initDatePicker(dateButton, selectedDateTV, datePickerDialog, onDateSetListener, getContext());
            dateTimeController.openDatePicker(view13);
        });

        timeButton.setOnClickListener(view12 -> {
            dateTimeController.initTimePicker(timeButton, selectedTimeTV, timePickerDialog, timeSetListener, getContext());
            dateTimeController.openTimePicker(view12);
        });

        SaveButton = view.findViewById(R.id.add_button_save);
        SaveButton.setOnClickListener(view1 -> {

            String eventName = editNameView.getText().toString();
            String eventDate = selectedDateTV.getText().toString();
            String eventTime = selectedTimeTV.getText().toString();
            String eventLocation = editLocationView.getText().toString();
            String eventDescription = editDescriptionView.getText().toString();

            addNewEvent(eventName, eventDate, eventTime, eventLocation, eventDescription);
            Toast.makeText(getContext(), "Event Saved", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.eventList);
        });

        eventListViewModel.getAllEvents().observe(getViewLifecycleOwner(), events -> {
            eventList.clear();
            eventList.addAll(events);
        });
    }
    private void addNewEvent(String eventName, String eventDate, String eventTime, String eventLocation, String eventDescription) {
        Event event = new Event();
        event.setName(eventName);
        event.setDate(eventDate);
        event.setTime(eventTime);
        event.setLocation(eventLocation);
        event.setDescription(eventDescription);
        eventListViewModel.addEvent(event);
    }
}