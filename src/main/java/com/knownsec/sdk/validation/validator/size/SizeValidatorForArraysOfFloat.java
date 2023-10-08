
package com.knownsec.sdk.validation.validator.size;

import com.knownsec.sdk.validation.limitation.Size;

public class SizeValidatorForArraysOfFloat extends AbstractSizeValidator<float[]> {

    public SizeValidatorForArraysOfFloat(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(float[] array) {
        if (array == null) {
            return true;
        }
        return array.length >= min && array.length <= max;
    }
}
