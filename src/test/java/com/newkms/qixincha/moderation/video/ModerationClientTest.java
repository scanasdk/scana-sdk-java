package com.newkms.qixincha.moderation.video;

import com.newkms.qixincha.moderation.ModerationClient;
import com.newkms.qixincha.moderation.ModerationRequester;
import com.newkms.qixincha.moderation.video.v3.VideoModerationInput;
import com.newkms.qixincha.moderation.video.v3.VideoModerationOutput;
import org.junit.Test;

public class ModerationClientTest {
    private final String secretKey = "a2f175a8-5373-11ed-9949-0242ac12000e";
    private final String appId = "62ac786311b92177337a933a";
    private final String businessId = "1813481786970231031";

    @Test
    public void testVideoAsyncCheck() {

        // 实例化一个requester，入参需要传入hjws内容安全分配的appId，secretKey
        ModerationRequester moderationRequester = new ModerationRequester(appId, secretKey);

        // 实例化发起请求的client 对象
        ModerationClient videoClient = new ModerationClient(moderationRequester);
        // 实例化请求对象,传入业务ID
        VideoModerationInput videoModerationInput = new VideoModerationInput(businessId);
        // 设置检测内容
        videoModerationInput.setExtra("测试");
        videoModerationInput.setUrl(
                "https://rs-videos.zsxq.com/c3w3vfhmsiw0uofgs11kxm7wdnnf1csg.m3u8?MtsHlsUriToken=ZW5jcnlwdGVkPTAmdWlkPTAmdmlkPTg1Njg1JmV0PTE3MDE5MzY0MDI3MTkmc2lnbj0wRTg5NjhEMjQ2QURBRDBFMUEzOUJGMjU0OEMyM0YwQQ%3D%3D");
        videoModerationInput.setContentId("test-video");
        VideoModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = videoClient.VideoAsyncModeration(videoModerationInput);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}