
package com.knownsec.sdk.validation.validator;


import com.knownsec.sdk.validation.limitation.NotNull;

public class NotNullValidator implements LimitationValidator<NotNull, Object> {

    @Override
    public boolean isValid(Object value) {
        return value != null;
    }
}
