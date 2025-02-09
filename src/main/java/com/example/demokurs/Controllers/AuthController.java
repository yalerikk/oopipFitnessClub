package com.example.demokurs.Controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.example.demokurs.Entity.User;
import com.example.demokurs.Exception.AuthRegEx;
import com.example.demokurs.Exception.InvalidInputLoginPasswordException;
import com.example.demokurs.Main;
import com.example.demokurs.Repository.DatabaseHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.w3c.dom.Node;

public class AuthController {
    @FXML
    private Button AuthButton;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasHide;

    @FXML
    private Label Label;

    @FXML
    private CheckBox CheckPasBox;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignUpButton;
    public static User myUser =null;

    @FXML
    void initialize() {
        CheckPasBox.setOnAction(this::changeVisibility);
        AuthButton.setOnAction(actionEvent -> {
            String loginText = LoginField.getText().trim();
            String loginPassword;
                if (CheckPasBox.isSelected()) {
                    loginPassword = PasHide.getText().trim();
                }
                else {
                    loginPassword = PasswordField.getText().trim();
                }
            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);

            }
            else {
                Label.setText("Введены не все данные!");
            }
        });

        SignUpButton.setOnAction(actionEvent -> {
            SignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demokurs/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Регистрация");
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    void changeVisibility(ActionEvent actionEvent) {
        if (!PasswordField.getText().isEmpty()) {//поле не пустое
            if (CheckPasBox.isSelected()) {
                PasHide.setText(PasswordField.getText());
                PasHide.setVisible(true);
                PasswordField.setVisible(false);
            } else {
                PasswordField.setText(PasHide.getText());
                PasswordField.setVisible(true);
                PasHide.setVisible(false);
            }
        }
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        int counter=0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
            }
        }
        if(counter == 0){
            Label.setText("Неправильно введён логин или пароль!");

        }

        try {
            AuthRegEx authRegEx = new AuthRegEx();

            if ((!authRegEx.loginValidation(loginText) && !authRegEx.passwordValidation(loginPassword)) || (!authRegEx.loginValidation(loginText) || !authRegEx.passwordValidation(loginPassword))) {
                throw new InvalidInputLoginPasswordException();
            }
            if (counter >= 1) {
                myUser = returnUser(loginText, loginPassword);

                if (myUser.getRole().equals(1)) {
                    openFirstPage("/com/example/demokurs/adminFirstPage.fxml");
                } else {
                    openFirstPage("/com/example/demokurs/firstPage.fxml");
                }
            }
        } catch (InvalidInputLoginPasswordException e) {
            LoginField.setStyle("-fx-border-color: red");
            PasswordField.setStyle("-fx-border-color: red");
            PasHide.setStyle("-fx-border-color: red");
            Label.setText("Некорректный ввод для логина и/или пароля!");
        }
    }

    public void openFirstPage(String window){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = (Stage) (AuthButton.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public User returnUser(String login, String password){
        User user = null;

        try{
            DatabaseHandler databaseHandler = new DatabaseHandler();
            User user1 = new User(login, password);
            ResultSet resultSet = databaseHandler.getUser(user1);
            while (resultSet.next()){
                String lastname = resultSet.getString(2);
                String name = resultSet.getString(3);
                Integer role = resultSet.getInt(8);
                user = new User(lastname, name, login, password, role);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}