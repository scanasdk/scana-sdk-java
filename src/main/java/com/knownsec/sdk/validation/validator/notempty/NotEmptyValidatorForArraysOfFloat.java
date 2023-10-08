
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfFloat implements LimitationValidator<NotEmpty, float[]> {

    @Override
    public boolean isValid(float[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
