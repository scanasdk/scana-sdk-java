package com.newkms.qixincha.validation.validator;

import java.lang.annotation.Annotation;

public interface LimitationValidator<A extends Annotation, T> {

    boolean isValid(T value);
}
