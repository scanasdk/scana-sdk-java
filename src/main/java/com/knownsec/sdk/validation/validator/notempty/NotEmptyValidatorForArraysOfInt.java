
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfInt implements LimitationValidator<NotEmpty, int[]> {

    @Override
    public boolean isValid(int[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
