
package com.newkms.qixincha.validation.validator.notempty;



import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public class NotEmptyValidatorForCharSequence implements LimitationValidator<NotEmpty, CharSequence> {

    @Override
    public boolean isValid(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        return charSequence.length() > 0;
    }
}
