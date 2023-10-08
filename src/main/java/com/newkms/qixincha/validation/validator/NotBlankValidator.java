package com.newkms.qixincha.validation.validator;

import com.newkms.qixincha.utils.StringUtils;
import com.newkms.qixincha.validation.limitation.NotBlank;

public class NotBlankValidator implements LimitationValidator<NotBlank, CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return StringUtils.isNotBlank(value);
    }
}