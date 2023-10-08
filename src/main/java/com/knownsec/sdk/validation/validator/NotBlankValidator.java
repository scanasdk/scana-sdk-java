package com.knownsec.sdk.validation.validator;

import com.knownsec.sdk.utils.StringUtils;
import com.knownsec.sdk.validation.limitation.NotBlank;

public class NotBlankValidator implements LimitationValidator<NotBlank, CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return StringUtils.isNotBlank(value);
    }
}