package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.demokurs.Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FirstPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label HiLabel;

    @FXML
    private Button HomeButton;

    @FXML
    private Button MyClassesButton;

    @FXML
    private Button SignUpClassesButton;

    @FXML
    void initialize() {
        //работает ли это.. и надо ли это добавлять в SignUpController? есть ли у меня там юзер.

        User user = AuthController.myUser;
        HiLabel.setText("Добро пожаловать, " + user.getLastname() + " " + user.getName() + "!");

        HomeButton.setOnAction(actionEvent -> {
            HomeButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/home.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Главная");
            stage.setScene(new Scene(root));
            stage.show();
        });

        MyClassesButton.setOnAction(actionEvent -> {
            MyClassesButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/showMyClasses.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Мои занятия");
            stage.setScene(new Scene(root));
            stage.show();
        });

        SignUpClassesButton.setOnAction(actionEvent -> {
            SignUpClassesButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/signUpClasses.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Запись на занятие");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}