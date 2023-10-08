
package com.newkms.qixincha.validation.validator.compare;


import com.newkms.qixincha.validation.limitation.Max;

public class MaxValidatorForShort extends AbstractMaxValidator<Short> {

    public MaxValidatorForShort(Max max) {
        super(max);
    }

    @Override
    protected int compare(Short number) {
        return ((Long) number.longValue()).compareTo(maxValue);
    }
}
