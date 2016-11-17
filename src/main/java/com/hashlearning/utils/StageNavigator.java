package com.hashlearning.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class StageNavigator {

    /**
     * This method creates a new stage containing a scene which contains a scene graph from a provided fxml file.
     *
     * @param currentWindow: the current window which needs to be closed.
     * @param fxml: the fxml file name for the new stage.
     * @return new stage.
     * @throws IOException
     */
    public static Stage switchStage(Stage currentWindow, String fxml) throws IOException {
        Parent root = FXMLLoader.load(StageNavigator.class.getResource(fxml));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        StageInitializer.initialize(stage);
        stage.setScene(scene);
        currentWindow.close();
        return stage;
    }

}
