package com.example.demokurs.Entity;

public class Specialist {
    private String imagePath;
    private String fi;
    private String speciality;
    private String skills;
    private String name;
    private String lastname;

    public Specialist(String imagePath, String speciality, String skills, String name, String lastname) {
        this.imagePath = imagePath;
        fi = lastname + " " + name;
        this.speciality = speciality;
        this.skills = skills;
        this.name = name;
        this.lastname = lastname;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFi() {
        return fi;
    }

    public void setFi(String fi) {
        this.fi = fi;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
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


}
