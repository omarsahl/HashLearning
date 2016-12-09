package com.hashlearning.gui.controllers;


import com.hashlearning.utils.DataManager;
import com.hashlearning.utils.ErrorHandler;
import com.hashlearning.utils.StageNavigator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
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
    void signUp(ActionEvent e) {
        //TODO checkUser the password strength

        if (notExisted(userNameTextField.getText())) {
            if (validatePassword(passwordTextField.getText(), retypePasswordTextField.getText())) {
                try {
                    DataManager.addUser(userNameTextField.getText(), emailTextField.getText(), passwordTextField.getText());
                    Stage landingStage = StageNavigator.switchStage((Stage) signUpBtn.getScene().getWindow(), "/fxml/landing_page.fxml", false);
                    landingStage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e1.toString());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Passwords doesn't match");
            }
        } else
            JOptionPane.showMessageDialog(null, "Username already taken ");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        goToLoginScreenLabel.setOnMouseClicked(event -> {
            try {
                openLoginScreen((Stage) goToLoginScreenLabel.getScene().getWindow());
            } catch (IOException e) {
                e.printStackTrace();
                ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
            }
        });
    }

    private boolean validatePassword(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    private boolean notExisted(String userName) {
        return DataManager.users.get(userName) == null;
    }

    private void openLoginScreen(Stage window) throws IOException {
        Stage logInStage = StageNavigator.switchStage(window, "/fxml/login_screen.fxml", false);
        logInStage.show();
    }
}
