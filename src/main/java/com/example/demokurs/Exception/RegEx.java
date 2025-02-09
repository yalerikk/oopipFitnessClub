package com.example.demokurs.Exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    private Pattern pattern;

    public boolean emailValidation (String email) {
        String reg = "^[A-Za-z0-9+_.-]+@(.+)$";
        pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean telephoneValidation (String telephone) {
        String reg = "^(\\+375|80)(44|29|25|33)\\d{7}$";
        pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }
}