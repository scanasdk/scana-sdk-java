
package com.newkms.qixincha.validation.validator.notempty;



import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfInt implements LimitationValidator<NotEmpty, int[]> {

    @Override
    public boolean isValid(int[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
