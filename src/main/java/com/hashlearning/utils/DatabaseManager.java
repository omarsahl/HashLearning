package com.hashlearning.utils;

import com.google.gson.*;
import com.hashlearning.gui.screens.LoginScreen;
import com.hashlearning.models.Course;
import com.hashlearning.models.User;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.Buffer;
import java.util.*;

/**
 * Created by Ahmed Ayman on 12/8/16
 */
public class DatabaseManager {

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
        jsonFile = new File(FileSystemView.getFileSystemView().getHomeDirectory(), "users.json");
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
            System.out.println("Enrolled courses: " + s.getValue().getEnrolledCourses());
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
