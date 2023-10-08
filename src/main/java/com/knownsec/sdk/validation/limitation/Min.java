package com.knownsec.sdk.validation.limitation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Limitation
@Target({FIELD})
@Retention(RUNTIME)
public @interface Min {

    String message() default "must be greater than or equal to {value}";

    long value();

}
