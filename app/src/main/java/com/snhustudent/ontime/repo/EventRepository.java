package com.snhustudent.ontime.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.snhustudent.ontime.model.Event;
import java.util.List;

public class EventRepository {
    private final EventDAO eventDao;

    private final LiveData<List<Event>> allEvents;

    public EventRepository(Application application) {
        EventDatabase db = EventDatabase.getDatabase(application);
        eventDao = db.eventDao();
        allEvents = eventDao.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public void addEvent(Event event) {
        EventDatabase.databaseWriteExecutor.execute(() -> eventDao.addEvent(event));
    }

    public LiveData<Event> getEvent(int eventId) {
        return eventDao.getEvent(eventId);
    }

    public void updateEvent(Event event) {
        EventDatabase.databaseWriteExecutor.execute(() -> eventDao.updateEvent(event));
    }

    public void deleteEvent(Event event) {
        EventDatabase.databaseWriteExecutor.execute(() -> eventDao.deleteEvent(event));
    }
}
