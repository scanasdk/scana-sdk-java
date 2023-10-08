package com.knownsec.sdk.client;

import com.knownsec.sdk.exception.KnownsecSdkException;
import com.knownsec.sdk.request.BaseRequest;
import com.knownsec.sdk.response.BaseResponse;

public interface Client {
    <R extends BaseResponse> R execute(BaseRequest<R> request) throws KnownsecSdkException;
}
