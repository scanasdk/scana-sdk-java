package com.newkms.qixincha.moderation;


import com.newkms.qixincha.utils.AssertUtils;
import com.newkms.qixincha.utils.SignatureUtils;

import java.util.Map;

/**
 * 提供两种实例化的方案 初始化一个client
 */
public class BaseClient {
    /**
     * 内置请求器
     */
    protected ModerationRequester requester;

    public BaseClient(ModerationRequester requester) {
        AssertUtils.notNull(requester, "ModerationRequester can not be null");
        this.requester = requester;
    }


    public BaseClient(String appId, String secretKey) {
        this.requester = new ModerationRequester(appId, secretKey);
        AssertUtils.notNull(requester, "ModerationRequester can not be null");
    }

    /**
     * 校验签名
     */
    public boolean ValidAuth(Map<String, String[]> requestParams) {
        return SignatureUtils.verifySignature(requestParams, requester.getClientConfig().getCredentials());
    }

    /**
     * 解析回调参数，异步审核时，接口接收回调可以使用该方法解析获得审核数据
     */

}
