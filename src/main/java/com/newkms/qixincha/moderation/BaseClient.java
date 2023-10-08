package com.newkms.qixincha.moderation;


import com.newkms.qixincha.utils.AssertUtils;

/**
 * 提供两种实例化的方案 初始化一个client
 */
public class BaseClient {
    protected ModerationRequester requester;

    public BaseClient(ModerationRequester requester) {
        AssertUtils.notNull(requester, "ModerationRequester can not be null");
        this.requester = requester;
    }


    public BaseClient(String appId, String secretKey) {
        this.requester = new ModerationRequester(appId, secretKey);
        AssertUtils.notNull(requester, "ModerationRequester can not be null");
    }

}
