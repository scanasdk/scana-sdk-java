
package com.newkms.qixincha.validation.validator.compare;

import com.newkms.qixincha.validation.limitation.Min;

import java.math.BigDecimal;

public class MinValidatorForDouble extends AbstractMinValidator<Double> {

    public MinValidatorForDouble(Min min) {
        super(min);
    }

    @Override
    protected int compare(Double number) {
        return BigDecimal.valueOf(number).compareTo(BigDecimal.valueOf(minValue));
    }
}
