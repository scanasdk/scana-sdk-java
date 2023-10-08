
package com.knownsec.sdk.validation.validator.compare;

import com.knownsec.sdk.validation.limitation.Min;

import java.math.BigDecimal;

public class MinValidatorForBigDecimal extends AbstractMinValidator<BigDecimal> {

    public MinValidatorForBigDecimal(Min min) {
        super(min);
    }

    @Override
    protected int compare(BigDecimal number) {
        return number.compareTo(BigDecimal.valueOf(minValue));
    }
}
