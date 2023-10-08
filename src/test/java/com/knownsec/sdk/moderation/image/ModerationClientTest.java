package com.knownsec.sdk.moderation.image;

import com.knownsec.sdk.moderation.ModerationRequester;
import com.knownsec.sdk.moderation.image.v3.async.ImageAsyncModerationInput;
import com.knownsec.sdk.moderation.image.v3.async.ImageAsyncModerationOutput;
import com.knownsec.sdk.moderation.image.v3.sync.ImageSyncModerationInput;
import com.knownsec.sdk.moderation.image.v3.sync.ImageSyncModerationOutput;
import com.knownsec.sdk.request.BaseImage;
import com.knownsec.sdk.request.ImageTypeEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ModerationClientTest {
    private final String secretKey = "a2f175a8-5373-11ed-9949-0242ac12000e";
    private final String appId = "62ac786311b92177337a933b";
    private final String businessId = "1800097935845781514";

    @Test
    public void testImageSyncCheck() {

        // 实例化一个requester，入参需要传入易盾内容安全分配的secretId，secretKey
        ModerationRequester moderationRequester = new ModerationRequester(appId, secretKey);

        // 实例化发起请求的client 对象
        ImageClient imageClient = new ImageClient(moderationRequester);
        // 实例化请求对象,传入业务ID
        ImageSyncModerationInput imageSyncModerationInput = new ImageSyncModerationInput(businessId);
        // 设置检测内容
        imageSyncModerationInput.setImages(createImages());
        ImageSyncModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = imageClient.ImageSyncModeration(imageSyncModerationInput);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testImageAsyncCheck() {

        // 实例化一个requester，入参需要传入易盾内容安全分配的secretId，secretKey
        ModerationRequester moderationRequester = new ModerationRequester(appId, secretKey);

        // 实例化发起请求的client 对象
        ImageClient imageClient = new ImageClient(moderationRequester);
        // 实例化请求对象,传入业务ID
        ImageAsyncModerationInput imageAsyncModerationInput = new ImageAsyncModerationInput(businessId);
        // 设置检测内容
        imageAsyncModerationInput.setImages(createImages());
        ImageAsyncModerationOutput resp = null;
        try {
            // 发起批量同步检测的请求
            resp = imageClient.ImageAsyncModeration(imageAsyncModerationInput);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建批量图片数据
     */
    private static List<BaseImage> createImages() {
        int contentCount = 1;
        List<BaseImage> imageObj = new ArrayList<>(contentCount);
        BaseImage image = null;
        for (int i = 0; i < contentCount; i++) {
            image = new BaseImage();
            image.setContentId("数据唯一标识");
            image.setData("https://images-rs.zsxq.com/Fo2bIKw8e8JkASNNnjPnnlD1psNU?e=1712303090&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:gV6Ox7ive-7pAWw9TN798ltryv4=");
            image.setType(ImageTypeEnum.URL.toInteger());
            System.out.println(ImageTypeEnum.URL.toInteger());
            // 请求对象中的其他参数如果有需要，请参考官方接口文档中字段说明，按需添加
            imageObj.add(image);
        }
        return imageObj;
    }

}