
package com.knownsec.sdk.validation.validator.compare;


import com.knownsec.sdk.validation.limitation.Max;

public class MaxValidatorForInteger extends AbstractMaxValidator<Integer> {

    public MaxValidatorForInteger(Max max) {
        super(max);
    }

    @Override
    protected int compare(Integer number) {
        return ((Long) number.longValue()).compareTo(maxValue);
    }
}
