package com.knownsec.sdk.moderation.text;

import com.knownsec.sdk.moderation.ModerationRequester;
import com.knownsec.sdk.moderation.text.v3.async.TextAsyncModerationInput;
import com.knownsec.sdk.moderation.text.v3.async.TextAsyncModerationOutput;
import com.knownsec.sdk.moderation.text.v3.sync.TextSyncModerationOutput;
import com.knownsec.sdk.request.BaseText;
import com.knownsec.sdk.moderation.text.v3.sync.TextSyncModerationInput;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ModerationClientTest {

    @Test
    public void testTextSyncCheck() {
        String secretKey = "a2f175a8-5373-11ed-9949-0242ac12000e";
        String appId = "62ac786311b92177337a933b";
        String businessId = "1720056633192620049";
        // 实例化一个requester，入参需要传入易盾内容安全分配的secretId，secretKey
        ModerationRequester moderationRequester = new ModerationRequester(appId, secretKey);

        // 实例化发起请求的client 对象
        TextClient textClient = new TextClient(moderationRequester);
        // 实例化请求对象,传入业务ID
        TextSyncModerationInput textModerationInput = new TextSyncModerationInput(businessId);
        // 设置检测内容
        textModerationInput.setText(createTexts());
        TextSyncModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = textClient.TextSyncModeration(textModerationInput);
            System.out.println(resp.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test //测试文本异步接口
    public void testTextAsyncCheck() {
        String secretKey = "a2f175a8-5373-11ed-9949-0242ac12000e";
        String appId = "62ac786311b92177337a933b";
        String businessId = "1720056633192620049";
        // 实例化一个requester，入参需要传入易盾内容安全分配的secretId，secretKey
        ModerationRequester moderationRequester = new ModerationRequester(appId, secretKey);

        // 实例化发起请求的client 对象
        TextClient textClient = new TextClient(moderationRequester);
        // 实例化请求对象,传入业务ID
        TextAsyncModerationInput textModerationInput = new TextAsyncModerationInput(businessId);
        // 设置检测内容
        textModerationInput.setText(createTexts());
        TextAsyncModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = textClient.TextAsyncModeration(textModerationInput);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建批量文本数据
     */
    /**
     * 创建批量文本数据
     */
    private static List<BaseText> createTexts() {
        int contentCount = 1;
        List<BaseText> textObj = new ArrayList<>(contentCount);
        BaseText text = null;
        for (int i = 0; i < contentCount; i++) {
            text = new BaseText();
            text.setContentId("数据唯一标识");
            text.setData("测试异步接口");
            // 请求对象中的其他参数如果有需要，请参考官方接口文档中字段说明，按需添加
            textObj.add(text);
        }
        return textObj;
    }

}