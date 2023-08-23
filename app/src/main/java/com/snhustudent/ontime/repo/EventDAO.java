package com.snhustudent.ontime.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.*;
import com.snhustudent.ontime.model.Event;
import java.util.List;

@Dao
public interface EventDAO {
    @Query("SELECT * FROM event_table WHERE id = :id")
    LiveData<Event> getEvent(int id);

    @Query("SELECT * FROM event_table ORDER BY date COLLATE NOCASE")
    LiveData<List<Event>> getAllEvents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addEvent(Event event);

    @Update
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);
}
