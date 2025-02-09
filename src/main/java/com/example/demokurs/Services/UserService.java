package com.example.demokurs.Services;

import com.example.demokurs.Entity.User;
import com.example.demokurs.Repository.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> userArrayList;
    DatabaseHandler databaseHandler = new DatabaseHandler();
    public UserService(){
        userArrayList = databaseHandler.getUsers();
    }
    public List<User> getUserArrayList() {
        return userArrayList;
    }
    public ObservableList<User> GetObservableListUser(){
        return FXCollections.observableList(userArrayList);
    }
    public List<String> getUsersFiList() {
        List<String> getUsersArrayList = new ArrayList<>();
        for (User u: userArrayList) {
            if(u.getRole().equals(0)){
            getUsersArrayList.add(u.getLastname() + " " + u.getName());
            }
        }
        return getUsersArrayList;
    }
}
