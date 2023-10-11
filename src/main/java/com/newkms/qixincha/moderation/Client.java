package com.newkms.qixincha.moderation;

import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.client.DefaultClient;

public abstract class Client {
    protected DefaultClient client;


    public Client(ClientConfig clientConfig) {
        client = createClient(clientConfig);
    }

    // 获取一个
    protected DefaultClient createClient(ClientConfig clientConfig) {
        return new DefaultClient(clientConfig);
    }
}
