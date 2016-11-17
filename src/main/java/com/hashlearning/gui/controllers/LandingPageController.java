package com.hashlearning.gui.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

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
    private ImageView rocket;

    @FXML
    private JFXButton browseCoursesBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
