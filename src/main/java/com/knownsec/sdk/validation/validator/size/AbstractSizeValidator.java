
package com.knownsec.sdk.validation.validator.size;


import com.knownsec.sdk.exception.KnownsecValidationException;
import com.knownsec.sdk.validation.limitation.Size;
import com.knownsec.sdk.validation.validator.LimitationValidator;

public abstract class AbstractSizeValidator<T> implements LimitationValidator<Size, T> {

    protected int min;
    protected int max;

    public AbstractSizeValidator(Size size) {
        this.min = size.min();
        this.max = size.max();
        if (this.max < this.min) {
            throw new KnownsecValidationException("Size min must be smaller than max");
        }
    }

}
