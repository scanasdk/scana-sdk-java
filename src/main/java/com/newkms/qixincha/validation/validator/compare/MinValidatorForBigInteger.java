
package com.newkms.qixincha.validation.validator.compare;

import com.newkms.qixincha.validation.limitation.Min;

import java.math.BigInteger;

public class MinValidatorForBigInteger extends AbstractMinValidator<BigInteger> {

    public MinValidatorForBigInteger(Min min) {
        super(min);
    }

    @Override
    protected int compare(BigInteger number) {
        return number.compareTo(BigInteger.valueOf(minValue));
    }
}
