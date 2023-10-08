
package com.newkms.qixincha.validation.validator.compare;

import com.newkms.qixincha.validation.limitation.Min;

public class MinValidatorForByte extends AbstractMinValidator<Byte> {

    public MinValidatorForByte(Min min) {
        super(min);
    }

    @Override
    protected int compare(Byte number) {
        return ((Long) number.longValue()).compareTo(minValue);
    }
}
