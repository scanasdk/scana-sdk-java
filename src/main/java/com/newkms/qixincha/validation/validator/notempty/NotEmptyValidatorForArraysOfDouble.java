
package com.newkms.qixincha.validation.validator.notempty;



import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfDouble implements LimitationValidator<NotEmpty, double[]> {

    @Override
    public boolean isValid(double[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
