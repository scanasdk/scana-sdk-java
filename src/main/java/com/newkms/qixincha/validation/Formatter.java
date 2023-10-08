package com.newkms.qixincha.validation;

import com.newkms.qixincha.utils.ArrayUtils;
import com.newkms.qixincha.utils.StringUtils;
import com.newkms.qixincha.validation.descriptor.LimitationDescriptor;

import java.lang.reflect.Field;
import java.util.Optional;

public class Formatter {

    public static String format(LimitationDescriptor descriptor) {
        String message = descriptor.getMessage();
        String[] params = StringUtils.substringsBetween(message, "{", "}");
        if (!ArrayUtils.isEmpty(params)) {
            for (String param : params) {
                Object attribute = descriptor.getAttribute(param);
                if (attribute == null) {
                    continue;
                }
                message = message.replace("{" + param + "}", attribute.toString());
            }
        }
        String fieldName = Optional.ofNullable(descriptor.getField()).map(Field::getName).orElse("");

        return fieldName + " is invalid, " + message.trim();
    }

}
