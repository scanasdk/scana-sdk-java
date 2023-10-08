
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfByte implements LimitationValidator<NotEmpty, byte[]> {

    public boolean isValid(byte[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
