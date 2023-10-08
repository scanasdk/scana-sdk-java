
package com.newkms.qixincha.validation.validator.size;


import com.newkms.qixincha.validation.limitation.Size;

import java.util.Map;

public class SizeValidatorForMap extends AbstractSizeValidator<Map> {

    public SizeValidatorForMap(Size size) {
        super(size);
    }

    @Override
    public boolean isValid(Map map) {
        if (map == null) {
            return true;
        }
        int size = map.size();
        return size >= min && size <= max;
    }
}
