
package com.newkms.qixincha.validation.validator.compare;


import com.newkms.qixincha.validation.limitation.Max;

public class MaxValidatorForByte extends AbstractMaxValidator<Byte> {

    public MaxValidatorForByte(Max max) {
        super(max);
    }

    @Override
    protected int compare(Byte number) {
        return ((Long) number.longValue()).compareTo(maxValue);
    }
}
