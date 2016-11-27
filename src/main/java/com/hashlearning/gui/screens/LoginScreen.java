package com.hashlearning.gui.screens;

import com.hashlearning.utils.StageInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard_page.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/listview_stylesheet.css").toExternalForm());
        StageInitializer.initializeStage(primaryStage, true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
