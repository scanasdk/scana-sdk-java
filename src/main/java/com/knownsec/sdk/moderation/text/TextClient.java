package com.knownsec.sdk.moderation.text;

import com.knownsec.sdk.moderation.BaseClient;
import com.knownsec.sdk.moderation.ModerationRequester;
import com.knownsec.sdk.moderation.text.v3.async.TextAsyncModerationInput;
import com.knownsec.sdk.moderation.text.v3.async.TextAsyncModerationOutput;
import com.knownsec.sdk.moderation.text.v3.sync.TextSyncModerationInput;
import com.knownsec.sdk.moderation.text.v3.sync.TextSyncModerationOutput;

public class TextClient extends BaseClient {
    public TextClient(ModerationRequester textRequester) {
        super(textRequester);
    }

    /**
     * 文本同步请求
     *
     * @param input TextSyncModerationInput
     * @return TextSyncModerationOutput
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
}
