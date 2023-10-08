
package com.newkms.qixincha.validation.validator;


import com.newkms.qixincha.validation.limitation.NotNull;

public class NotNullValidator implements LimitationValidator<NotNull, Object> {

    @Override
    public boolean isValid(Object value) {
        return value != null;
    }
}
