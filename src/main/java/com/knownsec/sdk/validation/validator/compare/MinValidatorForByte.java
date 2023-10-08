
package com.knownsec.sdk.validation.validator.compare;

import com.knownsec.sdk.validation.limitation.Min;

public class MinValidatorForByte extends AbstractMinValidator<Byte> {

    public MinValidatorForByte(Min min) {
        super(min);
    }

    @Override
    protected int compare(Byte number) {
        return ((Long) number.longValue()).compareTo(minValue);
    }
}
