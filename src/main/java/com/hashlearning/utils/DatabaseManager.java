package com.hashlearning.utils;

import com.google.gson.*;
import com.hashlearning.gui.controllers.DashboardManager;
import com.hashlearning.gui.custom_views.MaterialDialog;
import com.hashlearning.models.Course;
import com.hashlearning.models.User;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import javax.swing.filechooser.FileSystemView;
import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by Ahmed Ayman on 12/8/16
 */
public class DatabaseManager {

    private static final String USERNAME_KEY = "username";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String ENROLLED_COURSES_KEY = "enrolledCourses";

    public static HashMap<String, User> users;
    public static JsonArray usersJson;

    private static JsonParser parser;
    private static Gson gson;
    private static File jsonFile;
    private static BufferedWriter writer;
    private static FileInputStream fis;

    static {
        parser = new JsonParser();
        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    }

    public static void initJsonDatabase() {

        File testFile = new File(FileSystemView.getFileSystemView().getHomeDirectory(), "users.json");
        if (testFile.exists()) {
            jsonFile = testFile;
            try {
                fis = new FileInputStream(jsonFile);
                byte[] data = new byte[(int) jsonFile.length()];
                fis.read(data);
                fis.close();
                String jsonString = new String(data, "UTF-8");
                usersJson = parser.parse(jsonString).getAsJsonArray();
            } catch (IOException e) {
                e.printStackTrace();
                ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());

            }
        } else {
            MaterialDialog.showDialog(Alert.AlertType.ERROR, "Couldn't find \"users.json\"", "Couldn't find \"users.json\"", "You forgot to copy \"users.json\" file to your Desktop!");
            Platform.exit();
        }
    }

    public static void loadUsersFromJsonDatabase() {

        users = new HashMap<String, User>();

        for (JsonElement jsonElement : usersJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            User user = gson.fromJson(jsonObject, User.class);
            users.put(user.getUsername(), user);
        }

        for (Map.Entry<String, User> s : users.entrySet()) {
            System.out.println("Username: " + s.getValue().getUsername());
            System.out.println("Email: " + s.getValue().getEmail());
            System.out.println("Password: " + s.getValue().getPassword());
            System.out.println("Enrolled courses: ");
            for (Course course : s.getValue().getEnrolledCourses()) {
                System.out.println(course.getName());
            }
            System.out.println("----------------------------------------------------");
        }
    }

    public static void addUserJson(String username, String email, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);

        String newUserString = gson.toJson(newUser);
        usersJson.add(parser.parse(newUserString));
        updateJsonFile();
        initJsonDatabase();
        loadUsersFromJsonDatabase();
    }

    public static void enrollInCourse(Course course, User user){

        for (JsonElement jsonElement : usersJson) {
            JsonObject userToEdit = jsonElement.getAsJsonObject();
            String username = userToEdit.getAsJsonPrimitive(USERNAME_KEY).getAsString();
            if (username.equals(user.getUsername())) {
                System.out.println("Got this username: " + username);
                JsonArray enrolledCoursesJson = userToEdit.getAsJsonArray(ENROLLED_COURSES_KEY);
                enrolledCoursesJson.add(parser.parse(gson.toJson(course)));
            }
        }
        updateJsonFile();
        initJsonDatabase();
        loadUsersFromJsonDatabase();
        SessionManager.setCurrentUser(users.get(user.getUsername()));
    }

    public static boolean checkUser(String username, String password) {
        return (users.get(username) != null && users.get(username).getPassword().equals(password));
    }

    private static void updateJsonFile() {

        try {
            writer = new BufferedWriter(new PrintWriter(jsonFile));
            gson.toJson(usersJson, writer);
        } catch (IOException e) {
            e.printStackTrace();
            ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printUsers() {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        System.out.println(gson.toJson(usersJson));
    }

}
