

package com.newkms.qixincha.utils;

import com.newkms.qixincha.exception.KnownsecSdkException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * url 工具类
 */
public class UrlUtils {
    private static final String MSG_ENCODE_ERROR = "Failed to encode with UTF-8. str: ";

    // 编码utf8格式
    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new KnownsecSdkException(MSG_ENCODE_ERROR + str, e);
        }
    }

    // 编码url 字符串
    public static String encode(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();

        // 使用迭代器来遍历映射，避免产生临时变量。
        Iterator<Map.Entry<String, String>> paramIt = params.entrySet().iterator();
        while (paramIt.hasNext()) {
            Map.Entry<String, String> param = paramIt.next();
            String key = param.getKey();
            String value = param.getValue();

            if (StringUtils.isEmpty(key)) {
                continue;
            }

            result.append(encode(key));

            if (StringUtils.isNotEmpty(value)) {
                result.append("=");
                result.append(encode(value));
            }

            // 添加"&"符号到最后一个映射元素之前，避免在首元素前添加"&"符号。
            if (paramIt.hasNext()) {
                result.append("&");
            }
        }

        return result.toString();
    }
}
