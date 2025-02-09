package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import com.example.demokurs.Entity.Price;
import com.example.demokurs.Entity.Timetable;
import com.example.demokurs.Entity.User;
import com.example.demokurs.Repository.DatabaseHandler;
import com.example.demokurs.Services.PriceService;
import com.example.demokurs.Services.SpecialistService;
import com.example.demokurs.Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddClassesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddButton;

    @FXML
    private Button BackForAdminButton;

    @FXML
    private TextField CostField;

    @FXML
    private Label Label;

    @FXML
    private Spinner<Integer> HourSpinner = new Spinner<>();

    @FXML
    private Spinner<Integer> MinuteSpinner = new Spinner<>();

    @FXML
    private Button OkDateButton;

    @FXML
    private Button OkTimeButton;

    @FXML
    private DatePicker SelectDate;

    @FXML
    private TextField SpecialistField;

    @FXML
    private TextField TypeOfServiceField;

    static String date = null;
    static  String time = null;
    int hour; int minute;

    @FXML
    void initialize() {
        setContextMenu();

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

        OkDateButton.setOnAction(actionEvent -> {
            String s = SelectDate.getValue().toString();
            LocalDate day = SelectDate.getValue();
            if(!s.isEmpty()){
            date = s;
            }
        });

        SelectDate.valueProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null){
                if(checkWeekend(SelectDate.getValue())){
                    SpinnerValueFactory<Integer> hourValueFactory
                            = new SpinnerValueFactory.IntegerSpinnerValueFactory(9,21,9);
                    hourValueFactory.setValue(0);
                    HourSpinner.setValueFactory(hourValueFactory);
                }
                else {
                    SpinnerValueFactory<Integer> hourValueFactory
                            = new SpinnerValueFactory.IntegerSpinnerValueFactory(7,23,7);
                    hourValueFactory.setValue(0);
                    HourSpinner.setValueFactory(hourValueFactory);}
                SpinnerValueFactory<Integer> minuteValueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0);
                minuteValueFactory.setValue(0);
                MinuteSpinner.setValueFactory(minuteValueFactory);
            }

        });

            SpinnerValueFactory<Integer> hourValueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(7,23,7);
        hourValueFactory.setValue(0);
        HourSpinner.setValueFactory(hourValueFactory);
        SpinnerValueFactory<Integer> minuteValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0);
        minuteValueFactory.setValue(0);
        MinuteSpinner.setValueFactory(minuteValueFactory);

        OkTimeButton.setOnAction(actionEvent -> {
            hour = HourSpinner.getValue();
            minute = MinuteSpinner.getValue();
            String hourStr = String.valueOf(hour);
            String minuteStr = String.valueOf(minute);
            if(hour< 10){
                hourStr = "0" + hour;
            }
            if(minute< 10){
                minuteStr = "0" + minute;
            }

            time = hourStr + ":" + minuteStr;

        });


        AddButton.setOnAction(actionEvent -> {
            String client = "-";
            String type = TypeOfServiceField.getText();
            String specialist = SpecialistField.getText();
            Double cost = Double.parseDouble(CostField.getText());

            if (date != null && time != null) {
                System.out.println("Успех");
                if (!client.isEmpty() && !type.isEmpty() && !specialist.isEmpty() && !CostField.getText().isEmpty()) {
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    databaseHandler.addTimetable(date, time, client, type, cost, specialist);
                    Label.setStyle("-fx-text-fill: black");
                    Label.setText("Успешно добавленo!");
                } else {
                    Label.setStyle("-fx-text-fill: #d70e0e");
                    Label.setText("Ошибка!");
                }
            }
            else if (date != null || time != null){
                Label.setStyle("-fx-text-fill: #d70e0e");
                Label.setText("Вы не нажали на кнопку!");
            }
            else {
                Label.setStyle("-fx-text-fill: #d70e0e");
                Label.setText("Вы не нажали ни на одну из кнопок!");
            }
        });
    }
    void setContextMenu(){
        PriceService priceService = new PriceService();
        ContextMenu contextPrice = new ContextMenu();
        List<Price> priceList = priceService.getPriceArrayList();
        List<MenuItem> menuItemList2 = new ArrayList<>();

        for (Price price: priceList) {
            MenuItem menuItem = new MenuItem(price.getType());
            menuItemList2.add(menuItem);
        }

        for (MenuItem menuItem: menuItemList2) {
            menuItem.setOnAction(actionEvent -> {
                String s = menuItem.getText();
                TypeOfServiceField.setText(s);
                for (Price p:priceList) {
                    if(p.getType().equals(s)){
                        CostField.setText(String.valueOf(p.getCost()));
                        if(p.getSpecialist().equals("")){
                            SpecialistField.setVisible(false);
                            SpecialistField.setText("-");
                        }
                        else {
                            SpecialistField.setVisible(true);
                        ContextMenu contextMenu = new ContextMenu();
                        contextMenu.getItems().addAll(getSplite(p.getSpecialist()));
                        SpecialistField.setContextMenu(contextMenu);}
                    }
                }
            });
        }
        contextPrice.getItems().addAll(menuItemList2);
        TypeOfServiceField.setContextMenu(contextPrice);
    }

    List<MenuItem> getSplite(String string){
        List<String> list = new ArrayList<>(Arrays.asList(string.split(",")));
        List<MenuItem> menuItemList = new ArrayList<>();
        for (String s: list) {
            MenuItem menuItem = new MenuItem(s);
            menuItemList.add(menuItem);
        }
        for (MenuItem menuItem: menuItemList) {
            menuItem.setOnAction(actionEvent -> {
                String s = menuItem.getText();
                SpecialistField.setText(s.trim());
            });
        }
        return menuItemList;
    }

    boolean checkWeekend(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
            return true;
        }
        return  false;
    }

}
/*нужно:
- сортировка по убывании цены, по ближайшему занятию
- фильтрация по виду занятия, по специалистам и пользователям

- главная выход из стр админа/польз с сохранением данных и вход обратно на функц меню
- с регистрации вход на пользователя с приветствием

- редактирование не сделано
* */