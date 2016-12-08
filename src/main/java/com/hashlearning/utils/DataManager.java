package com.hashlearning.utils;

import com.hashlearning.gui.screens.LoginScreen;
import com.hashlearning.models.Course;
import com.hashlearning.models.User;

import java.io.*;
import java.util.*;

/**
 * Created by ahmed-ayman on 12/8/16.
 */
public class DataManager {
    public static HashMap<String, User> students;

    /*
    * this mesthod loads the data from the files and saves it into our data structures.
    * */

    public static void loadData() throws FileNotFoundException {
        students = new HashMap<>();
        students = loadStudents();

        System.out.println("Users       Passwords");
        for (Map.Entry<String,User>s :students.entrySet()){
            System.out.println(s.getKey() +"  "+s.getValue().getPassword());
      }
  }
    public static void refresh() throws FileNotFoundException {
        students=loadStudents();
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
                    student.setId(line[0]);  // No need for the ID
                    student.setName(line[1]); // Name
                  //  System.out.printf(line[1]+" ");
                    student.setMail(line[3]);  // MAIL
                    student.setPassword(line[4]); //Password
                //    System.out.printf(line[4]+"\n");
                    ArrayList<Course> courses = new ArrayList<Course>();
                    String[] coursesNames = line[2].split("-");
                    for (String courseName : coursesNames)
                        courses.add(new Course(courseName));
                    student.setEnrolledCourses(courses); //add the courses
                    listOfStudents.put(student.getName(), student);           //put the Object to a Hash of students !

                });
        return listOfStudents;
    }
    public static boolean validate(String username, String password) {
        return (students.get(username) != null && students.get(username).getPassword().equals(password));
    }
    public static void addStudent(String userName , String password, String mail) throws IOException {
        String line ="1|"+userName+"|"+password+"|"+mail;

        ClassLoader loader = LoginScreen.class.getClassLoader();
        File file=new File(loader.getResource("files/students").getFile());
        PrintWriter out =null;
        try{
        out     = new PrintWriter(new FileWriter(file,true));
            out.println(line);

            System.out.println(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
finally {
            out.close();
        }
   for(Map.Entry<String,User>s :students.entrySet()){
            System.out.println(s.getKey());
        }
    }
}
