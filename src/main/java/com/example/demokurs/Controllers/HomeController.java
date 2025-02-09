package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AboutButton;

    @FXML
    private Button ClassesButton;

    @FXML
    private Button ContactsButton;

    @FXML
    private Button HomeButton;

    @FXML
    private Button SpecialistsButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button hintButton;

    @FXML
    void initialize() {
        if(AuthController.myUser != null){
            exitButton.setVisible(true);
        }
        else {
            exitButton.setVisible(false);
        }
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

        SpecialistsButton.setOnAction(actionEvent -> {
            SpecialistsButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/specialists.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Специалисты");
            stage.setScene(new Scene(root));
            stage.show();
        });

        ContactsButton.setOnAction(actionEvent -> {
            ContactsButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/contacts.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Контакты");
            stage.setScene(new Scene(root));
            stage.show();
        });

        AboutButton.setOnAction(actionEvent -> {
            AboutButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/about.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("О нас");
            stage.setScene(new Scene(root));
            stage.show();
        });

        exitButton.setOnAction(actionEvent -> {
            AuthController.myUser = null;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/home.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = (Stage) (exitButton.getScene().getWindow());
            stage.setTitle("Главная?");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }


}