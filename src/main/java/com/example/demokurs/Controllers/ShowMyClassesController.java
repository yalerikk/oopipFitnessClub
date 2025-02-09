package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

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

public class ShowMyClassesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackForUserButton;

    @FXML
    private Button DeleteMyClassButton;

    @FXML
    private Label Label;

    @FXML
    private Button SortMyClassesButton;

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
    AtomicBoolean atomicBooleanSort = new AtomicBoolean(false);

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

        DeleteMyClassButton.setOnAction(actionEvent -> {
            Timetable selected = Timetable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DatabaseHandler databaseHandler1 = new DatabaseHandler();
                String str = null;
                if(selected.getType().equals("Групповое занятие") || selected.getType().equals("Тренажерный зал")) {
                    List<String> stringList = new ArrayList<>(Arrays.asList(selected.getClient().split(",")));

                    Iterator<String> iterator = stringList.iterator();
                    while (iterator.hasNext()){
                        String stringNext = iterator.next();
                        if(stringNext.equals(AuthController.myUser.getLastname() + " " + AuthController.myUser.getName())){
                            iterator.remove();
                        }
                    }

                    stringList.forEach(System.out::println);
                    if (stringList.size() == 0) {
                        str = "-";
                    } else {
                        String[] strings = stringList.toArray(new String[0]);
                        str = String.join(",", strings);
                    }
                    selected.setClient(str);
                }
                else {selected.setClient("-");}
                System.out.println(str);
                databaseHandler1.updateTimetable(selected);
                timetables.remove(selected);
                Label.setText("Успешно удалено!");
            }
            else {
                Label.setText("Возникла ошибка!");
            }
        });

        SortMyClassesButton.setOnAction(actionEvent -> {
            if(!atomicBooleanSort.get()){
            timetables = FXCollections.observableArrayList(timetables.stream().sorted((x1, x2) -> (int)(x1.getCost() - x2.getCost())).toList());
            Timetable.setItems(timetables);
            Label.setStyle("-fx-text-color: white");
            Label.setText("Сортировка по стоимости занятия в порядке возрастания");
                atomicBooleanSort.set(true);}
            else {
                timetables = FXCollections.observableArrayList(timetables.stream().sorted((x1, x2) -> (int)(x2.getCost() - x1.getCost())).toList());
                Timetable.setItems(timetables);
                Label.setText("Сортировка по стоимости занятия в порядке убывания");
                atomicBooleanSort.set(false);
            }
        });
    }

    void addColumns(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        List<Timetable> timetables1 = databaseHandler.getTimetable();
        List<Timetable> freeTimetable = new ArrayList<>();
        for (Timetable timetable:timetables1) {
            if(timetable.getClient().equals(AuthController.myUser.getLastname() + " " + AuthController.myUser.getName())){
                freeTimetable.add(timetable);
            }
            else if(checkGroup(timetable.getClient())){
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