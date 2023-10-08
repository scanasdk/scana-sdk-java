
package com.newkms.qixincha.validation.validator.size;


import com.newkms.qixincha.validation.limitation.Size;

public class SizeValidatorForArraysOfChar extends AbstractSizeValidator<char[]> {

    public SizeValidatorForArraysOfChar(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(char[] array) {
        if (array == null) {
            return true;
        }
        return array.length >= min && array.length <= max;
    }
}
