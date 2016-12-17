package com.hashlearning.utils;

import com.google.gson.*;
import com.hashlearning.gui.custom_views.MaterialDialog;
import com.hashlearning.models.Course;
import com.hashlearning.models.Tutorial;
import com.hashlearning.models.User;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
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

    public static HashMap<String, User> users;
    public static HashMap<String,Tutorial> javaToutrials; //name = name of the toutrial.
    public static JsonArray usersJson;
    public static JsonArray toutrialJson ;
    private static JsonParser parser;
    private static JsonParser parser1;

    private static Gson gson;

    private static Gson gson1;
    private static File jsonFile;
    private static File jsonTutorialFile;
    private static BufferedWriter writer;
    private static FileInputStream fis;

    static {
        parser = new JsonParser();

        parser1 = new JsonParser();
        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson1 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    }

    /**
     * Initializes the Json Database by getting the users.json file and parsing it and then filling the users json array.
     */
    public static void initJsonDatabase()  {

        File testFile = new File(FileSystemView.getFileSystemView().getHomeDirectory(), "users.json");
        File toutrialFile = new File(FileSystemView.getFileSystemView().getHomeDirectory(),"java.json");
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
        //Toutrial
        if(toutrialFile.exists()){
            jsonTutorialFile=toutrialFile;
            try {
                fis = new FileInputStream(jsonTutorialFile);
                byte[] data = new byte[(int) jsonTutorialFile.length()];
                fis.read(data);
                String json = new String(data, "UTF-8");
                toutrialJson = parser1.parse(json).getAsJsonArray();
            }
            catch (IOException e){
                e.printStackTrace();
                ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
            }
            finally {
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }//if
        else {
            MaterialDialog.showDialog(Alert.AlertType.ERROR, "Couldn't find \"java.json\"", "Couldn't find \"java.json\"", "check this out!");
            Platform.exit();

        }
    }


    /**
     * Loads the users in users json array to the users Hashmap.
     */
    public static void loadUsersFromJsonDatabase() {

        users = new HashMap<String, User>();
        for (JsonElement jsonElement : usersJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            User user = gson.fromJson(jsonObject, User.class);
            users.put(user.getUsername(), user);
        }
    }

    /**
     * Loads the java tutorials in tutorial json array to the java Hashmap.
     */
    public static void javaTutorialsFromJsonDatabase() {

        javaToutrials = new HashMap<String, Tutorial>();
        for (JsonElement jsonElement : toutrialJson) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Tutorial tutorial = gson.fromJson(jsonObject, Tutorial.class);
            javaToutrials.put(tutorial.getName(), tutorial); //adding the name and the toutrial object to the HashMap.
        }
    }

    /**
     * Adds a new user to the database, then updates the json file and also the users hashmap.
     * @param username new user's username.
     * @param email new user's email.
     * @param password new user's password
     */
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


    /**
     * Enrolls a user to a course, then updates the json file and the users hashmap.
     * @param course the course which the user has to be enrolled in.
     * @param user the user to be enrolled.
     */
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


    /**
     * Checks the database to see if the user is in the database and also check if the password passed to it
     * matches the user's password.
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
    public static void printToutrialsName(){
        for(Map.Entry<String,Tutorial>s:javaToutrials.entrySet()){
            System.out.println(s.getKey());
            System.out.println(s.getValue().getName());
        }
    }

}
