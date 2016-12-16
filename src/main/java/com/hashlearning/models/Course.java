package com.hashlearning.models;

import java.util.HashMap;

public class Course {

    //TODO course class

    HashMap<String, Tutorial> tutorials;
    private int id;
    private String name;
    private Instructor instructor;
    private int duration; // in hours, TODO pick a better presentation

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Course))
            return false;
        Course course = (Course) obj;
        return name.equals(course.name);
    }
}
