package com.example.listviewtodo.model;

import com.example.listviewtodo.R;

import java.io.Serializable;
import java.util.Date;

public class Person {
    private String firstName;
    private String secondName;
    private Sex sex;
    private Date birthDate;
    private int photoResource;

    public Person(String firstName, String secondName, Sex sex, Date birthDay) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.sex = sex;
        this.birthDate = birthDay;
        if (sex == Sex.MALE) {
            this.photoResource = R.drawable.m1;
        } else {
            this.photoResource = R.drawable.w1;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getPhotoResource() {
        return photoResource;
    }
}
