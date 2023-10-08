
package com.newkms.qixincha.validation.validator.size;


import com.newkms.qixincha.validation.limitation.Size;

public class SizeValidatorForArray extends AbstractSizeValidator<Object[]> {

    public SizeValidatorForArray(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(Object[] value) {
        if (value == null) {
            return true;
        }
        return value.length >= min && value.length <= max;
    }
}
