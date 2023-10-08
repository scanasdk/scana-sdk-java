
package com.newkms.qixincha.validation.validator.size;


import com.newkms.qixincha.validation.limitation.Size;

public class SizeValidatorForArraysOfBoolean extends AbstractSizeValidator<boolean[]> {

    public SizeValidatorForArraysOfBoolean(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(boolean[] array) {
        if (array == null) {
            return true;
        }
        return array.length >= min && array.length <= max;
    }
}
