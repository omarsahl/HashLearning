package com.hashlearning.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bassel on 2/13/17.
 */
public class Student extends User {
    private List<Course> enrolledCourses;

    {
        enrolledCourses = new ArrayList<Course>();
    }

    public Student(String username, String email, String password) {
        super(username, email, password);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}
