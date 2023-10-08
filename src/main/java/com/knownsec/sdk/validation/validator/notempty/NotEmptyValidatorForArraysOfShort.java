
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfShort implements LimitationValidator<NotEmpty, short[]> {

    @Override
    public boolean isValid(short[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
