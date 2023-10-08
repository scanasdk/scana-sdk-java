package com.knownsec.sdk.moderation;


import com.knownsec.sdk.utils.AssertUtils;

public class BaseClient {
    protected ModerationRequester requester;

    public BaseClient(ModerationRequester requester) {
        AssertUtils.notNull(requester, "AntispamRequester can not be null");
        this.requester = requester;
    }

}
