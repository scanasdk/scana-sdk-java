
package com.knownsec.sdk.validation.validator.compare;

import com.knownsec.sdk.validation.limitation.Min;

public class MinValidatorForInteger extends AbstractMinValidator<Integer> {

    public MinValidatorForInteger(Min min) {
        super(min);
    }

    @Override
    protected int compare(Integer number) {
        return ((Long) number.longValue()).compareTo(minValue);
    }
}
