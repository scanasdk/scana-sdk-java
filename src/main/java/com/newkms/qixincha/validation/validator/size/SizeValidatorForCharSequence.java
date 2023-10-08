
package com.newkms.qixincha.validation.validator.size;


import com.newkms.qixincha.validation.limitation.Size;

public class SizeValidatorForCharSequence extends AbstractSizeValidator<CharSequence> {

    public SizeValidatorForCharSequence(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(CharSequence array) {
        if (array == null) {
            return true;
        }
        int length = array.length();
        return length >= min && length <= max;
    }
}
