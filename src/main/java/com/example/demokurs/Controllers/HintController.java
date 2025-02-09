package com.example.demokurs.Controllers;

import com.example.demokurs.Entity.Price;
import com.example.demokurs.Services.PriceService;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HintController {
    @FXML
    private TableView<Price> priceTableView;

    @FXML
    private TableColumn<Price, String > TypeColumn = new TableColumn<>("type");

    @FXML
    private TableColumn<Price, Double> CostColumn = new TableColumn<>("cost");

    @FXML
    private TableColumn<Price, String> SpecialistColumn = new TableColumn<>("specialist");

    @FXML
    void initialize(){
        priceTableView.setEditable(true);
        PriceService priceService = new PriceService();
        priceTableView.setItems(priceService.GetObservableListPrice());
        CostColumn.setCellValueFactory(new PropertyValueFactory<Price, Double>("cost"));
        SpecialistColumn.setCellValueFactory(new PropertyValueFactory<Price, String>("specialist"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Price, String>("type"));

        SpecialistColumn.setCellFactory(column -> new TableCell<Price, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    setWrapText(true); // Включаем перенос текста
                    // Устанавливаем желаемую высоту строки
                    setPrefHeight(50); // Задайте нужное значение высоты
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
    }
}
