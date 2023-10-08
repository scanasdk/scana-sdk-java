package com.knownsec.sdk.validation;

import com.knownsec.sdk.exception.KnownsecValidationException;
import com.knownsec.sdk.utils.CollectionUtils;
import com.knownsec.sdk.validation.descriptor.BeanDescriptor;
import com.knownsec.sdk.validation.descriptor.BeanHierarchyDescriptor;
import com.knownsec.sdk.validation.descriptor.BeanHierarchyFactory;
import com.knownsec.sdk.validation.descriptor.LimitationDescriptor;
import com.knownsec.sdk.validation.validator.LimitationValidator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Validator {


    /**
     * 参数回归性验证
     *
     * @param obj 待验证的对象实例
     * @return
     */
    public static List<String> validate(Object obj) {
        Context context = new Context();
        try {
            validateHierarchy(obj, context);
        } catch (KnownsecValidationException e) {
            throw e;
        } catch (Exception e) {
            // should never happen
            e.printStackTrace();
        }

        return context.getValidationErrors();
    }

    /**
     * 验证对象可能是集合、map、对象等情况
     *
     * @param obj     obj
     * @param context 上下文
     */
    private static void validateHierarchy(Object obj, Context context) {
        if (obj == null) {
            return;
        }
        if (context.isValidated(obj)) {
            return;
        }
        // 防止循环引用造成的死循环
        context.addValidatedValue(obj);

        if (obj instanceof Map) {
            Map map = (Map) obj;
            for (Object key : map.keySet()) {
                validateHierarchy(key, context);
                validateHierarchy(map.get(key), context);
            }
        } else if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            for (Object item : collection) {
                validateHierarchy(item, context);
            }
        } else {
            validateHierarchyForSimple(obj, context);
        }
    }

    /**
     * 验证简单对象
     *
     * @param obj     obj
     * @param context 上下文
     */
    private static void validateHierarchyForSimple(Object obj, Context context) {
        BeanHierarchyDescriptor beanHierarchy = BeanHierarchyFactory.getBeanHierarchy(obj.getClass());
        if (beanHierarchy == null) {
            return;
        }
        for (BeanDescriptor beanDescriptor : beanHierarchy.getHierarchies()) {
            // validate 当前bean的所有待验证的字段
            context.addAllValidationErrors(validateLimitation(obj, beanDescriptor));
            // 处理当前bean中字段是关联对象的所有字段
            List<Field> validAnnotatedFields = beanDescriptor.getValidAnnotatedFields();
            if (CollectionUtils.isEmpty(validAnnotatedFields)) {
                continue;
            }

            for (Field field : validAnnotatedFields) {
                Object fieldValue;
                try {
                    fieldValue = field.get(obj);
                } catch (IllegalAccessException e) {
                    // should never happen
                    e.printStackTrace();
                    continue;
                }
                validateHierarchy(fieldValue, context);
            }
        }
    }

    /**
     * 验证所有待验证的对象
     *
     * @param obj            obj
     * @param beanDescriptor bean描述符
     * @return {@link List}<{@link String}>
     */
    private static List<String> validateLimitation(Object obj, BeanDescriptor beanDescriptor) {
        List<String> violations = new ArrayList<>();
        Map<Field, List<LimitationDescriptor>> limitationDescriptorsMap = beanDescriptor.getLimitationDescriptors();
        for (Map.Entry<Field, List<LimitationDescriptor>> entry : limitationDescriptorsMap.entrySet()) {
            Field field = entry.getKey();
            List<LimitationDescriptor> limitationDescriptors = entry.getValue();
            Object fieldValue;
            try {
                fieldValue = field.get(obj);
            } catch (IllegalAccessException e) {
                // should never happen
                e.printStackTrace();
                continue;
            }

            for (LimitationDescriptor limitationDescriptor : limitationDescriptors) {
                LimitationValidator limitationValidator = limitationDescriptor.getLimitationValidator();
                if (limitationValidator == null) {
                    continue;
                }
                boolean valid = limitationValidator.isValid(fieldValue);
                if (!valid) {
                    violations.add(Formatter.format(limitationDescriptor));
                }
            }

        }
        return violations;
    }
}
