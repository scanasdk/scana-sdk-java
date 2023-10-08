
package com.newkms.qixincha.validation.validator.notempty;


import com.newkms.qixincha.validation.limitation.NotEmpty;
import com.newkms.qixincha.validation.validator.LimitationValidator;

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
