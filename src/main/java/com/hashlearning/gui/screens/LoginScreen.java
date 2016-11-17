package com.hashlearning.gui.screens;

import com.hashlearning.utils.StageInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/landing_page.fxml"));
        Scene scene = new Scene(root);
        StageInitializer.initialize(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
