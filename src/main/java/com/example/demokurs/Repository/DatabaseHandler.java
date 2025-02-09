package com.example.demokurs.Repository;

import com.example.demokurs.Entity.Price;
import com.example.demokurs.Entity.Specialist;
import com.example.demokurs.Entity.Timetable;
import com.example.demokurs.Entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USERS_LASTNAME + ", "
                + Const.USERS_NAME + ", " + Const.USERS_LOGIN + ", " + Const.USERS_PASSWORD + ", "
                + Const.USERS_TELEPHONE + ", " + Const.USERS_EMAIL + ", " + Const.USERS_ROLE
                + ") " + "VALUES(?,?,?,?,?,?,?)";
        //при регистрации по умолчанию роль пользователя определяется как 0

        try {
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, user.getLastname());
        prSt.setString(2, user.getName());
        prSt.setString(3, user.getLogin());
        prSt.setString(4, user.getPassword());
        prSt.setString(5, user.getTelephone());
        prSt.setString(6, user.getEmail());
        prSt.setInt(7, user.getRole());
        prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        ResultSet resultSet = null;
        String resSetSt = "SELECT * FROM " + Const.USER_TABLE;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(resSetSt);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String lastname = resultSet.getString(2);
                String name = resultSet.getString(3);
                String login = resultSet.getString(4);
                String password = resultSet.getString(5);
                String telephone = resultSet.getString(6);
                String email = resultSet.getString(7);
                int role = resultSet.getInt(8);

                list.add(new User(lastname, name, login, password, telephone, email, role));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
                /*-----------------------------------------------------------------------*/
    public void addSpecialist(String imagePath, String speciality, String skills, String name, String lastname) {
        String insert = "INSERT INTO " + Const.SPECIALISTS_TABLE + "(" +
                Const.SPECIALISTS_LASTNAME + "," + Const.SPECIALISTS_NAME + "," +
                Const.SPECIALISTS_SPECIALITY + "," + Const.SPECIALISTS_SKILLS + "," +
                Const.SPECIALISTS_IMAGEPATH + ")" +
                "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, lastname);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, speciality);
            preparedStatement.setString(4, skills);
            preparedStatement.setString(5, imagePath);


            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Specialist> getSpec() {
        List<Specialist> list = new ArrayList<>();
        ResultSet resultSet = null;
        String resSetSt = "SELECT * FROM " + Const.SPECIALISTS_TABLE;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(resSetSt);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String lastname = resultSet.getString(2);
                String name = resultSet.getString(3);
                String speciality = resultSet.getString(4);
                String skills = resultSet.getString(5);
                String imagePath = resultSet.getString(6);

                list.add(new Specialist(imagePath, speciality,  skills, name, lastname));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
                    /*-----------------------------------------------------------------------*/
    public void addTimetable(String date, String time, String client, String type, double cost, String specialist) {
        String insert = "INSERT INTO " + Const.TIMETABLE_TABLE + "(" +
                Const.TIMETABLE_DATE + "," + Const.TIMETABLE_TIME + "," +
                Const.TIMETABLE_CLIENT + "," + Const.TIMETABLE_TYPE + "," +
                Const.TIMETABLE_COST + "," + Const.TIMETABLE_SPECIALIST + ")" +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, client);
            preparedStatement.setString(4, type);
            preparedStatement.setDouble(5, cost);
            preparedStatement.setString(6, specialist);

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Timetable> getTimetable() {
        List<Timetable> list = new ArrayList<>();
        ResultSet resultSet = null;
        String resSetSt = "SELECT * FROM " + Const.TIMETABLE_TABLE;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(resSetSt);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String date = resultSet.getString(2);
                String time = resultSet.getString(3);
                String client = resultSet.getString(4);
                String type = resultSet.getString(5);
                double cost = resultSet.getDouble(6);
                String specialist = resultSet.getString(7);

                list.add(new Timetable(date, time, client, type, cost, specialist));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteTimetable(Timetable selected) {
        String deleting = "DELETE FROM " + Const.TIMETABLE_TABLE + " WHERE " +
                Const.TIMETABLE_DATE + "=? AND " + Const.TIMETABLE_TIME + "=? AND " + Const.TIMETABLE_SPECIALIST + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(deleting);
            preparedStatement.setString(1, selected.getDate());
            preparedStatement.setString(2, selected.getTime());
            preparedStatement.setString(3, selected.getSpecialist());
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*-----------------------------------------------------------------------*/
    public void addPrice(String type, double cost, String specialist) {
        String insert = "INSERT INTO " + Const.PRICE_TABLE + "(" +
                Const.PRICE_TYPE + "," + Const.PRICE_COST + "," + Const.PRICE_SPECIALIST + ")" +
                "VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, type);
            preparedStatement.setDouble(2, cost);
            preparedStatement.setString(3, specialist);

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Price> getPrice() {
        List<Price> list = new ArrayList<>();
        ResultSet resultSet = null;
        String resSetSt = "SELECT * FROM " + Const.PRICE_TABLE;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(resSetSt);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String type = resultSet.getString(2);
                double cost = resultSet.getDouble(3);
                String specialist = resultSet.getString(4);

                list.add(new Price(type, cost, specialist));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void updateTimetable(Timetable timetable) {
        String update = "UPDATE " + Const.TIMETABLE_TABLE + " SET " + Const.TIMETABLE_CLIENT +"=? WHERE " +
                Const.TIMETABLE_SPECIALIST +"=? AND " + Const.TIMETABLE_TIME +"=? AND " + Const.TIMETABLE_DATE +"=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, timetable.getClient());
            preparedStatement.setString(2, timetable.getSpecialist());
            preparedStatement.setString(3, timetable.getTime());
            preparedStatement.setString(4, timetable.getDate());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateAllTimetable(List<Timetable> timetableList) {
        String deleting = "DELETE FROM " + Const.TIMETABLE_TABLE;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(deleting);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Timetable timetable:timetableList) {
            addTimetable(timetable.getDate(),timetable.getTime(),timetable.getClient(), timetable.getType(), timetable.getCost(), timetable.getSpecialist());
        }
    }
}

