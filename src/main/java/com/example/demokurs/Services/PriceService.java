package com.example.demokurs.Services;

import com.example.demokurs.Entity.Price;
import com.example.demokurs.Repository.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PriceService {
    private List<Price> priceArrayList;
    DatabaseHandler databaseHandler = new DatabaseHandler();
    public PriceService(){
       priceArrayList = databaseHandler.getPrice();

    }
    public List<Price> getPriceArrayList() {
        return priceArrayList;
    }
    public ObservableList<Price> GetObservableListPrice(){
        return FXCollections.observableList(priceArrayList);
    }
    /*public List<String> getPriceList() {
        List<String> getPriceArrayList = new ArrayList<>();
        for (Price price: priceArrayList) {
            getPriceArrayList.add(price.getType() + " - " + price.getCost());
        }
        return getPriceArrayList;
    }

    List.of(new Price("Групповое занятие", 50, "Палюхович Лариса, Гниль Александра"),
            new Price("Персональная тренировка", 25, "Фёдоров Фёдор, Гармаза Максим, Надточеев Александр, Еременко Паулина"), new Price("Тренажерный зал", 20, ""), new Price("Коррекционный тренинг", 40, "Палюхович Лариса"))*/
}

