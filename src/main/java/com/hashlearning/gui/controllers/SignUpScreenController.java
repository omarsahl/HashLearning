package com.hashlearning.gui.controllers;


import com.hashlearning.utils.StageNavigator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpScreenController implements Initializable {

    @FXML
    private JFXTextField userNameTextField;

    @FXML
    private JFXButton signUpBtn;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXPasswordField retypePasswordTextField;

    @FXML
    private Label goToLoginScreenLabel;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    void signUp(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        goToLoginScreenLabel.setOnMouseClicked(event -> {
            try {
                openLoginScreen((Stage) goToLoginScreenLabel.getScene().getWindow());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void openLoginScreen(Stage window) throws IOException {
        Stage logInStage = StageNavigator.switchStage(window, "/fxml/login_screen.fxml", false);
        logInStage.show();
    }
}
