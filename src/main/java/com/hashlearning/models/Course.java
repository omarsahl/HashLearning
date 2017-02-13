package com.hashlearning.models;

import java.util.Map;

public class Course {
    Map<String, Tutorial> tutorial;
    private int id;
    private String name ;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Course))
            return false;

        Course course = (Course) obj;
        return name.equals(course.name);
    }
}
