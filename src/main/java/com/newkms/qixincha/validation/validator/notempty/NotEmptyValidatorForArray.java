
package com.newkms.qixincha.validation.validator.notempty;



import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArray implements LimitationValidator<NotEmpty, Object[]> {

    @Override
    public boolean isValid(Object[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
