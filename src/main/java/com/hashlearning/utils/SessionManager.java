package com.hashlearning.utils;

import javafx.stage.Stage;

import java.io.IOException;


public class SessionManager {

    public static void signOut(Stage currentStage) throws IOException {
        StageNavigator.switchStage(currentStage, "/fxml/login_screen.fxml", false).show();
    }

}
