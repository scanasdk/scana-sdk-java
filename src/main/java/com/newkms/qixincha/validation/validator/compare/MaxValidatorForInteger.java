
package com.newkms.qixincha.validation.validator.compare;


import com.newkms.qixincha.validation.limitation.Max;

public class MaxValidatorForInteger extends AbstractMaxValidator<Integer> {

    public MaxValidatorForInteger(Max max) {
        super(max);
    }

    @Override
    protected int compare(Integer number) {
        return ((Long) number.longValue()).compareTo(maxValue);
    }
}
