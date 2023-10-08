package com.newkms.qixincha.client;

import com.newkms.qixincha.exception.KnownsecSdkException;
import com.newkms.qixincha.request.BaseRequest;
import com.newkms.qixincha.response.BaseResponse;

public interface Client {
    <R extends BaseResponse> R execute(BaseRequest<R> request) throws KnownsecSdkException;
}
