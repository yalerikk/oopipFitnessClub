package com.example.demokurs.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.demokurs.Entity.Specialist;
import com.example.demokurs.Services.SpecialistService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class SpecialistsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private Button HomeButton;

    @FXML
    private Text NameSpecialist;

    @FXML
    private Button NextButton;

    @FXML
    private ImageView SImage;

    @FXML
    private TextArea Skills;

    @FXML
    private Text Speciality;//персональный тренер

    static Specialist specialist = null;


    //нужно добавить кнопки чтобы перелистывалось все. пока не понимаю…

    SpecialistService specialistService = new SpecialistService();
    List<Specialist> specialistsList = specialistService.getSpecialistArrayList();
    static int i=0;

    @FXML
    void initialize() {
        specialist = specialistsList.get(i);
        showSpec(specialist);
        NextButton.setOnAction(actionEvent -> {
            if (i < specialistsList.size() - 1) {
                i++;
                specialist = specialistsList.get(i);
                showSpec(specialist);
            }
            else {
                i = -1;
                showSpec(specialist);
            }
        });

        BackButton.setOnAction(actionEvent -> {
            if(i > 0){
                i--;
                specialist = specialistsList.get(i);
                showSpec(specialist);
            }
            else {
                i = specialistsList.size();
                showSpec(specialist);
            }
        });

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
    }
    void showSpec(Specialist specialist){
        NameSpecialist.setText(specialist.getFi());
        Image image = new Image(specialist.getImagePath());
        SImage.setImage(image);
        Skills.setText(specialist.getSkills());
        Speciality.setText(specialist.getSpeciality());
    }
}