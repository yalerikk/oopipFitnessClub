package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demokurs.Entity.Timetable;
import com.example.demokurs.Repository.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShowGroupClassesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ClassesButton;

    @FXML
    private TableColumn<Timetable, String> ClientColumn = new TableColumn<>("client");

    @FXML
    private TableColumn<Timetable, Double> CostColumn = new TableColumn<>("cost");

    @FXML
    private TableColumn<Timetable, String> DateColumn = new TableColumn<>("date");

    @FXML
    private TableColumn<Timetable, String> SpecialistColumn = new TableColumn<>("specialist");

    @FXML
    private TableColumn<Timetable, String> TimeColumn = new TableColumn<>("time");

    @FXML
    private TableColumn<Timetable, String> TypeColumn = new TableColumn<>("type");

    @FXML
    private TableView<Timetable> Timetable = new TableView<>();

    ObservableList<Timetable> timetables = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        ClassesButton.setOnAction(actionEvent -> {
            ClassesButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/classes.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Занятия");
            stage.setScene(new Scene(root));
            stage.show();
        });

        addColumns();
    }

    void addColumns(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        List<Timetable> timetables1 = databaseHandler.getTimetable();
        List<Timetable> groupShowTimetable = timetables1.stream().filter(x-> x.getType().equals("Групповое занятие")).toList();
        groupShowTimetable.forEach(x -> System.out.println(x.getSpecialist()));
        timetables = FXCollections.observableArrayList(groupShowTimetable);
        Timetable.setEditable(true);
        Timetable.setItems(timetables);
        ClientColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("client"));
        CostColumn.setCellValueFactory(new PropertyValueFactory<Timetable, Double>("cost"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("date"));
        SpecialistColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("specialist"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("time"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("type"));

        ClientColumn.setCellFactory(column -> new TableCell<Timetable, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    setWrapText(true); // Включаем перенос текста
                    // Устанавливаем желаемую высоту строки
                    setPrefHeight(100); // Задайте нужное значение высоты
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
    }
}