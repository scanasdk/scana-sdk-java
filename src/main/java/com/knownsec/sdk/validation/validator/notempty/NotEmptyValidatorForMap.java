
package com.knownsec.sdk.validation.validator.notempty;


import com.knownsec.sdk.validation.limitation.NotEmpty;
import com.knownsec.sdk.validation.validator.LimitationValidator;

import java.util.Map;

public class NotEmptyValidatorForMap implements LimitationValidator<NotEmpty, Map> {

    @Override
    public boolean isValid(Map map) {
        if (map == null) {
            return false;
        }
        return map.size() > 0;
    }
}
