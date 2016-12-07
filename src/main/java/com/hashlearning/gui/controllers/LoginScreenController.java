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
        //printing the Encrypted.
        String password = ( passwordTextField.getText());//Encrypt.encrypt
        if(DataManager.validate(userName,password))
        { try {
            Stage landingStage = StageNavigator.switchStage((Stage) logInBtn.getScene().getWindow(),"/fxml/landing_page.fxml",false);
            landingStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            ErrorHandler.showErrorDialog(ErrorHandler.DEFAULT_MESSAGE, e.getMessage());
        }
        }
        else {
            ErrorHandler.showErrorDialog("Check your Password or Your Username","");
        }

    }


    private void openSignUpScreen(Stage window) throws IOException {
        Stage signUpStage = StageNavigator.switchStage(window, "/fxml/signup_screen.fxml", true);
        signUpStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpLabel.setOnMouseClicked(event -> {
            try {
                openSignUpScreen((Stage) signUpLabel.getScene().getWindow());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
