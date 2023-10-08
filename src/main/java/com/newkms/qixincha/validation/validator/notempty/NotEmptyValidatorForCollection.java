
package com.newkms.qixincha.validation.validator.notempty;



import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

import java.util.Collection;

public class NotEmptyValidatorForCollection implements LimitationValidator<NotEmpty, Collection> {

    @Override
    public boolean isValid(Collection collection) {
        if (collection == null) {
            return false;
        }
        return collection.size() > 0;
    }
}
