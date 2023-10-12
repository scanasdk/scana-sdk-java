package com.newkms.qixincha.utils;

import com.newkms.qixincha.auth.SignResult;
import com.newkms.qixincha.auth.SignerImpl;
import com.newkms.qixincha.moderation.ModerationClient;
import com.newkms.qixincha.moderation.text.v3.sync.TextSyncModerationInput;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class SignatureUtilsTest {
    String secretKey = "a2f175a8-5373-11ed-9949-0242ac12000e";
    String appId = "62ac786311b92177337a933b";
    String businessId = "1720056633192620049";
    ModerationClient textClient = new ModerationClient(appId, secretKey);

    @Test
    public void genSignature() {
        ModerationClient textClient = new ModerationClient(appId,secretKey);
        TextSyncModerationInput textModerationInput = new TextSyncModerationInput(businessId);
        Map<String, String> signMap = textModerationInput.getSignParams();
        SignResult signResult = SignerImpl.INSTANCE.genSignature(textClient.ClientConfig().getCredentials(), signMap);
        System.out.println(signResult.getSignature());
    }

    @Test
    public void verifySignature() {
        // 模拟request
        Map<String, String[]> requestParams = new HashMap<>();
        String[] appId = {"62ac786311b92177337a933b"};
        String[] businessId = {"1720056633192620049"};
        String[] nonce = {"C8UilBiztG"};
        String[] signature = {"1742fbc9efbd26db2c0fc9f90200d0bf"};
        String[] timestamp = {"1697020537"};
        requestParams.put("appId", appId);
        requestParams.put("businessId", businessId);
        requestParams.put("nonce", nonce);
        requestParams.put("signature", signature);
        requestParams.put("timestamp", timestamp);

        System.out.println(SignatureUtils.verifySignature(requestParams, textClient.ClientConfig().getCredentials().getSecretKey()))
        ;
    }
}
