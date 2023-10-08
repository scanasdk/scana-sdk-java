package com.newkms.qixincha.validation.validator.compare;


import com.newkms.qixincha.validation.limitation.Max;

import java.math.BigDecimal;

public class MaxValidatorForFloat extends AbstractMaxValidator<Float> {

    public MaxValidatorForFloat(Max max) {
        super(max);
    }

    @Override
    protected int compare(Float number) {
        return BigDecimal.valueOf(number).compareTo(BigDecimal.valueOf(maxValue));
    }
}
