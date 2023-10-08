
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForArray implements LimitationValidator<NotEmpty, Object[]> {

    @Override
    public boolean isValid(Object[] array) {
        if (array == null) {
            return false;
        }
        return array.length > 0;
    }
}
