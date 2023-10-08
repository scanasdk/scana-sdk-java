
package com.knownsec.sdk.validation.validator.compare;

import com.knownsec.sdk.validation.limitation.Min;

public class MinValidatorForLong extends AbstractMinValidator<Long> {

    public MinValidatorForLong(Min min) {
        super(min);
    }

    @Override
    protected int compare(Long number) {
        return number.compareTo(minValue);
    }
}
