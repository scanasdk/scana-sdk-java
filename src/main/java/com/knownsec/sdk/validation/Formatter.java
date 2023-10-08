package com.knownsec.sdk.validation;

import com.knownsec.sdk.utils.ArrayUtils;
import com.knownsec.sdk.utils.StringUtils;
import com.knownsec.sdk.validation.descriptor.LimitationDescriptor;

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
