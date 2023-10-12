package com.newkms.qixincha.validation.validator;


import com.newkms.qixincha.exception.KnownsecValidationException;
import com.newkms.qixincha.utils.ClassUtils;
import com.newkms.qixincha.validation.limitation.*;
import com.newkms.qixincha.validation.validator.compare.*;
import com.newkms.qixincha.validation.validator.notempty.*;
import com.newkms.qixincha.validation.validator.size.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理所有的validator，并根据注解和field的类型new合适的validator
 */
public class LimitationValidatorManager {

    private static final Map<Type, List<Class<? extends LimitationValidator>>> ANNOTATION_VALIDATOR_CLASS_MAP;

    private static final Map<Class<? extends LimitationValidator>, LimitationValidator> validatorMap = new ConcurrentHashMap<>();

    static {
        ANNOTATION_VALIDATOR_CLASS_MAP = new HashMap<>();
        ANNOTATION_VALIDATOR_CLASS_MAP.put(NotBlank.class, createValidatorClasses(NotBlankValidator.class));
        ANNOTATION_VALIDATOR_CLASS_MAP.put(NotNull.class, createValidatorClasses(NotNullValidator.class));
        ANNOTATION_VALIDATOR_CLASS_MAP.put(Length.class, createValidatorClasses(LengthValidator.class));
        ANNOTATION_VALIDATOR_CLASS_MAP.put(Size.class, createValidatorClasses(SizeValidatorForArray.class,
                SizeValidatorForArraysOfBoolean.class,
                SizeValidatorForArraysOfByte.class,
                SizeValidatorForArraysOfChar.class,
                SizeValidatorForArraysOfDouble.class,
                SizeValidatorForArraysOfFloat.class,
                SizeValidatorForArraysOfInt.class,
                SizeValidatorForArraysOfLong.class,
                SizeValidatorForArraysOfShort.class,
                SizeValidatorForCharSequence.class,
                SizeValidatorForCollection.class,
                SizeValidatorForMap.class));
        List<Class<? extends LimitationValidator>> put = ANNOTATION_VALIDATOR_CLASS_MAP.put(Max.class, createValidatorClasses(MaxValidatorForBigDecimal.class,
                MaxValidatorForBigInteger.class,
                MaxValidatorForByte.class,
                MaxValidatorForCharSequence.class,
                MaxValidatorForInteger.class,
                MaxValidatorForFloat.class,
                MaxValidatorForDouble.class,
                MaxValidatorForLong.class,
                MaxValidatorForShort.class));
        ANNOTATION_VALIDATOR_CLASS_MAP.put(Min.class, createValidatorClasses(MinValidatorForBigDecimal.class,
                MinValidatorForBigInteger.class,
                MinValidatorForByte.class,
                MinValidatorForCharSequence.class,
                MinValidatorForDouble.class,
                MinValidatorForFloat.class,
                MinValidatorForInteger.class,
                MinValidatorForLong.class,
                MinValidatorForShort.class));
        ANNOTATION_VALIDATOR_CLASS_MAP.put(NotEmpty.class, createValidatorClasses(NotEmptyValidatorForArray.class,
                NotEmptyValidatorForArraysOfBoolean.class,
                NotEmptyValidatorForArraysOfByte.class,
                NotEmptyValidatorForArraysOfChar.class,
                NotEmptyValidatorForArraysOfDouble.class,
                NotEmptyValidatorForArraysOfFloat.class,
                NotEmptyValidatorForArraysOfInt.class,
                NotEmptyValidatorForArraysOfLong.class,
                NotEmptyValidatorForArraysOfShort.class,
                NotEmptyValidatorForCharSequence.class,
                NotEmptyValidatorForCollection.class,
                NotEmptyValidatorForMap.class));
    }

    private static List<Class<? extends LimitationValidator>> createValidatorClasses(Class<? extends LimitationValidator>... classes) {
        return Arrays.asList(classes);
    }


    /**
     * 创建匹配对应注解的验证器
     *
     * @param fieldType  字段类型
     * @param limitation 限制
     * @return {@link LimitationValidator}
     */
    public static LimitationValidator createValidator(Type fieldType, Annotation limitation) {

        List<Class<? extends LimitationValidator>> validatorClasses = ANNOTATION_VALIDATOR_CLASS_MAP.get(limitation.annotationType());
        if (validatorClasses == null) {
            throw new KnownsecValidationException("current not support validator for " + limitation.annotationType().getName());
        }
        for (Class<? extends LimitationValidator> validatorClass : validatorClasses) {

            Type supportType = extractValidatorArgumentType(validatorClass, 1);
            if (!ClassUtils.isAssignable(supportType, fieldType)) {
                continue;
            }
            try {
                for (Constructor<?> constructor : validatorClass.getDeclaredConstructors()) {
                    if (constructor.getParameterCount() == 1 && limitation.annotationType().equals(constructor.getParameterTypes()[0])) {
                        return (LimitationValidator) constructor.newInstance(limitation);
                    } else if (constructor.getParameterCount() == 0) {
                        // validator构造器参数是0时，则validator可以复用
                        LimitationValidator limitationValidator = validatorMap.get(validatorClass);
                        if (limitationValidator != null) {
                            return limitationValidator;
                        }
                        limitationValidator = (LimitationValidator) constructor.newInstance();
                        LimitationValidator previous = validatorMap.putIfAbsent(validatorClass, limitationValidator);
                        if (previous != null) {
                            return previous;
                        }

                        return limitationValidator;
                    }
                }
            } catch (Exception e) {
                // should not happen
                e.printStackTrace();
            }
            throw new KnownsecValidationException(String.format("validator constructor invalid for limitation %s and validator %s",
                    limitation.annotationType().getName(), validatorClass.getName()));
        }
        throw new KnownsecValidationException("current not support validator for " + limitation.annotationType().getName());
    }

    public static Type extractValidatorArgumentType(Class<? extends LimitationValidator> validator, int typeArgumentIndex) {
        Map<Type, Type> resolvedTypes = new HashMap<>();
        Type validatorType = ClassUtils.resolveTypes(resolvedTypes, validator);

        Type type = ((ParameterizedType) validatorType).getActualTypeArguments()[typeArgumentIndex];
        if (type == null) {
            return null;
        } else if (type instanceof GenericArrayType) {
            type = ClassUtils.getArrayType(ClassUtils.getComponentType(type));
        }

        while (resolvedTypes.containsKey(type)) {
            type = resolvedTypes.get(type);
        }
        return type;
    }
}
