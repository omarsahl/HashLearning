package com.hashlearning.gui.screens;

import com.hashlearning.utils.StageInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends Application {



    public static void main(String[] args) throws IOException {
     //  launch(args);
        HashMap<Integer,String> students = new HashMap<>();
        ClassLoader loader = LoginScreen.class.getClassLoader();
        FileInputStream fis = new FileInputStream(loader.getResource("files/students").getFile());
        BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
        bf
                .lines().map(line -> line.split("[|]"))
                .skip(1).skip(2)
                .forEach(x -> {
                    students.put(Integer.parseInt(x[0].trim()),x[1]);
                });
        for(Map.Entry <Integer,String> s :students.entrySet()){
            System.out.println(s.getKey()+" ----> "+s.getValue());
        }
    }
    // test pull request
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login_screen.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/listview_stylesheet.css").toExternalForm());
        StageInitializer.initializeStage(primaryStage, true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
