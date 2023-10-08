package com.knownsec.sdk.utils;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}
