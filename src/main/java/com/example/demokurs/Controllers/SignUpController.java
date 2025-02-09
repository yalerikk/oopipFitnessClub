package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demokurs.Entity.User;
import com.example.demokurs.Exception.*;
import com.example.demokurs.Main;
import com.example.demokurs.Repository.DatabaseHandler;
import com.example.demokurs.Services.UserService;
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

public class SignUpController {


    @FXML
    private TextField EmailField;

    @FXML
    private TextField PasHide;

    @FXML
    private Label Label;

    @FXML
    private CheckBox CheckPasBox;

    @FXML
    private TextField LastnameField;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField NameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField TelField;

    @FXML
    void initialize() {
        CheckPasBox.setOnAction(this::changeVisibility);
        SignUpButton.setOnAction(actionEvent -> {
            signUpNewUser();
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        RegNameEx regNameEx = new RegNameEx();
        AuthRegEx authRegEx = new AuthRegEx();
        RegEx regEx = new RegEx();
        try {
            String lastname = LastnameField.getText().trim();
            String name = NameField.getText().trim();
            String login = LoginField.getText().trim();
            String password = PasswordField.getText().trim();
            String telephone = TelField.getText().trim();
            String email = EmailField.getText().trim();
            if (!name.equals("") && !lastname.equals("") && !login.equals("") && !password.equals("") && !telephone.equals("") && !email.equals("") && !telephone.equals("")) {
                if ((!regNameEx.lastnameValidation(lastname) && !regNameEx.nameValidation(name)) || (!regNameEx.lastnameValidation(lastname) || !regNameEx.nameValidation(name))) {
                    throw new InvalidInputLastnameNameException();
                }
                if (!authRegEx.loginValidation(login) && !authRegEx.passwordValidation(password)) {
                    throw new InvalidInputLoginPasswordException();
                }
                if (!authRegEx.loginValidation(login) || !authRegEx.passwordValidation(password)) {
                    throw new InvalidInputLoginPasswordException();
                }
                if ((!regEx.emailValidation(email) && !regEx.telephoneValidation(telephone)) || (!regEx.emailValidation(email) || !regEx.telephoneValidation(telephone))) {
                    throw new InvalidInputEmailTelephoneException();
                }
                if (!check(login, password)) {
                    throw new UserExistsException();
                }
                User user = new User(lastname, name, login, password, telephone, email, 0);
                AuthController.myUser = user;
                dbHandler.signUpUser(user);
                openFirstPage("/com/example/demokurs/firstPage.fxml");

                System.out.println("Успех");
            }
            else {
                LoginField.setStyle("-fx-border-color: red");
                PasswordField.setStyle("-fx-border-color: red");
                PasHide.setStyle("-fx-border-color: red");
                LastnameField.setStyle("-fx-border-color: red");
                NameField.setStyle("-fx-border-color: red");
                EmailField.setStyle("-fx-border-color: red");
                TelField.setStyle("-fx-border-color: red");
                Label.setText("Введите все данные!");
            }
        }catch (InvalidInputEmailTelephoneException e){
            EmailField.setStyle("-fx-border-color: red");
            TelField.setStyle("-fx-border-color: red");
            Label.setText("Некорректный ввод для почты и/или телефона!");
        }catch (InvalidInputLastnameNameException e){
            LastnameField.setStyle("-fx-border-color: red");
            NameField.setStyle("-fx-border-color: red");
            Label.setText("Некорректный ввод для имени и/или фамилии!");
        }catch (InvalidInputLoginPasswordException e){
            LoginField.setStyle("-fx-border-color: red");
            PasswordField.setStyle("-fx-border-color: red");
            PasHide.setStyle("-fx-border-color: red");
            Label.setText("Некорректный ввод для логина и/или пароля!");
        }catch (UserExistsException e){
            LoginField.setStyle("-fx-border-color: red");
            PasswordField.setStyle("-fx-border-color: red");
            PasHide.setStyle("-fx-border-color: red");
            Label.setText("Пользователь с такими данными уже существует!");
        }
    }

    public boolean check(String login,String password){
        UserService userService = new UserService();
        for (User u: userService.getUserArrayList()) {
            System.out.println(u.getLogin() + u.getPassword());
            if(u.getLogin().equals(login) && u.getPassword().equals(password)){
                return false;
            }
        }

        return true;
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

    public void openFirstPage(String window){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = (Stage) (SignUpButton.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }
}