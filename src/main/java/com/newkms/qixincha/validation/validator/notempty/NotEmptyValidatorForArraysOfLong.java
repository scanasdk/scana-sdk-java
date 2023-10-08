
package com.newkms.qixincha.validation.validator.notempty;


import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfLong implements LimitationValidator<NotEmpty, long[]> {

    @Override
    public boolean isValid(long[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
