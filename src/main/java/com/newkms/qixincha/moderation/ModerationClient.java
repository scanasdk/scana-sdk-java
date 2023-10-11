package com.newkms.qixincha.moderation;

import com.newkms.qixincha.client.ClientConfig;
import com.newkms.qixincha.moderation.audio.v3.AudioModerationInput;
import com.newkms.qixincha.moderation.audio.v3.AudioModerationOutput;
import com.newkms.qixincha.moderation.doc.v3.DocModerationInput;
import com.newkms.qixincha.moderation.doc.v3.DocModerationOutput;
import com.newkms.qixincha.moderation.image.v3.async.ImageAsyncModerationInput;
import com.newkms.qixincha.moderation.image.v3.async.ImageAsyncModerationOutput;
import com.newkms.qixincha.moderation.image.v3.sync.ImageSyncModerationInput;
import com.newkms.qixincha.moderation.image.v3.sync.ImageSyncModerationOutput;
import com.newkms.qixincha.moderation.text.v3.async.TextAsyncModerationInput;
import com.newkms.qixincha.moderation.text.v3.async.TextAsyncModerationOutput;
import com.newkms.qixincha.moderation.text.v3.sync.TextSyncModerationInput;
import com.newkms.qixincha.moderation.text.v3.sync.TextSyncModerationOutput;
import com.newkms.qixincha.moderation.video.v3.VideoModerationInput;
import com.newkms.qixincha.moderation.video.v3.VideoModerationOutput;

/**
 * 通用客户端
 */
public class ModerationClient extends BaseClient {
    /**
     * 通过请求器创建
     *
     * @param requester
     */
    public ModerationClient(ModerationRequester requester) {
        // 实例化一个requester，入参需要传入scana内容安全分配的appId，secretKey
        super(requester);
    }

    public ClientConfig ClientConfig() {
        return super.requester.getClientConfig();
    }

    /**
     * 通过appId ,secretKey 创建
     *
     * @param appId
     * @param secretKey
     */

    public ModerationClient(String appId, String secretKey) {
        super(appId, secretKey);
    }

    /**
     * 图片同步请求
     *
     * @param input TextSyncModerationInput
     * @return TextSyncModerationOutput
     */

    public ImageSyncModerationOutput ImageSyncModeration(ImageSyncModerationInput input) {
        return this.requester.getImageCheckClient().ImageSyncModeration(input);
    }


    /**
     * 图片异步审核请求
     *
     * @param Input TextAsyncModerationInput
     * @return TextAsyncModerationOutput
     */
    public ImageAsyncModerationOutput ImageAsyncModeration(ImageAsyncModerationInput Input) {
        return this.requester.getImageCheckClient().ImageAsyncModeration(Input);
    }

    /**
     * 文本同步请求
     *
     * @param input
     * @return
     */

    public TextSyncModerationOutput TextSyncModeration(TextSyncModerationInput input) {
        return this.requester.getTextCheckClient().TextSyncModeration(input);
    }


    /**
     * 文本异步审核请求
     *
     * @param Input TextAsyncModerationInput
     * @return TextAsyncModerationOutput
     */
    public TextAsyncModerationOutput TextAsyncModeration(TextAsyncModerationInput Input) {
        return this.requester.getTextCheckClient().TextAsyncModeration(Input);
    }


    /**
     * 文档异步审核请求
     *
     * @param input
     * @return
     */
    public DocModerationOutput DocAsyncModeration(DocModerationInput input) {
        return this.requester.getDocCheckClient().DocAsyncModeration(input);
    }


    /**
     * 音频异步审核请求
     *
     * @param input
     * @return
     */
    public AudioModerationOutput AudioAsyncModeration(AudioModerationInput input) {
        return this.requester.getAudioCheckClient().AudioAsyncModeration(input);
    }

    /**
     * 视频异步审核请求
     *
     * @param input
     * @return
     */
    public VideoModerationOutput VideoAsyncModeration(VideoModerationInput input) {
        return this.requester.getVideoCheckClient().VideoAsyncModeration(input);
    }
}
