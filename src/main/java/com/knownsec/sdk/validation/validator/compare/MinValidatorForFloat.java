
package com.knownsec.sdk.validation.validator.compare;

import com.knownsec.sdk.validation.limitation.Min;

import java.math.BigDecimal;

public class MinValidatorForFloat extends AbstractMinValidator<Float> {

    public MinValidatorForFloat(Min min) {
        super(min);
    }

    @Override
    protected int compare(Float number) {
        return BigDecimal.valueOf(number).compareTo(BigDecimal.valueOf(minValue));
    }
}
