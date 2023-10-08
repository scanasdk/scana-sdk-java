
package com.newkms.qixincha.validation.validator.compare;

import com.newkms.qixincha.validation.limitation.Max;

import java.math.BigDecimal;

public class MaxValidatorForBigDecimal extends AbstractMaxValidator<BigDecimal> {

    public MaxValidatorForBigDecimal(Max max) {
        super(max);
    }

    @Override
    protected int compare(BigDecimal number) {
        return number.compareTo(BigDecimal.valueOf(maxValue));
    }
}
