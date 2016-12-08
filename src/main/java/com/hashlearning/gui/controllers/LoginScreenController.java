package com.hashlearning.gui.controllers;

import com.hashlearning.utils.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXButton logInBtn;

    @FXML
    private Label forgotPasswordLabel;

    @FXML
    private Label signUpLabel;

    @FXML
    private void logIn(ActionEvent event) {

        String userName = emailTextField.getText();

        String password = passwordTextField.getText();

        if (!passwordTextField.validate()){
            return;
        }

        if (!emailTextField.validate()){
            return;
        }

        if (DataManager.validate(userName, password)) {
            SessionManager.setCurrentStudent(userName); // save the name of the student to use it later.
            try {
                Stage landingStage = StageNavigator.switchStage((Stage) logInBtn.getScene().getWindow(), "/fxml/landing_page.fxml", false);
                landingStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
            }
        } else {

        }


    }

    private void openSignUpScreen(Stage window) throws IOException {
        Stage signUpStage = StageNavigator.switchStage(window, "/fxml/signup_screen.fxml", true);
        signUpStage.show();
    }

    private void initializeInputUI() {

        RequiredFieldValidator emailValidator = new RequiredFieldValidator();
        RequiredFieldValidator passwordValidator = new RequiredFieldValidator();

        emailValidator.setMessage("Email can't be empty");
        emailTextField.getValidators().add(emailValidator);
        emailTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                emailTextField.validate();
            }
        });

        passwordValidator.setMessage("Password Can't be empty");
        passwordTextField.getValidators().add(passwordValidator);
        passwordTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                passwordTextField.validate();
            }
        });
    }

    public void initialize(URL location, ResourceBundle resources) {
        initializeInputUI();

        signUpLabel.setOnMouseClicked(event -> {
            try {
                openSignUpScreen((Stage) signUpLabel.getScene().getWindow());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
