package com.fire_app.fire_app.util;

import javax.ws.rs.core.Response;
import java.util.List;

public class ErrorMessage {

    private String message;
    private List<String> failedValidations;

    public ErrorMessage() {

    }

    public ErrorMessage(String message, List<String> failedValidations) {
        this.message = message;

        this.failedValidations = failedValidations;
    }

    public ErrorMessage(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getFailedValidations() {
        return failedValidations;
    }

    public void setFailedValidations(List<String> failedValidations) {
        this.failedValidations = failedValidations;
    }
}
