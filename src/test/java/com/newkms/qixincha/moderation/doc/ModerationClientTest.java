package com.newkms.qixincha.moderation.doc;

import com.newkms.qixincha.moderation.ModerationClient;
import com.newkms.qixincha.moderation.ModerationRequester;
import com.newkms.qixincha.moderation.doc.v3.DocModerationInput;
import com.newkms.qixincha.moderation.doc.v3.DocModerationOutput;
import org.junit.Test;

public class ModerationClientTest {
    private final String secretKey = "a2f175a8-5373-11ed-9949-0242ac12000e";
    private final String appId = "62ac786311b92177337a933a";
    private final String businessId = "1818208965452919186";

    @Test
    public void testDocAsyncCheck() {

        // 实例化一个requester，入参需要传入hjws内容安全分配的appId，secretKey
        ModerationRequester moderationRequester = new ModerationRequester(appId, secretKey);

        // 实例化发起请求的client 对象
        ModerationClient docClient = new ModerationClient(moderationRequester);
        // 实例化请求对象,传入业务ID
        DocModerationInput docModerationInput = new DocModerationInput(businessId);
        // 设置检测内容
        docModerationInput.setExtra("测试");
        docModerationInput.setUrl(
                "https://files-rs.zsxq.com/Fs_AR1jjWjQkr9jIbLSahVEAqohG?attname=%E6%96%B0%E8%83%BD%E6%BA%90%E6%B1%BD%E8%BD%A6%E5%9B%9B%E5%AD%A3%E5%BA%A6%E6%99%AF%E6%B0%94%E5%BA%A6%E5%B1%95%E6%9C%9B%E7%94%B5%E8%AF%9D%E4%BC%9A%E7%BA%AA%E8%A6%81.pdf&e=1712310714&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:tHtnp2rV64K8-W6qh7XdfEj_BLg=");
        docModerationInput.setContentId("test-doc");
        DocModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = docClient.DocAsyncModeration(docModerationInput);
            System.out.println(resp.getMessage());
            System.out.println(resp.getCode());
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}