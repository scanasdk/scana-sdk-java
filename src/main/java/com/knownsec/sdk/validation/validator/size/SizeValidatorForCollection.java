
package com.knownsec.sdk.validation.validator.size;


import com.knownsec.sdk.validation.limitation.Size;

import java.util.Collection;

public class SizeValidatorForCollection extends AbstractSizeValidator<Collection> {


    public SizeValidatorForCollection(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(Collection array) {
        if (array == null) {
            return true;
        }
        int length = array.size();
        return length >= min && length <= max;
    }
}
