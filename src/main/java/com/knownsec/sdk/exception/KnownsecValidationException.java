package com.knownsec.sdk.exception;

public class KnownsecValidationException extends RuntimeException {

    public KnownsecValidationException(String message) {
        super(message);
    }

    public KnownsecValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public KnownsecValidationException(Throwable cause) {
        super(cause);
    }

    protected KnownsecValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
