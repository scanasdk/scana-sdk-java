

package com.newkms.qixincha.auth;

import com.newkms.qixincha.utils.SM3Digest;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;

public enum SignatureMethodEnum {
    MD5(str -> DigestUtils.md5Hex(str.getBytes(StandardCharsets.UTF_8))),
    SHA1(str -> DigestUtils.sha1Hex(str.getBytes(StandardCharsets.UTF_8))),
    SHA256(str -> DigestUtils.sha256Hex(str.getBytes(StandardCharsets.UTF_8))),
    SM3(str -> SM3Digest.sm3Hex(str.getBytes(StandardCharsets.UTF_8)));

    private SignCalculator signCalculator;

    public String calcSign(String str) {
        return signCalculator.calc(str);
    }

    SignatureMethodEnum(SignCalculator signCalculator) {
        this.signCalculator = signCalculator;
    }

    @FunctionalInterface
    private interface SignCalculator {
        String calc(String str);
    }
}
