package com.snhustudent.ontime.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int Id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "expanded")
    private boolean expanded;
    public Event() {}

    @Ignore
    public Event(@NonNull String eName, String date, String time) {
        this.name = eName;
        this.date = date;
        this.time = time;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
    public int getId() {
        return this.Id;
    }

    // Accessor methods
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
    public String getDescription() {
        return description;
    }

    // Mutator methods
    public void setId(int Id) {
        this.Id = Id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
