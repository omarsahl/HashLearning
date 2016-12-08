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
}
