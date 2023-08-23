package com.snhustudent.ontime.viewmodel;

import android.app.Application;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.snhustudent.ontime.model.Event;
import com.snhustudent.ontime.repo.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventListViewModel extends AndroidViewModel {

    private static final String TAG = "EventListViewModel";
    private final EventRepository eventRepo;
    private final LiveData<List<Event>> allEvents;
    private MutableLiveData<Event> selectedEvent = new MutableLiveData<>();
    public EventListViewModel(Application application) {
        super(application);
        eventRepo = new EventRepository(application);
        allEvents = eventRepo.getAllEvents();
    }

    public LiveData<Event> getEvent() {
        if (selectedEvent == null) {
            selectedEvent = new MutableLiveData<>();
        }
        return selectedEvent;
    }
    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public void setEvent(int position) {
        Event event = new Event();
        event = allEvents.getValue().get(position);
        selectedEvent.setValue(event);
    }

    public void addEvent(Event event) {
        eventRepo.addEvent(event);
    }

    public void updateEvent(Event event) {
        eventRepo.updateEvent(event);
    }

    public void removeEvent(Event event) {
        eventRepo.deleteEvent(event);
    }
}

