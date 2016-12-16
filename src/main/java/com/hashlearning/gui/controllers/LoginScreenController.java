package com.hashlearning.gui.controllers;

import com.hashlearning.gui.custom_views.MaterialDialog;
import com.hashlearning.utils.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.hashlearning.utils.DatabaseManager.users;

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
    private void logIn(ActionEvent event) throws IOException {
        Audio.playOnClick(this);

        String username = emailTextField.getText();
        String password = passwordTextField.getText();

        if (!passwordTextField.validate() || !emailTextField.validate()) {
            return;
        }

        if (DatabaseManager.checkUser(username, password)) {
            SessionManager.setCurrentUser(users.get(username)); // save the user as the current user.
            try {
                Stage landingStage = StageNavigator.switchStage((Stage) logInBtn.getScene().getWindow(), "/fxml/landing_page.fxml", false);
                landingStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
            }
        } else {
            MaterialDialog.showDialog(AlertType.ERROR, "Error signing in", "Error signing in!",
                    "Either this username doesn't exist or you entered an incorrect password.");
        }


    }

    private void openSignUpScreen(Stage window) throws IOException {
        Stage signUpStage = StageNavigator.switchStage(window, "/fxml/signup_screen.fxml", false, "textfield_stylesheet.css");
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

    /**
     * Login on hitting Enter key
     *
     * @param keyEvent
     * @throws IOException
     */
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER)
            logIn(new ActionEvent(logInBtn, logInBtn));
    }
}
