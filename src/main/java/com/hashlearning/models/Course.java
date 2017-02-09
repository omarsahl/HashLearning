package com.hashlearning.models;

import java.util.HashMap;

public class Course {

    //TODO course class

    private String name ;
    HashMap<String , Tutorial> toutrials;

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
