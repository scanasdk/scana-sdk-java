package com.knownsec.sdk.validation.validator;


import com.knownsec.sdk.exception.KnownsecValidationException;
import com.knownsec.sdk.validation.limitation.Length;

public class LengthValidator implements LimitationValidator<Length, CharSequence> {

    private int min;
    private int max;


    public LengthValidator(Length length) {
        this.min = length.min();
        this.max = length.max();
        if (this.max < this.min) {
            throw new KnownsecValidationException("Length min must be smaller than max");
        }
    }

    @Override
    public boolean isValid(CharSequence value) {
        if (value == null) {
            return true;
        }
        int length = value.length();
        return length >= min && length <= max;
    }

}
