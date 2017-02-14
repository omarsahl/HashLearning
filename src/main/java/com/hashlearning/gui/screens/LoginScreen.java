package com.hashlearning.gui.screens;

import com.hashlearning.utils.DatabaseManager;
import com.hashlearning.utils.StageInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen extends Application {


    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        DatabaseManager.initJsonDatabase();
        DatabaseManager.loadUsersFromJsonDatabase();
        DatabaseManager.printUsers();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login_screen.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/textfield_stylesheet.css");
        StageInitializer.initializeStage(primaryStage, false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
