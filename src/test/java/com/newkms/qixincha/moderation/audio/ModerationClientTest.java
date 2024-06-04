package com.newkms.qixincha.moderation.audio;

import com.newkms.qixincha.moderation.ModerationClient;
import com.newkms.qixincha.moderation.ModerationRequester;
import com.newkms.qixincha.moderation.audio.v3.AudioModerationInput;
import com.newkms.qixincha.moderation.audio.v3.AudioModerationOutput;
import org.junit.Test;

public class ModerationClientTest {
    private final String secretKey = "a2f175a8-5373-11ed-9949-0242ac12000e";
    private final String appId = "62ac786311b92177337a933a";
    private final String businessId = "1720063483019526161";

    @Test
    public void testAudioAsyncCheck() {

        // 实例化一个requester，入参需要传入hjws内容安全分配的appId，secretKey
        ModerationRequester moderationRequester = new ModerationRequester(appId, secretKey);

        // 实例化发起请求的client 对象
        ModerationClient audioClient = new ModerationClient(moderationRequester);
        // 实例化请求对象,传入业务ID
        AudioModerationInput audioModerationInput = new AudioModerationInput(businessId);
        // 设置检测内容
        audioModerationInput.setExtra("测试");
        audioModerationInput.setUrl(
                "https://files-rs.zsxq.com/lpAtQmTCJ8YKCVXFueVSncMSOL8C?attname=%E9%A3%8E%E7%94%B5%26%E7%94%B5%E7%BD%91%E5%91%A8%E5%BA%A6%E6%95%B0%E6%8D%AE%E4%B8%8E%E8%A7%82%E7%82%B9%E6%B1%87%E6%8A%A5+231007.mp3&e=1712308798&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:CVP8s58Shu7CbIZmom8l_JFOlG4=");
        audioModerationInput.setContentId("test-audio");
        AudioModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = audioClient.AudioAsyncModeration(audioModerationInput);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}