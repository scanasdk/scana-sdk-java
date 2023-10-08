
package com.knownsec.sdk.validation.validator.compare;

import com.knownsec.sdk.validation.limitation.Min;

import java.math.BigDecimal;

public class MinValidatorForCharSequence extends AbstractMinValidator<CharSequence> {

    public MinValidatorForCharSequence(Min min) {
        super(min);
    }

    @Override
    protected int compare(CharSequence number) {
        try {
            return new BigDecimal(number.toString()).compareTo(BigDecimal.valueOf(minValue));
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
}
