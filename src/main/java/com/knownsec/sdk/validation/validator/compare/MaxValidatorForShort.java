
package com.knownsec.sdk.validation.validator.compare;


import com.knownsec.sdk.validation.limitation.Max;

public class MaxValidatorForShort extends AbstractMaxValidator<Short> {

    public MaxValidatorForShort(Max max) {
        super(max);
    }

    @Override
    protected int compare(Short number) {
        return ((Long) number.longValue()).compareTo(maxValue);
    }
}
