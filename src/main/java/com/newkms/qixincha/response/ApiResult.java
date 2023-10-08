package com.newkms.qixincha.response;

// 定义api 响应抽象类
public abstract class ApiResult implements BaseResponse {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResponse{" + "code=" + code + ", message='" + message + '\'' + '}';
    }
}
