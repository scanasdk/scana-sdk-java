

package com.newkms.qixincha.utils;

import java.lang.reflect.Array;

public class ArrayUtils {
    /**
     * 校验是否空列表
     *
     * @param array
     * @return
     */

    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    /**
     * 获取array 长度
     *
     * @param array
     * @return
     */
    public static int getLength(final Object array) {
        return (array == null) ? 0 : Array.getLength(array);
    }
}
