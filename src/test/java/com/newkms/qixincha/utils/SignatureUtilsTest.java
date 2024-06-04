package com.newkms.qixincha.utils;

import com.newkms.qixincha.auth.SignResult;
import com.newkms.qixincha.auth.SignerImpl;
import com.newkms.qixincha.moderation.ModerationClient;
import com.newkms.qixincha.moderation.text.v3.sync.TextSyncModerationInput;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SignatureUtilsTest {
    String secretKey = "9c3749d3-0a94-11ee-9481-fa163e917565";
    String appId = "62ac786311b92177337a933a";
    String businessId = "1720056633192620049";
    ModerationClient textClient = new ModerationClient(appId, secretKey);

    @Test
    public void genSignature() {
        ModerationClient textClient = new ModerationClient(appId, secretKey);
        TextSyncModerationInput textModerationInput = new TextSyncModerationInput(businessId);
        Map<String, String> signMap = textModerationInput.getSignParams();
        SignResult signResult = SignerImpl.INSTANCE.genSignature(textClient.ClientConfig().getCredentials(), signMap);
        System.out.println(signResult.getSignature());
    }

    @Test
    public void verifySignature() {
        // 模拟request
        // businessId=1808816981760016558&nonce=3bcf2503-dfbf-42cd-817e-35d7d1ee587f&signature=8572cd94f528b78645030cc483a3a3ce&timestamp=1697080077
        Map<String, String[]> requestParams = new HashMap<>();
        // String[] appId = {"62ac786311b92177337a933a"};
        String[] businessId = { "1808816981760016558" };
        String[] nonce = { "3bcf2503-dfbf-42cd-817e-35d7d1ee587f" };
        String[] signature = { "8572cd94f528b78645030cc483a3a3ce" };
        String[] timestamp = { "1697080077" };
        // requestParams.put("appId", appId);
        requestParams.put("businessId", businessId);
        requestParams.put("nonce", nonce);
        requestParams.put("signature", signature);
        requestParams.put("timestamp", timestamp);

        System.out.println(SignatureUtils.verifySignature(requestParams, textClient.ClientConfig().getCredentials()));
    }
}
