
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public class NotEmptyValidatorForCharSequence implements LimitationValidator<NotEmpty, CharSequence> {

    @Override
    public boolean isValid(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        return charSequence.length() > 0;
    }
}
