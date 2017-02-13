package com.hashlearning.utils;

import com.google.gson.*;
import com.hashlearning.gui.custom_views.MaterialDialog;
import com.hashlearning.models.Course;
import com.hashlearning.models.Student;
import com.hashlearning.models.Tutorial;
import com.hashlearning.models.User;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for creating users database and updating it when needed.
 */
public class DatabaseManager {

    // Some keys for Json parsing
    private static final String USERNAME_KEY = "username";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String ENROLLED_COURSES_KEY = "enrolledCourses";
    private static final JsonParser parser;
    private static final File jsonFile, tutorialFile;
    public static Map<String, User> users;
    public static Map<String, Tutorial> javaTutorials; //name = name of the tutorial.
    private static Gson gson;
    private static JsonArray usersJson, javaJson;

    static {
        parser = new JsonParser();
        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        jsonFile = new File(FileSystemView.getFileSystemView().getHomeDirectory(), "users.json");
        tutorialFile = new File(FileSystemView.getFileSystemView().getHomeDirectory(), "java.json");
    }

    /**
     * Initializes the Json Database by getting the users.json file and parsing it and then filling the users json array.
     */
    public static void initJsonDatabase() {
        // if file doesn't exist create an empty one
        if (!jsonFile.exists()) try {
            InputStream in = DatabaseManager.class.getClassLoader().getResourceAsStream("files/users.json");
            Files.copy(in, jsonFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(jsonFile)) {
            byte[] data = new byte[(int) jsonFile.length()];
            fis.read(data);
            fis.close();
            String jsonString = new String(data, "UTF-8");
            usersJson = parser.parse(jsonString).getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
            ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
        } finally {
            if (!jsonFile.exists()) {
                MaterialDialog.showDialog(Alert.AlertType.ERROR, "Couldn't find \"users.json\"", "Couldn't find \"users.json\"", "You forgot to copy \"users.json\" file to your Desktop!");
                Platform.exit();
            }
        }

        // Tutorial
        if (!tutorialFile.exists()) {
            InputStream in = DatabaseManager.class.getClassLoader().getResourceAsStream("files/java.json");
            try {
                Files.copy(in, tutorialFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (tutorialFile.exists()) try (FileInputStream fis = new FileInputStream(tutorialFile)) {
            byte[] data = new byte[(int) tutorialFile.length()];
            fis.read(data);
            String json = new String(data, "UTF-8");
            javaJson = parser.parse(json).getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
            ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
        }
        else {
            MaterialDialog.showDialog(Alert.AlertType.ERROR, "Couldn't find \"java.json\"", "Couldn't find \"java.json\"", "check this out!");
            Platform.exit();
        }
    }

    /**
     * Loads the users in users json array to the users Hashmap.
     */
    public static void loadUsersFromJsonDatabase() {
        users = new HashMap<>();
        for (JsonElement jsonElement : usersJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Student user = gson.fromJson(jsonObject, Student.class);
            users.put(user.getUsername(), user);
        }
    }

    /**
     * Loads the java tutorials in tutorial json array to the java Hashmap.
     */
    public static void javaTutorialsFromJsonDatabase() {
        javaTutorials = new HashMap<>();
        for (JsonElement jsonElement : javaJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Tutorial tutorial = gson.fromJson(jsonObject, Tutorial.class);
            javaTutorials.put(tutorial.getName(), tutorial); //adding the name and the tutorial object to the HashMap.
        }
    }

    /**
     * Adds javaJson new user to the database, then updates the json file and also the users hashmap.
     *
     * @param username new user's username.
     * @param email    new user's email.
     * @param password new user's password
     */
    public static void addUserJson(String username, String email, String password) {
        Student newUser = new Student(username, email, password);
        String newUserString = gson.toJson(newUser);
        usersJson.add(parser.parse(newUserString));
        updateJsonFile();
        initJsonDatabase();
        loadUsersFromJsonDatabase();
    }


    /**
     * Enrolls javaJson user to javaJson course, then updates the json file and the users hashmap.
     *
     * @param course the course which the user has to be enrolled in.
     * @param user   the user to be enrolled.
     */
    public static void enrollInCourse(Course course, Student user) {
        // check if user is already enrolled
        if (user.getEnrolledCourses().contains(course))
            return;

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

    /**
     * Removes javaJson course from user's list of courses then updates the json file and the users hashmap.
     *
     * @param course the course which the user removes.
     * @param user   the user to remove the course for.
     */
    public static void dropCourse(Course course, Student user) {
        // check if user is not enrolled
        if (!user.getEnrolledCourses().contains(course))
            return;

        for (JsonElement jsonElement : usersJson) {
            JsonObject userToEdit = jsonElement.getAsJsonObject();
            String username = userToEdit.getAsJsonPrimitive(USERNAME_KEY).getAsString();
            if (username.equals(user.getUsername())) {
                System.out.println("Got this username: " + username);
                JsonArray enrolledCoursesJson = userToEdit.getAsJsonArray(ENROLLED_COURSES_KEY);
                enrolledCoursesJson.remove(parser.parse(gson.toJson(course)));
            }
        }
        updateJsonFile();
        initJsonDatabase();
        loadUsersFromJsonDatabase();
        SessionManager.setCurrentUser(users.get(user.getUsername()));
    }

    /**
     * Checks the database to see if the user is in the database and also check if the password passed to it
     * matches the user's password.
     *
     * @param username the username to be used as the key to the hashmap.
     * @param password the password to be checked against the user's  password which is stored in the database.
     * @return
     */
    public static boolean checkUser(String username, String password) {
        return (users.get(username) != null && users.get(username).getPassword().equals(password));
    }


    /**
     * Updates the json file by writing to it all the usersJson array in json format.
     */
    private static void updateJsonFile() {
        try (BufferedWriter writer = new BufferedWriter(new PrintWriter(jsonFile))) {
            gson.toJson(usersJson, writer);
        } catch (IOException e) {
            e.printStackTrace();
            ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
        }
    }

    public static void printUsers() {
        for (Map.Entry<String, User> s : users.entrySet()) {
            System.out.println("Username: " + s.getValue().getUsername());
            System.out.println("Email: " + s.getValue().getEmail());
            System.out.println("Password: " + s.getValue().getPassword());
            if (s instanceof Student) {
                Student st = (Student) s;
                System.out.println("Enrolled courses: ");
                for (Course course : st.getEnrolledCourses()) {
                    System.out.println(course.getName());
                }
            }
            System.out.println("----------------------------------------------------");
        }
    }

    public static void printTutorialsName() {
        for (Map.Entry<String, Tutorial> s : javaTutorials.entrySet())
            System.out.printf("%s\n%s\n", s.getKey(), s.getValue().getName());
    }

}