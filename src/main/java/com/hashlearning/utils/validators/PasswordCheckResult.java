package com.hashlearning.utils.validators;

public class PasswordCheckResult {

    private boolean isValid;
    private String message;

    public PasswordCheckResult(){

    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
