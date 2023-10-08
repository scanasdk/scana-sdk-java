
package com.knownsec.sdk.validation.validator.notempty;


import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfLong implements LimitationValidator<NotEmpty, long[]> {

    @Override
    public boolean isValid(long[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
