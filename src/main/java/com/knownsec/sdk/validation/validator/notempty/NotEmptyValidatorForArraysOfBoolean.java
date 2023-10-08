
package com.knownsec.sdk.validation.validator.notempty;


import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;


public class NotEmptyValidatorForArraysOfBoolean implements LimitationValidator<NotEmpty, boolean[]> {

    @Override
    public boolean isValid(boolean[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
