
package com.newkms.qixincha.validation.validator.compare;


import com.newkms.qixincha.validation.limitation.Min;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public abstract class AbstractMinValidator<T> implements LimitationValidator<Min, T> {

    protected long minValue;

    public AbstractMinValidator(Min min) {
        this.minValue = min.value();
    }

    public boolean isValid(T value) {
        if (value == null) {
            return true;
        }

        return compare(value) >= 0;
    }

    protected abstract int compare(T number);
}
