package com.knownsec.sdk.validation.descriptor;



import com.knownsec.sdk.utils.ArrayUtils;
import com.knownsec.sdk.validation.limitation.Limitation;
import com.knownsec.sdk.validation.limitation.Valid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class BeanDescriptor {

    /**
     * 当前class所有需要验证的字段的描述信息集合
     */
    private Map<Field, List<LimitationDescriptor>> limitationDescriptorMap;

    private Class<?> beanClass;

    private List<Field> validAnnotatedFields;

    private BeanDescriptor(Class clazz, Map<Field, List<LimitationDescriptor>> limitationDescriptorMap, List<Field> validAnnotatedFields) {
        this.beanClass = clazz;
        this.limitationDescriptorMap = limitationDescriptorMap;
        this.validAnnotatedFields = validAnnotatedFields;
    }

    /**
     * 创建bean的描述类
     *
     * @param clazz clazz
     * @return {@link BeanDescriptor}
     */
    public static BeanDescriptor createBeanDescriptor(Class clazz) {
        Map<Field, List<LimitationDescriptor>> fieldMap = new HashMap<>();
        Set<Field> validFieldSet = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) || field.isSynthetic()) {
                continue;
            }
            Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            if (ArrayUtils.isEmpty(declaredAnnotations)) {
                continue;
            }
            makeAccessField(field);

            List<LimitationDescriptor> limitationDescriptors = new ArrayList<>();
            for (Annotation annotation : declaredAnnotations) {
                if (annotation.annotationType() == Valid.class) {
                    validFieldSet.add(field);
                }

                if (annotation.annotationType().getAnnotation(Limitation.class) != null) {
                    limitationDescriptors.add(new LimitationDescriptor(annotation, field));
                }

            }
            if (limitationDescriptors.size() == 0) {
                continue;
            }

            fieldMap.put(field, limitationDescriptors);
        }

        if (fieldMap.size() == 0) {
            fieldMap = Collections.emptyMap();
        }

        List<Field> validFields = new ArrayList<>(validFieldSet);
        if (validFields.size() == 0) {
            validFields = Collections.emptyList();
        }

        return new BeanDescriptor(clazz, Collections.unmodifiableMap(fieldMap), Collections.unmodifiableList(validFields));
    }

    private static Field makeAccessField(Field field) {
        field.setAccessible(true);
        return field;
    }

    public List<LimitationDescriptor> getLimitationDescriptors(Field field) {
        return limitationDescriptorMap.get(field);
    }

    public Map<Field, List<LimitationDescriptor>> getLimitationDescriptors() {
        return limitationDescriptorMap;
    }

    public List<Field> getValidAnnotatedFields() {
        return validAnnotatedFields;
    }
}
