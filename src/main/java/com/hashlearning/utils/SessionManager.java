package com.hashlearning.utils;

import com.hashlearning.models.User;
import javafx.stage.Stage;

import java.io.IOException;


public class SessionManager {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SessionManager.currentUser = currentUser;
    }


    public static void signOut(Stage currentStage) throws IOException {
        StageNavigator.switchStage(currentStage, "/fxml/login_screen.fxml", false, "textfield_stylesheet.css").show();
    }

}
