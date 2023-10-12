package com.newkms.qixincha.utils;

import com.newkms.qixincha.auth.Credentials;
import com.newkms.qixincha.auth.SignerImpl;
import com.newkms.qixincha.exception.KnownsecSdkException;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名工具
 */
public class SignatureUtils {

    public static final String PARAM_SIGN = "signature";
    public static final String PARAM_APP_ID = "appId";
    public static final Duration SIGNTIMEOUT = Duration.ofMinutes(5);

    private static String genSignature(Credentials credentials, Map<String, String> params) {
        return SignerImpl.INSTANCE.genSignature(credentials, params).getSignature();
    }

    /**
     * 验证签名是否匹配
     *
     * @param requestParams 签名的参数
     * @param credentials   签名的key
     * @return 是否匹配
     * @throws com.newkms.qixincha.exception.KnownsecSdkException
     */
    public static boolean verifySignature(Map<String, String[]> requestParams, Credentials credentials) {
        if (credentials == null ||
                !requestParams.containsKey("businessId") || !requestParams.containsKey("timestamp") ||
                !requestParams.containsKey(PARAM_SIGN)) {
            return false;
        }

        // 校验是否过期
        Map<String, String> params = new HashMap<>();
        Instant timestamp = Instant.ofEpochSecond(Long.parseLong(requestParams.get("timestamp")[0]));
        if (Duration.between(timestamp, Instant.now()).compareTo(SIGNTIMEOUT) > 0) {
//            throw new KnownsecSdkException("timestamp is timeout");
            return false;
        }


        String signature = null;
        for (String paramName : requestParams.keySet()) {
            String[] value = requestParams.get(paramName);
            if (value == null || value.length == 0) {
                continue;
            }

            if (PARAM_SIGN.equals(paramName)) {
                signature = value[0];
            } else {
                params.put(paramName, value[0]);
            }
        }
        if (StringUtils.isBlank(signature)) {
            // 签名为空，直接返回失败
            System.out.println("blank");
            return false;
        }
        String generatedSignature = genSignature(credentials, params);
        if (StringUtils.isBlank(generatedSignature)) {
            return false;
        }
        System.out.println(generatedSignature);
        return signature.equals(generatedSignature);
    }
}
