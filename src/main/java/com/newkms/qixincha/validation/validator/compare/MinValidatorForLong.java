
package com.newkms.qixincha.validation.validator.compare;

import com.newkms.qixincha.validation.limitation.Min;

public class MinValidatorForLong extends AbstractMinValidator<Long> {

    public MinValidatorForLong(Min min) {
        super(min);
    }

    @Override
    protected int compare(Long number) {
        return number.compareTo(minValue);
    }
}
