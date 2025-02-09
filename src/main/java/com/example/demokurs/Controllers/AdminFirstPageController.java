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

public class AdminFirstPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label HiLabel;

    @FXML
    private Button HomeButton;

    @FXML
    private Button EditButton;

    @FXML
    private Button ShowClassesButton;

    @FXML
    private Button AddClassesButton;

    @FXML
    void initialize() {
        //работает ли это.. и надо ли это добавлять в SignUpController? есть ли у меня там юзер.

        User user = AuthController.myUser;
        HiLabel.setText("Добро пожаловать, " + user.getLogin() + "!");

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

        AddClassesButton.setOnAction(actionEvent -> {
            AddClassesButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/addClasses.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Добавление занятия");
            stage.setScene(new Scene(root));
            stage.show();
        });

        ShowClassesButton.setOnAction(actionEvent -> {
            ShowClassesButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/showClasses.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Просмотр занятий");
            stage.setScene(new Scene(root));
            stage.show();
        });

        EditButton.setOnAction(actionEvent -> {
            EditButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/editClasses.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Просмотр занятий");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}