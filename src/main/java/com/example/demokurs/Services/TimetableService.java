package com.example.demokurs.Services;

import com.example.demokurs.Entity.Timetable;
import com.example.demokurs.Repository.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TimetableService {
    private List<Timetable> timetableArrayList;
    public TimetableService(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        timetableArrayList = databaseHandler.getTimetable();
        timetableArrayList.forEach(x -> System.out.println(x.getType()));
    }
    public List<Timetable> getTimetableArrayList() {
        return timetableArrayList;
    }
    public ObservableList<Timetable> GetObservableListTimetable(){
        return FXCollections.observableList(timetableArrayList);
    }


}