package com.example.demokurs.Entity;

public class User {
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String telephone;
    private String email;
    private Integer role;

    public User(String lastname, String name,
                String login, String password,
                String telephone, String email){
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
    }


    public User(String lastname, String name,
                String login, String password,
                String telephone, String email, Integer role){
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
    }

    public User(String lastname, String name, String login, String password, Integer role) {
        this.lastname = lastname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
