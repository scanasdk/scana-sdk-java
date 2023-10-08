
package com.newkms.qixincha.validation.validator.notempty;



import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArraysOfByte implements LimitationValidator<NotEmpty, byte[]> {

    public boolean isValid(byte[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
