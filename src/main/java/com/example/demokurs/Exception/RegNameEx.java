package com.example.demokurs.Exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegNameEx {

    private Pattern pattern;

    public boolean lastnameValidation (String lastname) {
        String reg = "^[А-Я][а-я]*$";
        pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(lastname);
        return matcher.matches();
    }
    public boolean nameValidation (String name) {
        String reg = "^[А-Я][а-я]*$";
        pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
