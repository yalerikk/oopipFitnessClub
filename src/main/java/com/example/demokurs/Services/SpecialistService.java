package com.example.demokurs.Services;

import com.example.demokurs.Entity.Specialist;
import com.example.demokurs.Repository.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SpecialistService {
    private List<Specialist> specialistArrayList;
    public SpecialistService(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        specialistArrayList = databaseHandler.getSpec();
    }
    public List<Specialist> getSpecialistArrayList() {
        return specialistArrayList;
    }
    public ObservableList<Specialist> GetObservableListSpecialist(){
        return FXCollections.observableList(specialistArrayList);
    }
    DatabaseHandler databaseHandler= new DatabaseHandler();
    public void addSpe(){
        for (Specialist specialist:specialistArrayList) {
            databaseHandler.addSpecialist(specialist.getImagePath(),specialist.getSpeciality(),specialist.getSkills(),specialist.getName(),specialist.getLastname());
        }
    }
    public List<String> getSpecFiList() {
        List<String> getSpecArrayList = new ArrayList<>();
        for (Specialist specialist: specialistArrayList) {
            getSpecArrayList.add(specialist.getLastname() + " " + specialist.getName());
        }
        return getSpecArrayList;
    }
}

/*new ArrayList<>(List.of(new Specialist("C:\\Users\\olgah\\IdeaProjects\\demokurs\\src\\main\\resources\\Assets\\Specialists\\s1.png", "Персональный тренер", "Методист учебного центра «Фитнес \n" +
                        "образование» по направлениям:\n" +
                        "1) Курсы: «Коррекционный тренинг», \n" +
                        "«Подвесной тренинг (TRX)»\n" +
                        "2) Семинары серии GOLD: «Cтопа и голеностоп», \n" +
                        "«Лопатка и плечо», «Ягодицы.ТБС», «Позвоночник»,\n" +
                        "«Фасция и миофасциальный релиз»", "Фёдор", "Фёдоров"),
                new Specialist("C:\\Users\\olgah\\IdeaProjects\\demokurs\\src\\main\\resources\\Assets\\Specialists\\s2.png", "Персональный тренер", "Персональные тренировки в тренажерном зале.\n" +
                        "Знакомство с фитнесом.\n" +
                        "Диагностика опорно-двигательного аппарата.\n" +
                        "Индивидуальный подход к каждому клиенту.\n" +
                        "Силовые тренировки.\n" +
                        "Увеличение мышечной массы и уменьшение массы тела.\n" +
                        "Функциональный тренинг.\n" +
                        "Работа с детьми и подростками.\n" +
                        "Индивидуальные тренировки по кикбоксингу.", "Максим", "Гармаза"),
                new Specialist("C:\\Users\\olgah\\IdeaProjects\\demokurs\\src\\main\\resources\\Assets\\Specialists\\s3.png", "Персональный тренер", "Персональный тренинг в тренажерном зале.\n" +
                        "Индивидуализация тренировочных программ, проведение фитнес-тестирования, мониторинг результатов развития физических качеств.\n" +
                        "Силовой тренинг, общая физическая подготовка.\n" +
                        "Улучшение пропорций тела, качества тела, увеличение мышечной массы.\n" +
                        "Корректировка плана питания в зависимости от целей тренировочного процесса.", "Александр", "Надточеев"),
                new Specialist("C:\\Users\\olgah\\IdeaProjects\\demokurs\\src\\main\\resources\\Assets\\Specialists\\s4.png", "Специалистка групповых программ", "Суставная гимнастика.\n" +
                        "Цигун.\n" +
                        "Дыхательная гимнастика.", "Лариса", "Палюхович"),
                new Specialist("C:\\Users\\olgah\\IdeaProjects\\demokurs\\src\\main\\resources\\Assets\\Specialists\\s5.png", "Специалистка групповых программ", "Stretching.\n" +
                        "Pilates.\n" +
                        "Силовой тренинг.\n" +
                        "Функциональный тренинг.", "Александра", "Гниль"),
                new Specialist("C:\\Users\\olgah\\IdeaProjects\\demokurs\\src\\main\\resources\\Assets\\Specialists\\s6.png", "Персональный тренерша", "Персональный тренинг в тренажерном зале.\n" +
                        "Индивидуализация тренировочных программ, проведение фитнес-тестирования, мониторинг результатов развития физических качеств.\n" +
                        "Силовой тренинг, общая физическая подготовка, развитие всех физических качеств.\n" +
                        "Коррекционный тренинг.\n" +
                        "Функциональный тренинг.\n" +
                        "Акцент на развитии и совершенствовании биомеханики основных паттернов движения.", "Паулина", "Еременко")));*/
