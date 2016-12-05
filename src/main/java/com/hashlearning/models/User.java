package com.hashlearning.models;


import java.util.HashMap;

public class User {
    //TODO: implement user class

    private  String name;
    private String mail ;
    private int id ;
    private String password;
    public static HashMap<String,Course> enrolledCourses;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
