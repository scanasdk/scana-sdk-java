
package com.newkms.qixincha.validation.validator.compare;


import com.newkms.qixincha.validation.limitation.Max;

public class MaxValidatorForLong extends AbstractMaxValidator<Long> {

    public MaxValidatorForLong(Max max) {
        super(max);
    }

    @Override
    protected int compare(Long number) {
        return number.compareTo(maxValue);
    }
}
