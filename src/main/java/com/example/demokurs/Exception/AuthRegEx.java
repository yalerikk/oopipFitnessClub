package com.example.demokurs.Exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthRegEx {

    private Pattern pattern;

    public boolean loginValidation (String login) {
        String reg = "^[a-zA-Z](?:[a-zA-Z0-9_-](?!.*[_-]{2})){6,15}$";
        pattern = Pattern.compile(reg);
        Matcher matcher1 = pattern.matcher(login);
        System.out.println(matcher1.matches()+ "логин");
        return matcher1.matches();
    }
    public boolean passwordValidation (String password) {
        String reg = "^(?=.*[a-z])(?=.*[A-Z]?)(?=.*\\d?)(?=.*[!#$%^&_-]).{6,15}$";
        pattern = Pattern.compile(reg);
        Matcher matcher2 = pattern.matcher(password);
        System.out.println(matcher2.matches() + "пароль");
        return matcher2.matches();
    }
}
