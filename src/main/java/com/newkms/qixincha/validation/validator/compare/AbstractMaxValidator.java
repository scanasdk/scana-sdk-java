
package com.newkms.qixincha.validation.validator.compare;


import com.newkms.qixincha.validation.limitation.Max;
import com.newkms.qixincha.validation.validator.LimitationValidator;

public abstract class AbstractMaxValidator<T> implements LimitationValidator<Max, T> {

    protected long maxValue;

    public AbstractMaxValidator(Max max) {
        this.maxValue = max.value();
    }

    @Override
    public boolean isValid(T value) {
        if (value == null) {
            return true;
        }

        return compare(value) <= 0;
    }

    protected abstract int compare(T number);
}
