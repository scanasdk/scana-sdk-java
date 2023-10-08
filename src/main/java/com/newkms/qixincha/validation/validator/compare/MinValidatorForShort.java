
package com.newkms.qixincha.validation.validator.compare;

import com.newkms.qixincha.validation.limitation.Min;

public class MinValidatorForShort extends AbstractMinValidator<Short> {

    public MinValidatorForShort(Min min) {
        super(min);
    }

    @Override
    protected int compare(Short number) {
        return ((Long) number.longValue()).compareTo(minValue);
    }
}
