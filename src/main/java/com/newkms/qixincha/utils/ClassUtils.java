package com.newkms.qixincha.utils;


import com.newkms.qixincha.exception.KnownsecValidationException;
import com.newkms.qixincha.validation.validator.LimitationValidator;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;

public class ClassUtils {

    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPES;

    static {
        Map<Class<?>, Class<?>> tmpMap = new HashMap<>(9);

        tmpMap.put(boolean.class, Boolean.class);
        tmpMap.put(char.class, Character.class);
        tmpMap.put(double.class, Double.class);
        tmpMap.put(float.class, Float.class);
        tmpMap.put(long.class, Long.class);
        tmpMap.put(int.class, Integer.class);
        tmpMap.put(short.class, Short.class);
        tmpMap.put(byte.class, Byte.class);
        tmpMap.put(Void.TYPE, Void.TYPE);

        PRIMITIVE_TO_WRAPPER_TYPES = Collections.unmodifiableMap(tmpMap);
    }

    /**
     * 校验是否属于创宇的类文件
     *
     * @param clazz
     * @return
     */
    public static boolean isKnownsecClass(Class clazz) {
        return clazz.getPackage() == null || !clazz.getPackage().getName().startsWith("com.newkms.qixincha");
    }

    public static List<Class> getHierarchyClass(Class clazz) {
        List<Class> classes = new ArrayList<>();

        getHierarchyClass(clazz, classes);
        return classes;
    }

    /**
     * 获取所有实现了接口列表
     *
     * @param clazz
     * @param classes
     */
    private static void getHierarchyClass(Class clazz, List<Class> classes) {
        for (Class current = clazz; current != null; current = current.getSuperclass()) {
            if (classes.contains(current)) {
                return;
            }
            // 只有父类是接口类，才将每一个实现父类的类追加进去
            if (!clazz.isInterface()) {
                classes.add(current);
            }
        }
    }

    private static Class<?> internalBoxedType(Class<?> primitiveType) {
        Class<?> wrapperType = PRIMITIVE_TO_WRAPPER_TYPES.get(primitiveType);

        if (wrapperType == null) {
            throw new KnownsecValidationException("not support primitiveType " + primitiveType.getClass().getName());
        }

        return wrapperType;
    }

    public static Type boxedType(Type type) {
        if (type instanceof Class && ((Class<?>) type).isPrimitive()) {
            return internalBoxedType((Class<?>) type);
        } else {
            return type;
        }
    }

    /**
     * 暂时只支持了class和array，不包括泛型
     *
     * @param supertype
     * @param type
     * @return
     */
    public static boolean isAssignable(Type supertype, Type type) {
        if (supertype.equals(type)) {
            return true;
        }

        if (supertype instanceof Class<?>) {
            if (type instanceof Class<?>) {
                return isClassAssignable((Class<?>) supertype, (Class<?>) type);
            }

            if (type instanceof GenericArrayType) {
                if (((Class<?>) supertype).isArray()) {
                    return isAssignable(getComponentType(supertype), getComponentType(type));
                }

                return isArraySupertype((Class<?>) supertype);
            }

            return false;
        }

        if (supertype instanceof GenericArrayType) {
            if (isArray(type)) {
                return isAssignable(getComponentType(supertype), getComponentType(type));
            }

            return false;
        }

        return false;
    }

    public static boolean isArray(Type type) {
        return (type instanceof Class<?> && ((Class<?>) type).isArray())
                || (type instanceof GenericArrayType);
    }

    /**
     * 暂时只支持了class，不包括primitive type
     *
     * @param supertype
     * @param type
     * @return
     */
    private static boolean isClassAssignable(Class<?> supertype, Class<?> type) {

        return supertype.isAssignableFrom(type);
    }

    private static boolean isArraySupertype(Class<?> type) {
        return Object.class.equals(type)
                || Cloneable.class.equals(type)
                || Serializable.class.equals(type);
    }

    public static Type getComponentType(Type type) {
        if (type instanceof Class<?>) {
            Class<?> klass = (Class<?>) type;

            return klass.isArray() ? klass.getComponentType() : null;
        }

        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }

        return null;
    }

    public static Type getArrayType(Type componentType) {

        if (componentType instanceof Class<?>) {
            return Array.newInstance((Class<?>) componentType, 0).getClass();
        }

        return genericArrayType(componentType);
    }

    public static GenericArrayType genericArrayType(final Type componentType) {
        return new GenericArrayType() {
            @Override
            public Type getGenericComponentType() {
                return componentType;
            }
        };
    }

    public static Type resolveTypes(Map<Type, Type> resolvedTypes, Type type) {
        if (type == null) {
            return null;
        } else if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            final Type returnedType = resolveTypeForClassAndHierarchy(resolvedTypes, clazz);
            if (returnedType != null) {
                return returnedType;
            }
        } else if (type instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) type;
            if (!(paramType.getRawType() instanceof Class)) {
                return null;
            }
            Class<?> rawType = (Class<?>) paramType.getRawType();

            TypeVariable<?>[] originalTypes = rawType.getTypeParameters();
            Type[] partiallyResolvedTypes = paramType.getActualTypeArguments();
            int nbrOfParams = originalTypes.length;
            for (int i = 0; i < nbrOfParams; i++) {
                resolvedTypes.put(originalTypes[i], partiallyResolvedTypes[i]);
            }

            if (rawType.equals(LimitationValidator.class)) {
                return type;
            } else {
                Type returnedType = resolveTypeForClassAndHierarchy(resolvedTypes, rawType);
                if (returnedType != null) {
                    return returnedType;
                }
            }
        }
        return null;
    }

    private static Type resolveTypeForClassAndHierarchy(Map<Type, Type> resolvedTypes, Class<?> clazz) {
        Type returnedType = resolveTypes(resolvedTypes, clazz.getGenericSuperclass());
        if (returnedType != null) {
            return returnedType;
        }
        for (Type genericInterface : clazz.getGenericInterfaces()) {
            returnedType = resolveTypes(resolvedTypes, genericInterface);
            if (returnedType != null) {
                return returnedType;
            }
        }
        return null;
    }
}
