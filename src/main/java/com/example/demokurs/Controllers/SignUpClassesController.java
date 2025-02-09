package com.example.demokurs.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demokurs.Entity.Timetable;
import com.example.demokurs.Repository.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SignUpClassesController {

    @FXML
    private Button BackForUserButton;

    @FXML
    private Label Label;

    @FXML
    private Button OkClassesButton;

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
        BackForUserButton.setOnAction(actionEvent -> {
            BackForUserButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/FirstPage.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Меню пользователя");
            stage.setScene(new Scene(root));
            stage.show();
        });
        addColumns();
        OkClassesButton.setOnAction(actionEvent -> {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            Timetable selectedTimetable = Timetable.getSelectionModel().getSelectedItem();
            StringBuilder stringBuilder = null;
            if (selectedTimetable.getType().equals("Групповое занятие") || selectedTimetable.getType().equals("Тренажерный зал")) {
                stringBuilder = new StringBuilder(selectedTimetable.getClient());
                if (!selectedTimetable.getClient().equals("-")) {
                    stringBuilder.append("," + AuthController.myUser.getLastname() + " " + AuthController.myUser.getName());
                } else {
                    stringBuilder = new StringBuilder(AuthController.myUser.getLastname() + " " + AuthController.myUser.getName());
                }
                selectedTimetable.setClient(String.valueOf(stringBuilder));
            }
            else {
                selectedTimetable.setClient(AuthController.myUser.getLastname() + " " + AuthController.myUser.getName());
            }

            System.out.println(selectedTimetable.getDate() + selectedTimetable.getClient());
            databaseHandler.updateTimetable(selectedTimetable);
            Label.setText("Запись прошла успешно!");
        });
    }
    void addColumns(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        List<Timetable> timetables1 = databaseHandler.getTimetable();
        List<Timetable> freeTimetable = new ArrayList<>();
        System.out.println(AuthController.myUser.getLastname() + " " + AuthController.myUser.getName());
        System.out.println();
        for (Timetable timetable:timetables1) {
            System.out.println(timetable.getClient());
             if(timetable.getType().equals("Групповое занятие") || timetable.getType().equals("Тренажерный зал")){
                 if (!checkGroup(timetable.getClient())){
                freeTimetable.add(timetable);
                 }
            }
            else if(timetable.getClient().equals("-")){
                freeTimetable.add(timetable);
            }

        }
        freeTimetable.forEach(x -> System.out.println(x.getSpecialist()));
        timetables = FXCollections.observableArrayList(freeTimetable);
        Timetable.setEditable(true);
        Timetable.setItems(timetables);
        CostColumn.setCellValueFactory(new PropertyValueFactory<Timetable, Double>("cost"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("date"));
        SpecialistColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("specialist"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("time"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("type"));
    }

    boolean checkGroup(String string){
        List<String> stringList = List.of(string.split(","));
        for (String strings: stringList) {
            if(strings.equals(AuthController.myUser.getLastname() + " " + AuthController.myUser.getName())){
                return true;
            }
        }
        return false;
    }
}