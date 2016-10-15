package br.com.bruno.SpringBootAngularJS.Validation;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ValidationError {
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> errors = new HashMap<>();
    private final String errorMessage;
    
    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public void addValidationError(String fieldError, String message) {
        errors.put(fieldError, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
