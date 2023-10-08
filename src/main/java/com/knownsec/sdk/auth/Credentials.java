

package com.knownsec.sdk.auth;


import com.knownsec.sdk.utils.AssertUtils;

/**
 * 访问护架卫士的凭证信息。可以登录护架官网找到自己的凭证信息。请妥善保管，避免泄露。
 */
public class Credentials {

    private final String appId;
    private final String secretKey;

//    private final String businessId;

    public Credentials(String appId, String secretKey) {
        AssertUtils.notBlank(appId, "app id should not be blank");
        AssertUtils.notBlank(secretKey, "secret key should not be blank");
//        AssertUtils.notBlank(businessId, "business id should not be blank");
        this.appId = appId;
        this.secretKey = secretKey;
//        this.businessId = businessId;
    }

    public String getAppId() {
        return appId;
    }


    public String getSecretKey() {
        return secretKey;
    }

//    public String getBusinessId() {
//        return businessId;
//    }
}
