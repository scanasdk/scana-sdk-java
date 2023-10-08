
package com.knownsec.sdk.validation.validator.size;


import com.knownsec.sdk.validation.limitation.Size;

public class SizeValidatorForArraysOfInt extends AbstractSizeValidator<int[]> {

    public SizeValidatorForArraysOfInt(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(int[] array) {
        if (array == null) {
            return true;
        }
        return array.length >= min && array.length <= max;
    }
}
