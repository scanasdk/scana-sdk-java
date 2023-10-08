
package com.newkms.qixincha.validation.validator.size;


import com.newkms.qixincha.exception.KnownsecValidationException;
import com.newkms.qixincha.validation.limitation.Size;
import com.newkms.qixincha.validation.validator.LimitationValidator;

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
