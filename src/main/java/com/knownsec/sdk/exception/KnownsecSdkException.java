package com.knownsec.sdk.exception;

public class KnownsecSdkException extends RuntimeException {
    private static final long serialVersionUID = -5279264116296505331L;

    public KnownsecSdkException(String message) {
        super(message);
    }

    public KnownsecSdkException(Throwable cause) {
        super(cause);
    }

    public KnownsecSdkException(String message, Throwable cause) {
        super(message, cause);
    }
}
