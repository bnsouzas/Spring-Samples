package br.com.bruno.SpringBootAngularJS.Validation;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (FieldError fieldError : errors.getFieldErrors()) {
        	error.addValidationError(fieldError.getObjectName() + "." + fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        return error;
    }
}