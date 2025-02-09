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

public class ContactsController {


    @FXML
    private Button AuthButton;

    @FXML
    private Button HomeButton;

    @FXML
    void initialize() {

        HomeButton.setOnAction(actionEvent -> {
            openWin("/com/example/demokurs/home.fxml");
        });
        if(AuthController.myUser == null){
            AuthButton.setText("Авторизоваться");
        AuthButton.setOnAction(actionEvent -> {
          openWin("/com/example/demokurs/auth.fxml");
        });}


        else {
            AuthButton.setText("Зайти");
            AuthButton.setOnAction(actionEvent -> {
                if(AuthController.myUser.getRole() == 1){
                    openWin("/com/example/demokurs/adminFirstPage.fxml");
                }
                else {
                    openWin("/com/example/demokurs/firstPage.fxml");
                }

            });
        }
    }
    void openWin(String path){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = (Stage) (AuthButton.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }
}
