package com.hashlearning.gui.controllers;

import com.hashlearning.utils.SessionManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Omar on 17-Nov-16.
 */
public class LandingPageController implements Initializable{

    @FXML
    private JFXButton signOutBtn;

    @FXML
    private JFXButton assignmentsBtn;

    @FXML
    private JFXButton coursesBtn;

    @FXML
    private JFXButton myProfileBtn;

    @FXML
    private JFXButton contLearninBtn;

    @FXML
    private JFXButton browseCoursesBtn;

    @FXML
    void openAssignmentsPage(ActionEvent event) {

    }

    @FXML
    void openCoursesPage(ActionEvent event) {

    }

    @FXML
    void openMyProfilePage(ActionEvent event) {

    }

    @FXML
    void signOut(ActionEvent event) {
        try {
            SessionManager.signOut((Stage) signOutBtn.getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
