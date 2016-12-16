package com.hashlearning.models;


import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String email;
    private String password;
    private List<Course> enrolledCourses;

    {
        enrolledCourses = new ArrayList<Course>();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
