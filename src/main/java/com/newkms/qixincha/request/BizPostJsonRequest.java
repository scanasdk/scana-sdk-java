

package com.newkms.qixincha.request;


import com.newkms.qixincha.response.BaseResponse;
import com.newkms.qixincha.validation.limitation.NotBlank;

import java.util.Map;

/**
 * 这是对 {@link PostJsonRequest} 的扩展。适用于和具体业务绑定的子服务
 */
public abstract class BizPostJsonRequest<T extends BaseResponse> extends PostJsonRequest<T> {

    @NotBlank(message = "businessId不能为空")
    protected String businessId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String businessId() {
        return businessId;
    }

    public BizPostJsonRequest<T> businessId(String businessId) {
        this.businessId = businessId;
        return this;
    }

    @Override
    protected Map<String, String> getCustomSignParams() {
        Map<String, String> params = super.getCustomSignParams();
        params.put("businessId", businessId);
        return params;
    }

    @Override
    public String toString() {
        return "BizPostFormRequest(" + "super=" + super.toString() + ", businessId=" + businessId + ")";
    }
}
