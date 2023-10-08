
package com.knownsec.sdk.validation.validator.compare;

import com.knownsec.sdk.validation.limitation.Max;

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
