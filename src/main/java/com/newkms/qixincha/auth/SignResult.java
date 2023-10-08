

package com.newkms.qixincha.auth;

/**
 * 请求签名信息
 */
public class SignResult {

    /**
     * 签名算法
     */
    private final SignatureMethodEnum signMethod;
    /**
     * 访问scana服务的凭证ID
     */
    private final String appId;
    /**
     * 请求签名
     */
    private final String signature;

    public SignResult(SignatureMethodEnum signMethod, String appId, String signature) {
        this.signMethod = signMethod;
        this.appId = appId;
        this.signature = signature;
    }

    public SignatureMethodEnum getSignMethod() {
        return signMethod;
    }

    public String getAppId() {
        return appId;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return "SignResult(" +
                "signMethod=" + signMethod +
                ", appId=" + appId +
                ", signature=" + signature +
                ")";
    }
}
