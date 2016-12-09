package com.hashlearning.utils.validators;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

/**
 * Created by Omar on 09-Dec-16
 */
public class PasswordConfirmationMismatchValidator extends ValidatorBase {

    private JFXPasswordField passwordField;

    public PasswordConfirmationMismatchValidator(JFXPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl)
            evalTextInputField();
    }

    private void evalTextInputField() {
        TextInputControl passwordConfirmationTextField = (TextInputControl) srcControl.get();
        if (passwordConfirmationTextField.getText() == null || passwordConfirmationTextField.getText().equals("")
                || !passwordConfirmationTextField.getText().equals(passwordField.getText())) {
            hasErrors.set(true);
        }
        else hasErrors.set(false);
    }

}