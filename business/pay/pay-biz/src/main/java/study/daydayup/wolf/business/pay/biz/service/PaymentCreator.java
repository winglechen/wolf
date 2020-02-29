package study.daydayup.wolf.business.pay.biz.service;

import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateResponse;

/**
 * study.daydayup.wolf.business.pay.biz.service
 *
 * @author Wingle
 * @since 2020/2/28 12:29 下午
 **/
public interface PaymentCreator {
    PaymentCreateResponse create(PaymentCreateRequest request);


    void validateRequest();
    void createPayment();

    void callPayApi();

    void logPayRequest();
    void savePayment();
    PaymentCreateResponse formatResponse();
}
