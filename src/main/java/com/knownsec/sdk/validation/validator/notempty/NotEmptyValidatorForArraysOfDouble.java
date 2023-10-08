
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfDouble implements LimitationValidator<NotEmpty, double[]> {

    @Override
    public boolean isValid(double[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
