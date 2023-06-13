package com.example.mini_projet_01;

import androidx.annotation.NonNull;

public class User {

    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String image;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(String firstName, String lastName, String gender, String city, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.city = city;
        this.image = image;
    }

    public String fullName() {
        return String.format("%s %s ", this.getFirstName(), this.getLastName());
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Hi , i'm %s, i'm a %s.%nI live in %s.",
                this.firstName,
                this.getGender().equals("male") ? "♂" : "♀",
                this.getCity());
    }
}
