package study.daydayup.wolf.business.pay.api.service;

import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.api.service
 *
 * @author Wingle
 * @since 2020/2/27 10:40 上午
 **/
public interface PayService {
    /**
     * 一般是后端向第三方支付公司发起，获取支付参数后传给前端
     * @param request PaymentCreateRequest
     * @return response 第三方支付参数 + 安全校验参数
     */
    Result<PaymentCreateResponse> create(PaymentCreateRequest request);

    /**
     * 同步支付结果校验
     * 一般是前端(H5, app, ...)发起
     * @param request PayVerifyRequest
     * @return PayVerifyResponse
     */
    Result<PayVerifyResponse> verify(PayVerifyRequest request);

    /**
     * 订阅异步通知
     * @param data response of third party pay company
     * @return String
     */
    Result<String> subscribe(String data);

}
