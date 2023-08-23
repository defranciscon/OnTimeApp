package com.snhustudent.ontime.views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.snhustudent.ontime.adapters.EventListAdapter;
import com.snhustudent.ontime.R;
import com.snhustudent.ontime.controllers.DateTimeController;
import com.snhustudent.ontime.interfaceListeners.OnItemClickListener;
import com.snhustudent.ontime.model.Event;
import com.snhustudent.ontime.viewmodel.EventListViewModel;

import java.util.ArrayList;
import java.util.List;

public class EventListFragment extends Fragment implements OnItemClickListener {

    public EventListFragment() {}
    EventListAdapter eventListAdapter;
    EventListViewModel eventViewModel;
    NavController navController;
    RecyclerView recyclerView;
    List<Event> eventList = new ArrayList<>();
    EditText nameView, locationView, descriptionView;
    Button dateButton, timeButton, deleteButton;
    LinearLayout expandLayout;
    AlertDialog.Builder eventDialog;
    TextView selectedDateTV, selectedTimeTV;
    DateTimeController dateTimeController;
    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener timeSetListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)  {
        super.onViewCreated(view, savedInstanceState);
        Log.d("EventListFragment", "EventListFragment onViewCreated");

        navController = Navigation.findNavController(view);
        expandLayout = view.findViewById(R.id.expand_child_vertical_layout);
        deleteButton = view.findViewById(R.id.button_delete);

        recyclerView = view.findViewById(R.id.recyclerView);

        eventListAdapter = new EventListAdapter(getContext(), eventList, this);

        recyclerView.setAdapter(eventListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),
                DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        eventViewModel = new ViewModelProvider(this).get(EventListViewModel.class);

        eventViewModel.getAllEvents()
                .observe(getViewLifecycleOwner(), events -> {
                    eventList.clear();
                    eventList.addAll(events);
                    eventListAdapter.notifyDataSetChanged();
                });
        deleteButton.setOnClickListener(view1 -> eventViewModel.getEvent().observe(getViewLifecycleOwner(), event -> {
            eventList.remove(event);
            eventViewModel.removeEvent(event);
            eventListAdapter.notifyDataSetChanged();
        }));

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> navController.navigate(R.id.action_global_addEvent));
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Click and hold to edit the event." , Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemLongClick(int position) {
        Event selectedEvent = eventListAdapter.getEventAtPosition(position);
        Toast.makeText(getContext(), "In Event Details Editing Dialog", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_edit_layout, null);
        eventViewModel = new ViewModelProvider(this).get(EventListViewModel.class);

        eventDialog = new AlertDialog.Builder(getContext());
        eventDialog.setView(view);
        eventDialog.setTitle(R.string.update_event_details);
        eventDialog.setMessage(R.string.update_event_message);

        nameView = view.requireViewById(R.id.edit_event_name);
        dateButton = view.findViewById(R.id.edit_event_date);
        selectedDateTV = view.findViewById(R.id.inv_date);
        selectedTimeTV = view.findViewById(R.id.inv_time);
        timeButton = view.findViewById(R.id.edit_event_time);
        locationView = view.findViewById(R.id.edit_event_location);
        descriptionView = view.findViewById(R.id.edit_event_description);

        nameView.setText(selectedEvent.getName());
        dateButton.setText(selectedEvent.getDate());
        selectedDateTV.setText(selectedEvent.getDate());
        selectedTimeTV.setText(selectedEvent.getTime());
        timeButton.setText(selectedEvent.getTime());
        locationView.setText(selectedEvent.getLocation());
        descriptionView.setText(selectedEvent.getDescription());

        dateButton.setOnClickListener(v -> {
            dateTimeController = DateTimeController.createDateTimeFormatter();
            dateTimeController.initDatePicker(dateButton, selectedDateTV, datePickerDialog, onDateSetListener, requireContext());
            dateTimeController.openDatePicker(v);
        });

        timeButton.setOnClickListener(v -> {
            dateTimeController = DateTimeController.createDateTimeFormatter();
            dateTimeController.initTimePicker(timeButton, selectedTimeTV, timePickerDialog, timeSetListener, requireContext());
            dateTimeController.openTimePicker(requireView());
        });

        eventDialog.setPositiveButton(R.string.save, (dialog, which) -> {

            String name = nameView.getText().toString();
            String eventDate = dateButton.getText().toString();
            String eventTime = timeButton.getText().toString();
            String eventLocation = locationView.getText().toString();
            String eventDescription = descriptionView.getText().toString();
            selectedEvent.setName(name);
            selectedEvent.setDate(eventDate);
            selectedEvent.setTime(eventTime);
            selectedEvent.setLocation(eventLocation);
            selectedEvent.setDescription(eventDescription);
            eventViewModel.updateEvent(selectedEvent);

            eventViewModel.getEvent().observe(getViewLifecycleOwner(), this::updateUI);

        });
        eventDialog.setNegativeButton(R.string.btn_cancel, (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = eventDialog.create();
        alertDialog.show();
    }
    private void updateUI(Event event) {
        nameView.setText(event.getName());
        dateButton.setText(event.getDate());
        selectedDateTV.setText(event.getDate());
        timeButton.setText(event.getTime());
        selectedTimeTV.setText(event.getTime());
        locationView.setText(event.getLocation());
        descriptionView.setText(event.getDescription());
    }
}