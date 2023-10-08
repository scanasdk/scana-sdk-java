package com.knownsec.sdk.moderation;

import com.knownsec.sdk.client.ClientConfig;
import com.knownsec.sdk.client.DefaultClient;

public abstract class ModerationClient {
    protected DefaultClient client;

    public ModerationClient() {
    }

    public ModerationClient(ClientConfig clientConfig) {
        client = createClient(clientConfig);
    }

    // 获取一个
    protected DefaultClient createClient(ClientConfig clientConfig) {
        return new DefaultClient(clientConfig);
    }
}
