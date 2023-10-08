
package com.newkms.qixincha.validation.validator.notempty;



import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfShort implements LimitationValidator<NotEmpty, short[]> {

    @Override
    public boolean isValid(short[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
