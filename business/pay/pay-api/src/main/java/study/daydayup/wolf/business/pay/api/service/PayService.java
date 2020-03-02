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
    Result<PaymentCreateResponse> create(PaymentCreateRequest request);

    /**
     * pay
     * 一般是前端(H5, app, ...)发起
     */
    Result<PayVerifyResponse> pay(PayVerifyRequest request);

    /**
     * 订阅异步通知
     * @param data
     * @return
     */
    Result<String> subscribe(String data);

}
