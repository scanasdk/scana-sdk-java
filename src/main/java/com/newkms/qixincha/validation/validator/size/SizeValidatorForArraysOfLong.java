
package com.newkms.qixincha.validation.validator.size;

import com.newkms.qixincha.validation.limitation.Size;

public class SizeValidatorForArraysOfLong extends AbstractSizeValidator<long[]> {

    public SizeValidatorForArraysOfLong(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(long[] array) {
        if (array == null) {
            return true;
        }
        return array.length >= min && array.length <= max;
    }
}
