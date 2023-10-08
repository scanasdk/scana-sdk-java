package com.newkms.qixincha.moderation.doc;

import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.moderation.Client;
import com.newkms.qixincha.moderation.doc.v3.DocModerationInput;
import com.newkms.qixincha.moderation.doc.v3.DocModerationOutput;

/**
 * 文本审核请求客户端
 */
public class DocModerationClient extends Client {
    public DocModerationClient(ClientConfig clientConfig) {
        super(clientConfig);
    }


    public DocModerationOutput DocAsyncModeration(DocModerationInput input) {
        // 赋值appId secretKey
        input.setAppId(this.client.getCredentials().getAppId());
        input.setSecretKey(this.client.getCredentials().getSecretKey());
        return this.client.execute(input);
    }
}
