package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demokurs.Entity.Price;
import com.example.demokurs.Entity.Specialist;
import com.example.demokurs.Entity.Timetable;
import com.example.demokurs.Entity.User;
import com.example.demokurs.Repository.DatabaseHandler;
import com.example.demokurs.Services.PriceService;
import com.example.demokurs.Services.SpecialistService;
import com.example.demokurs.Services.TimetableService;
import com.example.demokurs.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class EditClassesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackForAdminButton;

    @FXML
    private Label Label1;

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
    private TableView<Timetable> timeTableView = new TableView<>();

    ObservableList<Timetable> timetables = FXCollections.observableArrayList();

    @FXML
    private Button hintButton;

    @FXML
    private Button updateButton;

    PriceService priceService = new PriceService();

    @FXML
    void initialize() {
        List<Price> priceList = priceService.getPriceArrayList();
        List<String> stringListType = new ArrayList<>();
        List<String> stringListCost = new ArrayList<>();
        List<String> stringListSpec = new ArrayList<>(List.of("-"));
        List<String> stringListClient = new ArrayList<>(List.of("-"));
        UserService userService = new UserService();
        List<User> userList = userService.getUserArrayList();
        SpecialistService specialistService = new SpecialistService();
        List<Specialist> specialists = specialistService.getSpecialistArrayList();

        for (Price price : priceList) {
            if(!price.getType().equals("Групповое занятие")) {
                stringListType.add(price.getType());
            }
        }
        for (Price price : priceList) {
            stringListCost.add(String.valueOf(price.getCost()));
        }
        for (Specialist specialist : specialists) {
            stringListSpec.add(specialist.getLastname() + " " + specialist.getName());
        }
        for (User user: userList) {
            if(user.getRole() == 0){
           stringListClient.add(user.getLastname() + " " + user.getName());}
        }

        TimetableService timetableService = new TimetableService();
        for (Timetable t:timetableService.getTimetableArrayList()) {
            System.out.println(t.getType());
        }
        timetables = FXCollections.observableArrayList(timetableService.getTimetableArrayList());
        timetables.forEach(x-> System.out.println(x.getType()));
        timeTableView.setEditable(true);
        timeTableView.setItems(timetables);

        ClientColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("client"));
        CostColumn.setCellValueFactory(new PropertyValueFactory<Timetable, Double>("cost"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("date"));
        SpecialistColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("specialist"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("time"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Timetable, String>("type"));
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

        updateButton.setOnAction(actionEvent -> {
            ObservableList<Timetable> timetableList = timeTableView.getItems();
            if(test(timetableList)){
                DatabaseHandler databaseHandler = new DatabaseHandler();
                timetableList.forEach(x-> System.out.println(x.getTime()));
                databaseHandler.updateAllTimetable(timetableList);
                System.out.println("Успех");
                Label1.setText("Успешно отредактировано!");
            }
        });
        hintButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/hint.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Подсказка");
            stage.setScene(new Scene(root));
            stage.show();
        });

        SpecialistColumn.setCellFactory(column -> new TableCell<Timetable, String>() {
            private final ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(stringListSpec));
            {
                comboBox.setOnAction(event -> {
                    Timetable type = getTableView().getItems().get(getIndex());
                    type.setSpecialist(comboBox.getValue());
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    comboBox.setValue(item);
                    setGraphic(comboBox);
                }
            }
        });

        ClientColumn.setCellFactory(column -> new TableCell<Timetable, String>() {
            private final ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(stringListClient));
            {
                comboBox.setOnAction(event -> {
                    Timetable type = getTableView().getItems().get(getIndex());
                    type.setClient(comboBox.getValue());
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    comboBox.setValue(item);
                    setGraphic(comboBox);
                }
            }
        });

        DateColumn.setCellFactory(col -> {
                    TableCell<Timetable, String> cell = new TableCell<Timetable, String>() {
                        private final DatePicker datePicker = new DatePicker();

                        {
                            datePicker.setOnAction(event -> {
                                // При выборе даты обновите значение в модели данных
                                Timetable timetable= getTableView().getItems().get(getIndex());
                                timetable.setDate(datePicker.getValue().toString());
                            });
                        }

                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty) {
                                setGraphic(null);
                            } else {
                                Timetable timetable = getTableView().getItems().get(getIndex());
                                datePicker.setValue(LocalDate.parse(timetable.getDate()));
                                setGraphic(datePicker);
                            }
                        }
                    };

                    return cell;
                });

        TypeColumn.setCellFactory(column -> new TableCell<Timetable, String>() {
            private final ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(stringListType));
            {
                comboBox.setOnAction(event -> {
                    Timetable type = getTableView().getItems().get(getIndex());
                    type.setType(comboBox.getValue());
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    comboBox.setValue(item);
                    setGraphic(comboBox);
                }
            }
        });

        TimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        TimeColumn.setOnEditCommit(event -> {
             Timetable timetable = event.getTableView().getItems().get(event.getTablePosition().getRow());
            timetable.setTime(event.getNewValue());
        });

        CostColumn.setCellFactory(column -> new TableCell<Timetable,Double>() {
            private final ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(stringListCost));
            {
                comboBox.setOnAction(event -> {
                    Timetable type = getTableView().getItems().get(getIndex());
                    type.setCost(Double.parseDouble(comboBox.getValue()));
                });
            }

            @Override
            protected void updateItem(Double item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                } else {
                    comboBox.setValue(String.valueOf(item));
                    setGraphic(comboBox);
                }
            }
        });
    }

    boolean test(ObservableList<Timetable> timetableList) {
        for (Timetable timetable: timetableList) {
            if(!patternTime(timetable.getTime())){
                return false;
            }
            if (!testTimeDate(timetable)){
                return false;
            }
            if (checkLocalDate(timetable)){
                return false;
            }
            if (!checkPrice(timetable)){
                return false;
            }
        }
        return true;
    }

    boolean patternTime(String time){
        System.out.println(time);
        String s = "^[0-9]{2}:[0-9]{2}$";
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(time);
        System.out.println(matcher.matches() + "0ошибка во времени");
        return matcher.matches();
    }

    boolean testTimeDate(Timetable timetable){
        List<String> stringsTime = List.of(timetable.getTime().split(":"));
        List<Integer> integerTime = stringsTime.stream().map(Integer::parseInt).toList();

        if(checkWeekend(getLocalDate(timetable))){
            if(integerTime.get(0) < 9 || integerTime.get(0) > 21){
                System.out.println("1ошибка во времени");

                return false;
            }
        }
        else {
            if(integerTime.get(0) < 7 || integerTime.get(0) > 23){
                System.out.println("2ошибка во времени");

                return false;
            }
        }
        if(integerTime.get(1) < 0 || integerTime.get(1) > 59){
            System.out.println("3ошибка во времени");

            return false;
        }
        return true;
    }

    boolean checkPrice(Timetable timetable){
        for (Price p: priceService.getPriceArrayList()) {
            if(timetable.getType().equals(p.getType())){
                if(timetable.getCost() == p.getCost()){
                List<String> stringList = List.of(timetable.getSpecialist());
                    for (String s: stringList) {
                        if(s.equals(timetable.getSpecialist())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean checkWeekend(LocalDateTime date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
            return true;
        }
        return  false;
    }

    public boolean checkLocalDate(Timetable timetable){
        LocalDateTime localDateTime = LocalDateTime.now();
            List<String> stringsTime = List.of(timetable.getTime().split(":"));
            List<String> stringsDate = List.of(timetable.getDate().split("-"));
            List<Integer> integerTime = stringsTime.stream().map(Integer::parseInt).toList();
            List<Integer> integerDate = stringsDate.stream().map(Integer::parseInt).toList();
            LocalDateTime dateTimeSchedule = LocalDateTime.of(integerDate.get(0),integerDate.get(1), integerDate.get(2),integerTime.get(0), integerTime.get(1));
        System.out.println(dateTimeSchedule.isBefore(localDateTime)+"Локальное время");
        return dateTimeSchedule.isBefore(localDateTime);

    }

    public LocalDateTime getLocalDate(Timetable timetable){
        List<String> stringsTime = List.of(timetable.getTime().split(":"));
        List<String> stringsDate = List.of(timetable.getDate().split("-"));
        List<Integer> integerTime = stringsTime.stream().map(Integer::parseInt).toList();
        List<Integer> integerDate = stringsDate.stream().map(Integer::parseInt).toList();
        LocalDateTime dateTime = LocalDateTime.of(integerDate.get(0),integerDate.get(1), integerDate.get(2),integerTime.get(0), integerTime.get(1));

        return dateTime;
    }
}