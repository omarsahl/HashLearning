package com.hashlearning.utils;

import com.hashlearning.gui.screens.LoginScreen;
import com.hashlearning.models.User;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ahmed-ayman on 12/8/16.
 */
public class DataManager {
    public static HashMap<String, User> students;

    /*
    * this method loads the data from the files and saves it into our data structures.
    * */

    public static void loadData() throws FileNotFoundException {
        students = new HashMap<>();
        students = loadStudents();
        for (Map.Entry<String, User> s : students.entrySet()) {
            System.out.println(s.getKey() + " ----> " + s.getValue().getPassword());
        }
    }

    private static HashMap<String, User> loadStudents() throws FileNotFoundException {
        HashMap<String, User> listOfStudents = new HashMap<>();
        ClassLoader loader = LoginScreen.class.getClassLoader();
        FileInputStream fis = new FileInputStream(loader.getResource("files/students").getFile());
        BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
        bf
                .lines().map(line -> line.split("[|]"))
                .skip(1)
                .forEach(line -> {
                    //new student
                    User student = new User();
                    student.setId(line[0]);  //ID
                    student.setName(line[1]); // Name
                    student.setEnrolledCourses(line[2].split("-")); //add the courses
                    student.setMail(line[3]);  // MAIL
                    student.setPassword(line[4]); //Password
                    listOfStudents.put(student.getName(), student);           //put the Object to a Hash of students !


                });

        return listOfStudents;
    }

    public static boolean validate(String username, String password) {
        return students.get(username) != null && students.get(username).getPassword().equals(password);
    }
}
