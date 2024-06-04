package com.newkms.qixincha.moderation.url;

import org.junit.Test;

import com.newkms.qixincha.moderation.ModerationClient;
import com.newkms.qixincha.moderation.ModerationRequester;
import com.newkms.qixincha.moderation.url.v3.UrlModerationInput;
import com.newkms.qixincha.moderation.url.v3.UrlModerationOutput;

public class ModerationClientTest {
    private final String secretKey = "a2f175a8-5373-11ed-9949-0242ac12000e";
    private final String appId = "62ac786311b92177337a933a";
    private final String businessId = "1720063483019526161";

    @Test
    public void testUrlAsyncCheck() {

        // 实例化一个requester，入参需要传入hjws内容安全分配的appId，secretKey
        ModerationRequester moderationRequester = new ModerationRequester(appId, secretKey);

        // 实例化发起请求的client 对象
        ModerationClient client = new ModerationClient(moderationRequester);
        // 实例化请求对象,传入业务ID
        UrlModerationInput input = new UrlModerationInput(businessId);
        // 设置检测内容
        input.setExtra("测试");
        input.setUrl(
                "http://example.com");
        input.setContentId("test-url");
        UrlModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = client.UrlAsyncModeration(input);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
