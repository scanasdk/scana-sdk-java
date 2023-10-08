
package com.newkms.qixincha.validation.validator.notempty;


import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;


public class NotEmptyValidatorForArraysOfBoolean implements LimitationValidator<NotEmpty, boolean[]> {

    @Override
    public boolean isValid(boolean[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
