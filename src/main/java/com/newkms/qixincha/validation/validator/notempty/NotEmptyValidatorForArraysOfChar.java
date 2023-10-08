
package com.newkms.qixincha.validation.validator.notempty;



import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfChar implements LimitationValidator<NotEmpty, char[]> {

    @Override
    public boolean isValid(char[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
