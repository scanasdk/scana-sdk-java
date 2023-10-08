package com.knownsec.sdk.validation;

import com.knownsec.sdk.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Context {

    private Set<Object> validatedValues;

    private List<String> validationErrors;

    public Context() {
        this.validatedValues = new HashSet<>();
        this.validationErrors = new ArrayList<>();
    }

    public boolean isValidated(Object value) {
        return validatedValues.contains(value);
    }

    public void addValidatedValue(Object value) {
        validatedValues.add(value);
    }

    public void addValidationError(String error) {
        validationErrors.add(error);
    }

    public void addAllValidationErrors(List<String> errors) {
        if (CollectionUtils.isEmpty(errors)) {
            return;
        }
        validationErrors.addAll(errors);
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
