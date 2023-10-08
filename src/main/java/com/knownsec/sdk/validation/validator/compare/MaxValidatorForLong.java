
package com.knownsec.sdk.validation.validator.compare;


import com.knownsec.sdk.validation.limitation.Max;

public class MaxValidatorForLong extends AbstractMaxValidator<Long> {

    public MaxValidatorForLong(Max max) {
        super(max);
    }

    @Override
    protected int compare(Long number) {
        return number.compareTo(maxValue);
    }
}
