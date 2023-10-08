
package com.knownsec.sdk.validation.validator.compare;


import com.knownsec.sdk.validation.limitation.Max;

public class MaxValidatorForByte extends AbstractMaxValidator<Byte> {

    public MaxValidatorForByte(Max max) {
        super(max);
    }

    @Override
    protected int compare(Byte number) {
        return ((Long) number.longValue()).compareTo(maxValue);
    }
}
