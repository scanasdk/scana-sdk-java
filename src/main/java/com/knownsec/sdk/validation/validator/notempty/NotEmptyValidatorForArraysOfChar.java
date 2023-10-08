
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfChar implements LimitationValidator<NotEmpty, char[]> {

    @Override
    public boolean isValid(char[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
