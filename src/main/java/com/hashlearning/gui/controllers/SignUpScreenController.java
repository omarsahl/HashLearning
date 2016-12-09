package com.hashlearning.gui.controllers;


import com.hashlearning.gui.custom_views.MaterialDialog;
import com.hashlearning.utils.DataManager;
import com.hashlearning.utils.ErrorHandler;
import com.hashlearning.utils.SessionManager;
import com.hashlearning.utils.StageNavigator;
import com.hashlearning.utils.validators.PasswordConfirmationMismatchValidator;
import com.hashlearning.utils.validators.PasswordStrengthValidator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.hashlearning.utils.DataManager.users;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSignUpInputUI();

        goToLoginScreenLabel.setOnMouseClicked(event -> {
            try {
                openLoginScreen((Stage) goToLoginScreenLabel.getScene().getWindow());
            } catch (IOException e) {
                e.printStackTrace();
                ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
            }
        });
    }

    @FXML
    void signUp(ActionEvent e) {

        if (!userNameTextField.validate() || !emailTextField.validate() || !passwordTextField.validate() ||
                !retypePasswordTextField.validate()) {
            return;
        }

        if (notExisted(userNameTextField.getText())) {
            try {
                DataManager.addUser(userNameTextField.getText(), emailTextField.getText(), passwordTextField.getText());
                SessionManager.setCurrentUser(users.get(userNameTextField.getText()));
                Stage landingStage = StageNavigator.switchStage((Stage) signUpBtn.getScene().getWindow(), "/fxml/landing_page.fxml", false);
                landingStage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
                ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e1.toString());
            }

        } else {
            MaterialDialog.showDialog(Alert.AlertType.ERROR, "Error signing up","Error signing up!", "The username you entered is already taken, please try again with a different one.");
        }

    }

    private void initializeSignUpInputUI() {

        RequiredFieldValidator usernameValidator = new RequiredFieldValidator();
        RequiredFieldValidator emailValidator = new RequiredFieldValidator();
        PasswordStrengthValidator passwordValidator = new PasswordStrengthValidator();
        PasswordConfirmationMismatchValidator retypePasswordValidator = new PasswordConfirmationMismatchValidator(passwordTextField);


        usernameValidator.setMessage("Username can't be empty");
        userNameTextField.getValidators().add(usernameValidator);
        userNameTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                userNameTextField.validate();
            }
        });

        emailValidator.setMessage("Email Can't be empty");
        emailTextField.getValidators().add(emailValidator);
        emailTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                emailTextField.validate();
            }
        });

        passwordValidator.setMessage("Password must contain at least 8 characters");
        passwordTextField.getValidators().add(passwordValidator);
        passwordTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                passwordTextField.validate();
            }
        });

        retypePasswordValidator.setMessage("Doesn't match the password above");
        retypePasswordTextField.getValidators().add(retypePasswordValidator);
        retypePasswordTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                retypePasswordTextField.validate();
            }
        });

    }

    private boolean notExisted(String userName) {
        return DataManager.users.get(userName) == null;
    }

    private void openLoginScreen(Stage window) throws IOException {
        Stage logInStage = StageNavigator.switchStage(window, "/fxml/login_screen.fxml", false, "textfield_stylesheet.css");
        logInStage.show();
    }
}
