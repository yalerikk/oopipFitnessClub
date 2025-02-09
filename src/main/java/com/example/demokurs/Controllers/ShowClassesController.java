package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

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
import com.example.demokurs.Repository.DatabaseHandler;
import com.example.demokurs.Entity.Timetable;

public class ShowClassesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label;

    @FXML
    private Button BackForAdminButton;

    @FXML
    private Button DeleteClassButton;

    @FXML
    private Button SortClassesButton;

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
    AtomicBoolean atomicBooleanSort = new AtomicBoolean(false);


    @FXML
    void initialize() {
        BackForAdminButton.setOnAction(actionEvent -> {
            BackForAdminButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/adminFirstPage.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Меню администратора");
            stage.setScene(new Scene(root));
            stage.show();
        });

        DatabaseHandler databaseHandler = new DatabaseHandler();
        timetables = FXCollections.observableArrayList(databaseHandler.getTimetable());
        Timetable.setEditable(true);
        Timetable.setItems(timetables);
        ClientColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("client"));
        CostColumn.setCellValueFactory(new PropertyValueFactory<Timetable, Double>("cost"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("date"));
        SpecialistColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("specialist"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("time"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("type"));

        DeleteClassButton.setOnAction(actionEvent -> {
            Timetable selected = Timetable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DatabaseHandler databaseHandler1 = new DatabaseHandler();
                databaseHandler1.deleteTimetable(selected);
                timetables.remove(selected);
                Label.setText("Успешно удалено!");
            }
            else {
                Label.setStyle("-fx-text-fill: red");
                Label.setText("Возникла ошибка!");
            }
        });

        SortClassesButton.setOnAction(actionEvent -> {
            if(!atomicBooleanSort.get()){
                timetables = FXCollections.observableArrayList(timetables.stream().sorted((x1, x2) -> (int)(x1.getCost() - x2.getCost())).toList());
                Timetable.setItems(timetables);
                Label.setStyle("-fx-text-fill: white");
                Label.setText("Сортировка по стоимости занятия в порядке возрастания");
            atomicBooleanSort.set(true);
            }
            else {
                timetables = FXCollections.observableArrayList(timetables.stream().sorted((x1, x2) -> (int)(x2.getCost() - x1.getCost())).toList());
                Timetable.setItems(timetables);
                Label.setStyle("-fx-text-fill: white");
                Label.setText("Сортировка по стоимости занятия в порядке убывания");
                atomicBooleanSort.set(false);
            }
        });
    }
}