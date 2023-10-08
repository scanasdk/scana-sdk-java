package com.knownsec.sdk.response;

// 定义api 响应抽象类
public abstract class ApiResult implements BaseResponse {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResult() {
    }

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResponse{" + "code=" + code + ", message='" + message + '\'' + '}';
    }
}
