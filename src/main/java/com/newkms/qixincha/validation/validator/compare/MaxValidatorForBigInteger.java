
package com.newkms.qixincha.validation.validator.compare;

import com.newkms.qixincha.validation.limitation.Max;

import java.math.BigInteger;

public class MaxValidatorForBigInteger extends AbstractMaxValidator<BigInteger> {

    public MaxValidatorForBigInteger(Max max) {
        super(max);
    }

    @Override
    protected int compare(BigInteger number) {
        return number.compareTo(BigInteger.valueOf(maxValue));
    }
}
