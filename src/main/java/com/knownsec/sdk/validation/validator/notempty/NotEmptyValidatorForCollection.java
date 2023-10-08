
package com.knownsec.sdk.validation.validator.notempty;



import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

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
