
package com.knownsec.sdk.validation.validator.compare;


import com.knownsec.sdk.validation.limitation.Max;

import java.math.BigDecimal;

public class MaxValidatorForDouble extends AbstractMaxValidator<Double> {

    public MaxValidatorForDouble(Max max) {
        super(max);
    }

    @Override
    protected int compare(Double number) {
        return BigDecimal.valueOf(number).compareTo(BigDecimal.valueOf(maxValue));
    }
}
