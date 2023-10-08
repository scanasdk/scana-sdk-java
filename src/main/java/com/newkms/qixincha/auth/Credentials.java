

package com.newkms.qixincha.auth;


import com.newkms.qixincha.utils.AssertUtils;

/**
 * 访问护架卫士的凭证信息。可以登录护架官网找到自己的凭证信息。请妥善保管，避免泄露。
 */
public class Credentials {

    private final String appId;
    private final String secretKey;


    public Credentials(String appId, String secretKey) {
        AssertUtils.notBlank(appId, "app id should not be blank");
        AssertUtils.notBlank(secretKey, "secret key should not be blank");
        this.appId = appId;
        this.secretKey = secretKey;
    }

    public String getAppId() {
        return appId;
    }


    public String getSecretKey() {
        return secretKey;
    }

}
